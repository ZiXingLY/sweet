package io.anshily.shiro.classical.filter;

import io.anshily.base.core.ResultGenerator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CORSShrioFilter extends UserFilter {

    /**
     * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("preHandle");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }

    /**
     * 该方法会在验证失败后调用，这里由于是前后端分离，后台不控制页面跳转
     * 因此重写改成传输JSON数据
     */
    @Override
    public void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("saveRequestAndRedirectToLogin");
        saveRequest(request);
        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
        PrintWriter out = response.getWriter();
//        out.println(JSONObject.toJSONString("未登录"));
        out.println(ResultGenerator.errResult(403,"未登录"));
        out.flush();
        out.close();
    }

//    @Override
//    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        System.out.println("onAccessDenied");
//        saveRequest(request);
//        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
//        PrintWriter out = response.getWriter();
//        out.println(JSONObject.toJSONString("未登录"));
//        out.flush();
//        out.close();
//        return false;
//    }

//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        saveRequestAndRedirectToLogin(request, response);
//        return false;
//    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("isAccessAllowed");
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }


    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request,HttpServletResponse response){
        System.out.println("setHeader");
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
