/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
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

    @Context
    private UriInfo context;

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
        System.out.println("Este é o token: "+login.token+":Final");
        SqlQuery sqlquerry = new SqlQuery();
        Usuario usuario = JWTToken.getInstance().ValidateToken(login.token);
        return sqlquerry.ID(usuario.getNome());
    }

    //cadastra usuario
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean putJson(String content) throws SQLException, ClassNotFoundException {

        Gson gson = new Gson();

        Usuario usuario = gson.fromJson(content, Usuario.class);

        SqlQuery query = new SqlQuery();

        return query.InsertUser(usuario.getNome(), usuario.getSenha(), usuario.getSalt());
    }

    //efetua login  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(String content) throws SQLException, ClassNotFoundException {
        Gson gson = new Gson();
        
        System.out.println("\tPost\n");
        
        Usuario usuario = gson.fromJson(content, Usuario.class);

        SqlQuery query = new SqlQuery();

        Login login = new Login();

        if (query.VerifyCrendentials(usuario.getNome(), usuario.getSenha())) {

            usuario.setSenha("null");

            login.token = JWTToken.getInstance().GenerateToken(usuario);

            login.success = true;

            usuario.setJwt(gson.toJson(login));
        } else {
            login.success = false;
        }
        return gson.toJson(login);
    }
}
