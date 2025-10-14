package model.conta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Investimento extends Conta{
    private String tipo_inv;
    private BigDecimal valor_aplic;

    public Investimento(String n_conta, BigDecimal saldo, Timestamp data_abert, int id_cliente, String tipo, String tipo_inv, BigDecimal valor_aplic){
        super(n_conta, saldo, data_abert, id_cliente, tipo);
        this.tipo_inv = tipo_inv;
        this.valor_aplic = valor_aplic;
    }

    public Investimento(String n_conta, BigDecimal saldo, int id_cliente, String tipo, String tipo_inv, BigDecimal valor_aplic){
        super(n_conta, saldo, id_cliente, tipo);
        this.tipo_inv = tipo_inv;
        this.valor_aplic = valor_aplic;
    }

    public Investimento(int id){
        super(id);
    }

    public String getTipo_inv() {
        return tipo_inv;
    }

    public void setTipo_inv(String tipo_inv) {
        this.tipo_inv = tipo_inv;
    }

    public BigDecimal getValor_aplic() {
        return valor_aplic;
    }

    public void setValor_aplic(BigDecimal valor_aplic) {
        this.valor_aplic = valor_aplic;
    }
}
