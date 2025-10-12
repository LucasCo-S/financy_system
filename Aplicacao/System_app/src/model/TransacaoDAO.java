package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        } catch (SQLException e){
            System.out.println("Erro ao executar transacao: " + e.getMessage());
        }
    }  
    
    public static void InsertBoleto(Connection conexao, Boleto tran){
        String cmd_sql = "INSERT INTO tran_boleto (codBarras, dataVencimento) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, tran.getCod_barras());
            state.setDate(2, Date.valueOf(tran.getData_venc()));

        } catch (SQLException e){
            System.out.println("Erro ao fazer transferencia via boleto: " + e.getMessage());
        }
    }

    public static void InsertPix(Connection conexao, Pix tran){
        String cmd_sql = "INSERT INTO tran_pix (chaveOrg, chaveDest) VALUES (?, ?)";

        try(PreparedStatement state = conexao.prepareStatement(cmd_sql)){
            state.setString(1, tran.getChave_orig());
            state.setString(2, tran.getChave_dest());

        } catch (SQLException e){
            System.out.println("Erro ao fazer transferencia via pix: " + e.getMessage());
        }
    }
}
