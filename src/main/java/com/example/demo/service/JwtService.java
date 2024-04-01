package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.UserPrincipal;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String issue(int userId, String email, List<String> roles, int time) {
        return JWT.create()
                .withSubject(userId + "")
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public LoginResponse generateToken(Customer customer) {
        String accessToken = issue(customer.getId(), customer.getEmail(),
                List.of("USER"), 1000 * 60 * 60 * 24);
        String refreshToken = issue(customer.getId(), customer.getEmail(),
                List.of("USER"), 1000 * 60 * 60 * 24 * 30);

        return new LoginResponse(accessToken, refreshToken);
    }


    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
    }

    public UserPrincipal convUserPrincipal(DecodedJWT decodedJWT){
        return UserPrincipal.builder()
                .userId(Integer.parseInt(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .authorities(extractAuthorities(decodedJWT))
                .build();
    }

    public List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT decodedJWT){
        var claims = decodedJWT.getClaim("roles");
        if(claims.isNull() || claims.isMissing()){
            return List.of();
        }
        return claims.asList(SimpleGrantedAuthority.class);
    }

}
