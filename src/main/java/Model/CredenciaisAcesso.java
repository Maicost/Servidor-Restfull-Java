/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author maico
 */
public class CredenciaisAcesso {

    private String nome;
    private String senha;
    private String salt;

    public CredenciaisAcesso(String nome, String senha, String salt) {
        this.nome = nome;
        this.senha = senha;
        this.salt = salt;
    }

    public CredenciaisAcesso(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public CredenciaisAcesso() {
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
    
    public void setSenha(String senha){
        this.senha = senha;
    }
}
