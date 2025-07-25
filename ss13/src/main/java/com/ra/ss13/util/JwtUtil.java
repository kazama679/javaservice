package com.ra.ss13.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "ra123"; // có thể dùng @Value từ application.properties
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token hết hạn: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token không được hỗ trợ: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Cấu trúc token sai: {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("Sai chữ ký token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token rỗng hoặc null: {}", e.getMessage());
        }
        return false;
    }
}
