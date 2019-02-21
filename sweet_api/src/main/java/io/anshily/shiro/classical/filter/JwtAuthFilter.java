package io.anshily.shiro.classical.filter;

import io.anshily.base.core.ResultGenerator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtAuthFilter extends AuthenticatingFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

//    @Override
//    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
//        request.getAttributeNames();
//        return null;
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        PrintWriter out = response.getWriter();
////        out.println(JSONObject.toJSONString("未登录"));
//        out.println(ResultGenerator.errResult(403,"未登录"));
//        out.flush();
//        out.close();
//        return false;
//    }


    /**
     * 父类会在请求进入拦截器后调用该方法，返回true则继续，返回false则会调用onAccessDenied()。这里在不通过时，还调用了isPermissive()方法，我们后面解释。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(this.isLoginRequest(request, response))
            return true;
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch(IllegalStateException e){ //not found any token
            logger.error("Not found any token");
        }catch (Exception e) {
            logger.error("Error occurs when login", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }
    /**
     * 这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro。这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
//        String jwtToken = getAuthzHeader(servletRequest);
//        if(StringUtils.isNotBlank(jwtToken)&&!JwtUtils.isTokenExpired(jwtToken))
//            return new JWTToken(jwtToken);
        HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
        String token = httpRequest.getHeader("x-auth-token");
        if (token != null){
            return new JWTToken(token);
        }
        return null;
    }
    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
//        httpResponse.setCharacterEncoding("UTF-8");
//        httpResponse.setContentType("application/json;charset=UTF-8");
//        httpResponse.setStatus(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);

        setHeader((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
        PrintWriter out = servletResponse.getWriter();
//        out.println(JSONObject.toJSONString("未登录"));
        out.println(ResultGenerator.errResult(403,"未登录"));
        out.flush();
        out.close();
        return false;
    }
    /**
     *  如果Shiro Login认证成功，会进入该方法，等同于用户名密码登录成功，我们这里还判断了是否要刷新Token
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String newToken = null;
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

        return true;
    }
    /**
     * 如果调用shiro的login认证失败，会回调这个方法，这里我们什么都不做，因为逻辑放到了onAccessDenied（）中。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        logger.error("Validate token fail, token:{}, error:{}", token.toString(), e.getMessage());
        return false;
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
