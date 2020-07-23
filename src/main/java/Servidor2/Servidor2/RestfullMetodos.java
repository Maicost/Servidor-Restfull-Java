/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws SQLException {
        return "Get";
    }

    //cadastra usuario
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean putJson(String content) throws SQLException, ClassNotFoundException {

        GsonConvert gsoncvt = new GsonConvert();

        Usuario usuario = gsoncvt.convert(content);

        SqlQuery query = new SqlQuery();

        return query.InsertUser(usuario.getNome(), usuario.getSenha(), usuario.getSalt());
    }

    //efetua login  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(String content) throws SQLException, ClassNotFoundException {

        GsonConvert gsoncvt = new GsonConvert();

        Usuario usuario = gsoncvt.convert(content);

        SqlQuery query = new SqlQuery();

        if (query.VerifyCrendentials(usuario.getNome(), usuario.getSenha())) {

            JWT jwt = new JWT();

            usuario.setJwt(jwt.CreateToken(usuario.getNome()));

            System.out.println("" + gsoncvt.convert(usuario));

            return usuario.getJwt().toString();
        }
        return "false";
    }
}
