/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor2.Servidor2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maico
 */
public class SqlQuery {

    Connection conn;

    public SqlQuery() throws ClassNotFoundException, SQLException {
        SqliteConnection bancoDados = new SqliteConnection();
        this.conn = bancoDados.connect();
    }

    public boolean VerifyCrendentials(String nome, String senha) throws SQLException {

        String sql = "SELECT * FROM Usuarios WHERE Nome LIKE '" + nome
                + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return PasswordUtils.verifyUserPassword(senha, rs.getString("Senha"),
                rs.getString("Salt"));
    }

    public boolean VerifyUsername(String nome) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE Nome LIKE '" + nome + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);  //execute query gera retorno
        if (rs.isClosed()) //quando não encontra resultado result set vem fechado
        {
            return false;
        } else {
            return nome.contentEquals(rs.getString("Nome"));
        }
    }
    
    public String ID(String nome) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE Nome LIKE '" + nome + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);  //execute query gera retorno
        return rs.getString("ID");
    }

    public boolean InsertUser(String nome, String senha, String salt) throws SQLException {
        if (!VerifyUsername(nome)) {

            String sql = "INSERT INTO Usuarios (Nome, Senha, Salt) VALUES('" + nome + "', '" + senha + "', '" + salt + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql); //execute update não gera retorno
            conn.close();
            return true;
        } else {
            conn.close();
            return false;
        }
    }
}
