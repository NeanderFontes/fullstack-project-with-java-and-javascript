package br.com.register.apiproject.security;

import br.com.register.apiproject.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;
    private static final String SECRET_KEY = "MyK3Yt0T0k3nP4r@S3CuRiTY@Sp3c14L";
    private static final String EMISSOR = "DevNice";

    public static String createToken(UserModel userModel) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String token = Jwts.builder()
                .setSubject(userModel.getName())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        
        return PREFIX + token;
    }

    private static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor) {
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String subjectUsername) {
        return subjectUsername != null && subjectUsername.length() > 0;
    }

    public static UsernamePasswordAuthenticationToken validateToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER);
        token = token.replace(PREFIX, "");

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token);

        String userName = claimsJws.getBody().getSubject();
        String issuer = claimsJws.getBody().getIssuer();
        Date expiration = claimsJws.getBody().getExpiration();

        if (isSubjectValid(userName) && isEmissorValid(issuer) && isExpirationValid(expiration)) {
            return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
        }

        return null;
    }
}
