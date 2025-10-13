package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import model.conta.*;
import model.cliente.*;

public class ContaDAO {
    public static void InsertConta(Connection conexao, Conta account){
        String cmd_sql = "INSERT INTO conta (numero_Conta, saldo, id_Cliente, tipo_conta) VALUES (?, ?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, account.getN_conta());
            state.setBigDecimal(2, account.getSaldo());
            state.setInt(3, account.getId_cliente());
            state.setString(4, account.getTipo());

            state.executeUpdate();

            try(ResultSet result = state.getGeneratedKeys()){
                if(result.next()){
                    account.setId_cliente(result.getInt(1));
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    public static void InsertCorrente(Connection conexao, Corrente account){
        String cmd_sql = "INSERT INTO conta_corrente (id_conta, tarifaMensal) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setBigDecimal(2, account.getTariaMensal());

            state.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Corrente: " + e.getMessage());
        }
    }

    public static void InsertPoupanca(Connection conexao, Poupanca account){
        String cmd_sql = "INSERT INTO conta_poupanca (id_conta, rendimento) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setBigDecimal(2, account.getRendimento());

            state.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Poupanca: " + e.getMessage());
        }
    }

    public static void InsertInvestimento(Connection conexao, Investimento account){
        String cmd_sql = "INSERT INTO conta_investimento (id_conta, tipoInvestimento, valorAplicado) VALUES (?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, account.getId());
            state.setString(2, account.getTipo());
            state.setBigDecimal(3, account.getValor_aplic());

            state.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao gerar conta Investimento: " + e.getMessage());
        }
    }

    public static void UpdateSalario(Connection conexao, Conta account){
        String upd_sql = "UPDATE conta SET saldo = ? WHERE id_conta = ?";

        try(PreparedStatement state = conexao.prepareStatement(upd_sql)){
            state.setBigDecimal(1, account.getSaldo());
            state.setInt(2, account.getId());

            state.executeUpdate();
        } catch (SQLException e){
            System.out.println("Erro ao atualizar salario: " + e.getMessage());
        }
    }

    public static Object[] SelectDados(Connection conexao, Corrente account){
        String query_sql = "SELECT cl.nome, cl.cpf, cl.data_nascimento, co.numero_conta, co.saldo, co.data_abertura, co.tipo_conta, corr.tarifaMensal FROM cliente cl INNER JOIN conta co ON cl.id_cliente = co.id_cliente INNER JOIN conta_corrente corr ON co.id_conta = corr.id_conta WHERE co.id_conta = ?";

        Object[] dados = new Object[2];

        try(PreparedStatement state = conexao.prepareStatement(query_sql)){
            state.setInt(1, account.getId());

            ResultSet result = state.executeQuery();

            if(result.next()){
                Corrente conta = new Corrente(
                    result.getString("numero_conta"), 
                    result.getBigDecimal("saldo"), 
                    result.getTimestamp("data_abertura"), 
                    account.getId_cliente(), 
                    result.getString("tipo_conta"), 
                    result.getBigDecimal("tarifaMensal")
                );

                Cliente cliente = new Cliente(result.getString("nome"), 
                result.getString("cpf"), 
                result.getDate("data_nascimento").toString());

                dados[0] = conta;
                dados[1] = cliente;
            }

            result.close();

        } catch (SQLException e){
            System.out.println("Erro ao consultar dados: " + e.getMessage());
        }

        return dados;
    }

    public static Object[] SelectDados(Connection conexao, Poupanca account){
        String query_sql = "SELECT cl.nome, cl.cpf, cl.data_nascimento, co.numero_conta, co.saldo, co.data_abertura, co.tipo_conta, p.rendimento, p.dataRendimento FROM cliente cl INNER JOIN conta co ON cl.id_cliente = co.id_cliente INNER JOIN conta_poupanca p ON co.id_conta = p.id_conta WHERE co.id_conta = ?";

        Object[] dados = new Object[2];

        try(PreparedStatement state = conexao.prepareStatement(query_sql)){
            state.setInt(1, account.getId());

            ResultSet result = state.executeQuery();

            if(result.next()){
                Poupanca conta = new Poupanca(
                    result.getString("numero_conta"), 
                    result.getBigDecimal("saldo"), 
                    result.getTimestamp("data_abertura"), 
                    account.getId_cliente(),
                    result.getString("tipo_conta"), 
                    result.getTimestamp("dataRendimento").toString(), 
                    result.getBigDecimal("rendimento")
                );

                Cliente cliente = new Cliente(result.getString("nome"), 
                result.getString("cpf"), 
                result.getDate("data_nascimento").toString());

                dados[0] = conta;
                dados[1] = cliente;
            }

            result.close();

        } catch (SQLException e){
            System.out.println("Erro ao consultar dados: " + e.getMessage());
        }

        return dados;
    }

    public static Object[] SelectDados(Connection conexao, Investimento account){
        String query_sql = "SELECT cl.nome, cl.cpf, cl.data_nascimento, co.numero_conta, co.saldo, co.data_abertura, co.tipo_conta, i.tipoInvestimento, i.valorAplicado FROM cliente cl INNER JOIN conta co ON cl.id_cliente = co.id_cliente INNER JOIN conta_investimento i ON co.id_conta = i.id_conta WHERE co.id_conta = ?";

        Object[] dados = new Object[2];

        try(PreparedStatement state = conexao.prepareStatement(query_sql)){
            state.setInt(1, account.getId());

            ResultSet result = state.executeQuery();

            if(result.next()){
                Investimento conta = new Investimento(
                    result.getString("numero_conta"), 
                    result.getBigDecimal("saldo"), 
                    result.getTimestamp("data_abertura"), 
                    account.getId_cliente(), result.getString("tipo_conta"), 
                    result.getString("tipoInvestimento"), 
                    result.getBigDecimal("valorAplicado")
                );

                Cliente cliente = new Cliente(result.getString("nome"), 
                result.getString("cpf"), 
                result.getDate("data_nascimento").toString());

                dados[0] = conta;
                dados[1] = cliente;
            }

            result.close();

        } catch (SQLException e){
            System.out.println("Erro ao consultar dados: " + e.getMessage());
        }

        return dados;
    }
}
