package model.conta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Poupanca extends Conta{
    private String data_rend;
    private BigDecimal rendimento;

    public Poupanca(String n_conta, BigDecimal saldo, Timestamp data_abert, int id_cliente, String tipo, String data_rend, BigDecimal rendimento){
        super(n_conta, saldo, data_abert, id_cliente, tipo);
        this.data_rend = data_rend;
        this.rendimento = rendimento;
    }

    public String getData_rend() {
        return data_rend;
    }

    public void setData_rend(String data_rend) {
        this.data_rend = data_rend;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public void setRendimento(BigDecimal rendimento) {
        this.rendimento = rendimento;
    }

}
