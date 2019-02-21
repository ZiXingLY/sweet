package io.anshily.shiro.jwt.filter;

import io.anshily.base.core.ResultGenerator;
import io.anshily.shiro.jwt.configuration.JWTToken;
import io.anshily.shiro.jwt.configuration.JwtUtils;
import io.anshily.shiro.jwt.service.JWTUserService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtAuthFilter extends AuthenticatingFilter {
	private final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private static final int tokenRefreshInterval = 300;
    private JWTUserService userService;

    public JwtAuthFilter(JWTUserService userService){
        this.userService = userService;
        this.setLoginUrl("/login");
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) //对于OPTION请求做拦截，不做token校验
            return false;

        return super.preHandle(request, response);
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response){
        this.fillCorsHeader(WebUtils.toHttp(request), WebUtils.toHttp(response));
        request.setAttribute("jwtShiroFilter.FILTERED", true);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(this.isLoginRequest(request, response))
            return true;
        Boolean afterFiltered = (Boolean)(request.getAttribute("jwtShiroFilter.FILTERED"));
        if( BooleanUtils.isTrue(afterFiltered))
        	return true;

        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch(IllegalStateException e){ //not found any token
            log.error("Not found any token");
//            setHeader((HttpServletRequest) request,(HttpServletResponse) response);
//            PrintWriter out = null;
//            try {
//                out = response.getWriter();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
////        out.println(JSONObject.toJSONString("未登录"));
//            out.println(ResultGenerator.errResult(403,"Not found any token"));
//            out.flush();
//            out.close();
            return false;
        }catch (Exception e) {
            log.error("Error occurs when login", e);
            return false;
        }
        return allowed || super.isPermissive(mappedValue);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String jwtToken = getAuthzHeader(servletRequest);
        if(StringUtils.isNotBlank(jwtToken)&&!JwtUtils.isTokenExpired(jwtToken))
            return new JWTToken(jwtToken);

        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
//        httpResponse.setCharacterEncoding("UTF-8");
//        httpResponse.setContentType("application/json;charset=UTF-8");
//        httpResponse.setStatus(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);
//        fillCorsHeader(WebUtils.toHttp(servletRequest), httpResponse);
        setHeader((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
        PrintWriter out = servletResponse.getWriter();
//        out.println(JSONObject.toJSONString("未登录"));
        out.println(ResultGenerator.errResult(403,"未登录"));
        out.flush();
        out.close();

        return false;
    }

//    登录成功 根据需要更新token
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse httpResponse = WebUtils.toHttp(response);
//        String newToken = null;
//        if(token instanceof JWTToken){
//            JWTToken jwtToken = (JWTToken)token;
//            UserDto user = (UserDto) subject.getPrincipal();
//            boolean shouldRefresh = shouldTokenRefresh(JwtUtils.getIssuedAt(jwtToken.getToken()));
//            if(shouldRefresh) {
//                newToken = userService.generateJwtToken(user.getUsername());
//            }
//        }
//        if(StringUtils.isNotBlank(newToken))
//            httpResponse.setHeader("x-auth-token", newToken);
//
//        return true;
//    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("Validate token fail, token:{}, error:{}", token.toString(), e.getMessage());
        return false;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String header = httpRequest.getHeader("x-auth-token");
        return StringUtils.removeStart(header, "Bearer ");
    }

    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

    protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        System.out.println("setHeader");
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(org.springframework.http.HttpStatus.OK.value());
    }
}
