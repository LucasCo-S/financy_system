package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

import model.cliente.*;

public class ClienteDAO {
    public static void InsertCliente(Connection conexao, Cliente client){
        String cmd_sql = "INSERT INTO cliente (nome, cpf, data_Nascimento) VALUES (?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, client.getNome());
            state.setString(2, client.getCpf());
            state.setDate(3, Date.valueOf(client.getData_nasc()));

            state.executeUpdate();

            try(ResultSet result = state.getGeneratedKeys()){
                if(result.next()){
                    client.setId(result.getInt(1));
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }
}
