package ua.com.deluxedostavka.security.tokenUtils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.com.deluxedostavka.security.dto.TokenUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TokenTools {

//    private final static Logger LOGGER = Logger.getLogger(TokenTools.class);


    @Value("${token.expiration}")
    private Integer expirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    public String tokenGenerator(String login,String role){
//        LOGGER.info("Creating time slot for token");
        Date createdAt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.SECOND,expirationTime);
        Date validTo = calendar.getTime();
        String token = null;
//        LOGGER.info("Creating token");
        try {
              token = Jwts.builder().setIssuer("OSCE Manager")
                    .claim("login",login)
                    .claim("role",role)
                    .setIssuedAt(createdAt)
                    .setExpiration(validTo)
                    .signWith(SignatureAlgorithm.HS256,getTokenSecret())
                    .compact();
        } catch (Exception e) {

        }
        return  token;
    }

    public boolean isTokenValid(String token){
        boolean valid  = false;
        try{
            Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token);
            valid = true;
        }catch (Exception e){
//            LOGGER.error("Token isn't valid");
        }
        return valid;
    }

    public boolean isTokenExpired(String authToken) {
        boolean expired = true;
        try {
            Date date = Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(authToken).getBody().getExpiration();
            expired = false;
        } catch (ExpiredJwtException e) {
            System.out.println("Token is expired " );
        } catch (Exception e) {
//            LOGGER.error("Token validation failed");
        }

        return (expired);
    }

    public UserDetails getUserByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token).getBody();

//        LOGGER.info("Get roles from token");
        String role = (String) claims.get("role");
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(authority);
//        LOGGER.info("Get other info from token");
        String login = (String) claims.get("login");

        return new TokenUser(login,roles);
    }



    private byte[] getTokenSecret() {
        return tokenSecret.getBytes();
    }

}
