package mlanima.cards.security.jwts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import mlanima.cards.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Getter
@Component
public class JwtProvider {

    private final SecretKey secret = Jwts.SIG.HS256.key().build();

    @Value("${app.jwt.expiration}")
    private int expiration;

    @Value("${app.jwt.issuer}")
    private String issuer;

    private JwtParser getJwtVerifier() {
        return Jwts.parser()
                .requireIssuer(issuer) // Fix here
                .verifyWith(secret)
                .build();
    }

    public String generateToken(Authentication authentication) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .issuer(issuer)
                .expiration(new Date(new Date().getTime() + expiration)) // Fix here
                .signWith(secret, Jwts.SIG.HS256)
                .compact();
    }

    public Boolean validateToken(String token) {
        return getJwtVerifier().isSigned(token);
    }

    public String getUsernameFromToken(String token) {
        return getJwtVerifier().parseSignedClaims(token).getPayload().getSubject();
    }
}