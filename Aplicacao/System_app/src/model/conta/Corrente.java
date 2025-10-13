package model.conta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Corrente extends Conta{
    private BigDecimal tariaMensal;

    public Corrente(String n_conta, BigDecimal saldo, Timestamp data_abert, int id_cliente, String tipo, BigDecimal tarifaMensal){
        super(n_conta, saldo, data_abert, id_cliente, tipo);
        this.tariaMensal = tarifaMensal;
    }

    public Corrente(String n_conta, BigDecimal saldo, int id_cliente, String tipo, BigDecimal tarifaMensal){
        super(n_conta, saldo, id_cliente, tipo);
        this.tariaMensal = tarifaMensal;
    }

    public BigDecimal getTariaMensal() {
        return tariaMensal;
    }

    public void setTariaMensal(BigDecimal tariaMensal) {
        this.tariaMensal = tariaMensal;
    }
}
