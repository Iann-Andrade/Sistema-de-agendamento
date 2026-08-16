package agenda.security;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore.SecretKeyEntry;
import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import agenda.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;


    private SecretKey obterChave(){
            return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    };

    public String gerarToken(Usuario usuario){
        System.out.println("Secret: " + secret);
        return Jwts.builder()
            .subject(usuario.getId().toString())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+expiration))
            .signWith(obterChave())
            .compact();
        }

    public boolean validarToken(String token){

        try{
            Jwts.parser()
                .verifyWith(obterChave())
                .build()
                .parseSignedClaims(token);

                return true;

        }catch(Exception e){
            return false;
        }

    }

    public String extrairUsuarioId(String token){

        return Jwts.parser()
                    .verifyWith(obterChave())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
    }

    

}
