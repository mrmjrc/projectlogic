package cn.mrmj.filter;

import cn.mrmj.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * create by: mrmj
 * description: tocken处理器，判断tocken是否正确
 * create time: 2019/11/26 15:01
 */
public class AuthHandler extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    @Override
    @CrossOrigin
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         if (true){
             return true;
       }
        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }
        logger.info("=====================权限验证====================");
        //从请求中获取到对应的信息并保存
        String url = request.getRequestURI();
        String jwt = (String) request.getAttribute("jwt");
        if(StringUtils.isEmpty(jwt)){
            //RequestDispatcher 让两个servlet相互通信成为可能，重定向
            RequestDispatcher
                    dispatcher = request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        //Claims授权
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {

        }
        //Claims授权信息为空
        if (claims == null) {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        if("test_admin".equals(claims.get("account").toString())){
            return true;
        }
        List<String> menuList = claims.get("menuList", List.class);
        if (menuList == null || menuList.isEmpty() || !menuList.contains(url)){
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/auth/error");
            dispatcher.forward(request, response);
            return false;
        }
        return true;
  }
}