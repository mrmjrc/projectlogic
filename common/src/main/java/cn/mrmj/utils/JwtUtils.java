package cn.mrmj.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by: mrmj
 * description: json web token 工具
 * create time: 2019/11/18 16:48
 */
@Component
public class JwtUtils {

	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "Ag3Nsr2mDa59MBbE56exXY8";

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<>(2);
		map.put("userId", 1);
		map.put("username", "张三");
		String jwt = createJWT(map, Integer.MAX_VALUE);
		System.out.println(jwt);
		Claims claims = parseJWT(jwt);
		claims.get("userId");
		System.out.println(claims);
	}

	/**
	 * create by: mrmj
	 * description: 由字符串生成加密key
	 * create time: 2019/11/18 16:49
	 */
	public static SecretKey generalKey() {
		byte[] encodedKey = Base64Utils.decodeFromString(JWT_SECRET);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	/**
	 * create by: mrmj
	 * description: 创建jwt,基于Token的WEB后台认证机制
	 * create time: 2019/11/18 16:49
	 */
	public static String createJWT(Map<String, Object> claims, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder().setClaims(claims).setIssuedAt(now).signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	/**
	 * create by: mrmj
	 * description: 解密jwt
	 * create time: 2019/11/18 16:51
	 */
	public static Claims parseJWT(String jwt) {
		SecretKey key = generalKey();
		Claims claims = null;
		try{
			claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		}catch (Exception e) {
		}
		return claims;
	}

}
