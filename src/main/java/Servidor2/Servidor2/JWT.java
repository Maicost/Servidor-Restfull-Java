/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

/**
 *
 * @author maico
 */
public class JWT {

    public Jws CreateToken(String nome) {
// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String buildJws = Jwts.builder()
                .setSubject("1234567890")
                .signWith(key)
                .claim("name", nome)
                .compact();

        try {
            Jws jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(buildJws);

            return jws;

        } catch (JwtException e) {
            //Ta falho kkk
        }
        return null;
    }

    public boolean VerifyToken(String nome) {
        return false;
        //assert Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("Joe");
    }
}
