package cn.mrmj.utils;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class GetTokenUtil {

    /**
     * create by: mrmj
     * description: 获取token信息
     * create time: 2019/11/18 16:43
     */
    public static Claims getTokenInfo (HttpServletRequest request){
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        Claims claims = JwtUtils.parseJWT(token);
        return claims;        
    } 
    
}
