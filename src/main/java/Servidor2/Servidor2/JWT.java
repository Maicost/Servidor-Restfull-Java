/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author maico
 */
public class JWT {

    public Jws CreateToken(String nome) throws InvalidKeyException, NoSuchAlgorithmException {
// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String buildJws = Jwts.builder()
                .setSubject("1234567890")
                .signWith(key)
                .claim("name", nome)
                .compact();

        Jws jws;
        jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(buildJws);
        return jws;
    }
}
