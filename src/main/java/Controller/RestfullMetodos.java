/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CredenciaisAcesso;
import Utils.JWTToken;
import Utils.SqlQuery;
import Model.Login;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.imixs.jwt.JWTException;

/**
 * REST Web Service
 *
 * @author maico
 */
@Path("generic")
@RequestScoped
public class RestfullMetodos {

    /**
     * Creates a new instance of RestfullMetodos
     */
    public RestfullMetodos() {
    }

    @POST
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(String token) throws SQLException, ClassNotFoundException, JWTException {
        Gson gson = new Gson();
        Login login = gson.fromJson(token, Login.class);
        SqlQuery sqlquerry = new SqlQuery();
        CredenciaisAcesso credenciaisAcesso = JWTToken.getInstance().ValidateToken(login.token);
        return sqlquerry.ID(credenciaisAcesso.getNome());
    }

    //cadastra usuario
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean putJson(String content) throws SQLException, ClassNotFoundException {

        Gson gson = new Gson();

        CredenciaisAcesso credenciaisAcesso = gson.fromJson(content, CredenciaisAcesso.class);

        SqlQuery query = new SqlQuery();

        return query.InsertUser(credenciaisAcesso.getNome(),
                credenciaisAcesso.getSenha(), credenciaisAcesso.getSalt());
    }

    //efetua login  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(String content) {
        Gson gson = new Gson();
        System.out.println("Falha 3"+content);
        CredenciaisAcesso credenciaisAcesso = gson.fromJson(content, CredenciaisAcesso.class);
        System.out.println("Falha 3");
        Login login = new Login();

        try {
            SqlQuery query = new SqlQuery();
            if (query.VerifyCrendentials(credenciaisAcesso.getNome(), credenciaisAcesso.getSenha())) {
                
                credenciaisAcesso.setSenha("null");

                login.token = JWTToken.getInstance().GenerateToken(credenciaisAcesso);

                login.success = true;

            } else {
                System.out.println("Else");
                login.success = false;
            }
        } catch (SQLException e) {
            System.out.println("Falha 1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger("Falha 2"+RestfullMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Falha");
        return gson.toJson(login);
    }
}
