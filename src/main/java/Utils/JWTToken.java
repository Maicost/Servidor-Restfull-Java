package Utils;

import Model.CredenciaisAcesso;
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

    public String GenerateToken(CredenciaisAcesso pessoa) {
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

    public CredenciaisAcesso ValidateToken(String token) throws JWTException {
        String payload = new JWTParser().setKey(secretKey).setToken(token).verify().getPayload();
        Gson json = new Gson();
        CredenciaisAcesso usuario = json.fromJson(payload, CredenciaisAcesso.class);
        return usuario;
    }

}