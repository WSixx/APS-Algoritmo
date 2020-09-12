package com.br.aps;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conector {
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/APS?useTimezone=true&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Conectar ao banco
    public Connection abrir() throws Exception {
        // Registrar o driver
        Class.forName(DRIVER);
        // Capturar a conexão
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        // Retorna a conexao aberta
        return conn;
    }
}