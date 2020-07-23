/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import io.jsonwebtoken.Jwt;

/**
 *
 * @author maico
 */
public class Usuario {

    private String nome;
    private String senha;
    private Jwt jwt;
    private String salt;

    public Usuario(String nome, String senha, String salt) {
        this.nome = nome;
        this.senha = senha;
        this.salt = salt;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getSalt() {
        return salt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public Jwt getJwt() {
        return this.jwt;
    }
}
