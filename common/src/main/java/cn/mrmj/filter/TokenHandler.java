package cn.mrmj.filter;

import cn.mrmj.utils.JwtUtils;
import cn.mrmj.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * create by: mrmj
 * description: token验证处理
 * create time: 2019/11/26 18:02
 */
public class TokenHandler extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    @Override
    @CrossOrigin
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }
        if(true) {
            return true;
        }
        logger.info("URL:[ {} ]", new Object[] { request.getRequestURI() });
        logger.info("=====================登陆验证====================");
        //从请求中获取所有的字段信息
        Map<String, String> map = getAllRequestParam(request);
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        //如果获取到的信息为空
        if (userId == null || token == null) {
            RequestDispatcher
                    dispatcher = request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        //如果redis中的tocken不存在
        if(!redisUtils.exists(token)){
            RequestDispatcher
                    dispatcher = request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        String jwt = redisUtils.get(token);
        //如果redis中没有获取到jwt的信息
        if(StringUtils.isEmpty(jwt)){
            RequestDispatcher
                    dispatcher = request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        //将jwt的信息放在Claims中进行授权操作
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
        }
        //如果授权信息为空，
        if (claims == null) {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }

//        Date exp = claims.getExpiration();
//        if (new Date().after(exp)) {
//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("/token/expire");
//            dispatcher.forward(request, response);
//            return ;
//        }

        //如果授权信息中获取的id和传过来的id不一样
        int userIdInt = (int) claims.get("id");
        if (userIdInt !=
                Integer.valueOf(userId)) {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/token/error");
            dispatcher.forward(request, response);
            return false;
        }
        //将信息封装到请求中返回
        request.setAttribute("userName", claims.get("userName"));
        request.setAttribute("account", claims.get("account"));
        request.setAttribute("jwt", jwt);
        return true;
    }

    /**
     * create by: mrmj
     * description: 获取请求参数中所有的信息
     * create time: 2019/11/26 18:01
     */
    public Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}