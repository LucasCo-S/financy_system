package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import model.conta.*;

public class ContaDAO {
    public void InsertConta(Connection conexao,Conta account){
        String cmd_sql = "INSERT INTO conta (numero_Conta, saldo, id_Cliente, tipo_conta) VALUES (?, ?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, account.getN_conta());
            state.setBigDecimal(2, account.getSaldo());
            state.setInt(3, account.getId_cliente());
            state.setString(4, account.getTipo());

            try(ResultSet result = state.getGeneratedKeys()){
                if(result.next()){
                    account.setId_cliente(result.getInt(1));
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    public void InsertCorrente(Connection conexao, Corrente account){
        String cmd_sql = "INSERT INTO conta_corrente (id_conta, tarifaMensal) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setBigDecimal(2, account.getTariaMensal());

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Corrente: " + e.getMessage());
        }
    }

    public void InsertPoupanca(Connection conexao, Poupanca account){
        String cmd_sql = "INSERT INTO conta_poupanca (id_conta, rendimento) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setBigDecimal(2, account.getRendimento());

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Poupanca: " + e.getMessage());
        }
    }

    public void InsertInvestimento(Connection conexao, Investimento account){
        String cmd_sql = "INSERT INTO conta_investimento (id_conta, tipoInvestimento, valorAplicado) VALUES (?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setString(2, account.getTipo());
            state.setBigDecimal(3, account.getValor_aplic());

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Investimento: " + e.getMessage());
        }
    }
}
