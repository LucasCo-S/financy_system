package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.Date;

import model.transacao.*;

public class TransacaoDAO {
    public static void InsertTransferencia(Connection conexao, Transacao tran){
        String cmd_sql = "INSERT INTO transacao (formaPagamento, dataPagamento, valor, id_contaOrg, id_contaDest) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, tran.getForma_pag());
            state.setTimestamp(2, tran.getData_pag());
            state.setBigDecimal(3, tran.getValor());
            state.setInt(4, tran.getConta_orig());
            state.setInt(5, tran.getConta_dest());

            state.executeUpdate();

            try(ResultSet result = state.getGeneratedKeys()){
                if(result.next()){
                    tran.setId(result.getInt(1));
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao executar transacao: " + e.getMessage());
        }
    }  
    
    public static void InsertBoleto(Connection conexao, Boleto tran){
        String cmd_sql = "INSERT INTO tran_boleto (id_transacao, codBarras, dataVencimento) VALUES (?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, tran.getId());
            state.setString(2, tran.getCod_barras());
            state.setDate(3, Date.valueOf(tran.getData_venc()));

            state.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao fazer transferencia via boleto: " + e.getMessage());
        }
    }

    public static void InsertPix(Connection conexao, Pix tran){
        String cmd_sql = "INSERT INTO tran_pix (id_transacao, chaveOrg, chaveDest) VALUES (?, ?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setInt(1, tran.getId());
            state.setString(2, tran.getChave_orig());
            state.setString(3, tran.getChave_dest());

            state.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao fazer transferencia via pix: " + e.getMessage());
        }
    }

    public static List<Transacao> SelectExtrato(Connection conexao, int id_conta){
        String query_sql = "SELECT * FROM transacao WHERE id_contaOrg = ?";

        List<Transacao> extrato = new ArrayList<>();

        try(PreparedStatement state = conexao.prepareStatement(query_sql)){
            state.setInt(1, id_conta);

            ResultSet result = state.executeQuery(query_sql);

            while (result.next()) {
                Transacao t = new Transacao(
                    result.getString("formaPagamento"), 
                    result.getTimestamp("dataPagamento"), 
                    result.getBigDecimal("valor"),
                    result.getInt("id_contaOrg"),
                    result.getInt("id_contaDest")
                );

                t.setId(result.getInt("id_transacao"));
    
                extrato.add(t);
                result.close();
            }

        }catch (SQLException e){
            System.out.println("Erro ao consultar extrato: " + e.getMessage());
        }

        return extrato;
    }
}
