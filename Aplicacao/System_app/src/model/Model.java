package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Model {
    private static final String url = "jdbc:mysql://localhost:3306/sistema_bancario";
    private static final String user = "system";
    private static final String pass = "1029";


    private static Connection conexao = null;

    public static Connection connection_db(){
        try {
            if(conexao == null){
                conexao = DriverManager.getConnection(url, user, pass);
                System.out.println("Conexão bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }

        return conexao;
    }

    public static void close_connection(Connection conexao){
        try {
            if(conexao != null){
                conexao.close();
                System.out.println("Conexao fechada com sucesso.");
            }

        } catch (SQLException e){
            System.out.println("Erro ao fechar conexao. Erro: " + e.getMessage());
        }
    }
}
