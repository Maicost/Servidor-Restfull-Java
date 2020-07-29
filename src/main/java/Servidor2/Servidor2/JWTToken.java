package Servidor2.Servidor2;

import org.imixs.jwt.*;

import javax.crypto.SecretKey;

import com.google.gson.Gson;

public class JWTToken {
    public static JWTToken instance;
    SecretKey secretKey = HMAC.createKey("HmacSHA256", "chave_secreta".getBytes());

    public static JWTToken getInstance() {
        if (instance == null)
            instance = new JWTToken();
        return instance;
    }

    public String GenerateToken(Usuario pessoa) {
        try {
            Gson json = new Gson();
            String payload = json.toJson(pessoa);
            JWTBuilder builder = new JWTBuilder().setKey(secretKey).setPayload(payload);
            return builder.getToken();
        } catch (JWTException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public Usuario ValidateToken(String token) throws JWTException {
        String payload = new JWTParser().setKey(secretKey).setToken(token).verify().getPayload();
        Gson json = new Gson();
        Usuario usuario = json.fromJson(payload, Usuario.class);
        return usuario;
    }

}