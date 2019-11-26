package cn.mrmj.filter;

import cn.mrmj.utils.RedisUtils;
import cn.mrmj.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by: mrmj
 * description: 单台机器登录验证
 * create time: 2019/11/26 15:58
 */
public class OnlyLoginHandler extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(OnlyLoginHandler.class);

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
        logger.info("=====================单台机器登陆验证====================");
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        String loginToken = redisUtils.get(userId);
        //如果根据userid在Redis中获取不到值，直接返回错误信息
        if (StringUtil.isEmpty(loginToken) || !loginToken.equals(token)){
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/login/error");
            dispatcher.forward(request, response);
            return false;
        }
        return true;
    }
}