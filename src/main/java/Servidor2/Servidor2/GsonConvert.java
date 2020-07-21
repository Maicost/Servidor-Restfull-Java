/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import com.google.gson.Gson;
import javax.json.JsonString;

/**
 *
 * @author maico
 */
public class GsonConvert {

    public Usuario convert(String gsonString) {

        Gson gson = new Gson();

        Usuario usuario = gson.fromJson(gsonString, Usuario.class);

        return usuario;
    }
    public String convert(Usuario usuario){
        Gson gson = new Gson();
        
        return gson.toJson(usuario);
    }
}
