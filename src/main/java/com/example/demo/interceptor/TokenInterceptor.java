package com.example.demo.interceptor;


import com.example.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
//拦截器，这是springboot的组件，所以需要进行声明
@Component
public class TokenInterceptor implements HandlerInterceptor {

    //请求处理之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle....");

        //获取请求路径
        String requestURI = request.getRequestURI();//ip地址后面的部分
        //判断是否是登录请求，如果路径中包含login，说明是登录操作，放行
        if(requestURI.contains("login")&&requestURI.contains("register"))
        {
            log.info("登录操作，放行");
            return true;
        }
        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否存在，如果不存在，说明用户未登录，返回错误信息（响应401状态码）
        if(token==null||token.isEmpty())
        {
            log.info("令牌为空,用户未登录");
            response.setStatus(401);
            return false;
        }
        //如果存在，校验令牌，如果校验失败，返回错误信息（响应401状态码）
        try
        {
            JwtUtils.parseJwt( token);
        }
        catch (Exception e)
        {
            log.info("令牌非法,响应401");
            response.setStatus(401);
            return false;
        }
        //校验通过，放行
        log.info("令牌合法，放行");
        return true;
    }


    //请求处理之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle....");
    }

    //视图渲染完成之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion....");
    }
}
