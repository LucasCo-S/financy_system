package model.conta;

public class Investimento extends Conta{
    private String tipo_inv;
    private double valor_aplic;

    public Investimento(String n_conta, double saldo, String data_abert, int id_cliente, String tipo, String tipo_inv, double valor_aplic){
        super(n_conta, saldo, data_abert, id_cliente, tipo);
        this.tipo_inv = tipo_inv;
        this.valor_aplic = valor_aplic;
    }

    public String getTipo_inv() {
        return tipo_inv;
    }

    public void setTipo_inv(String tipo_inv) {
        this.tipo_inv = tipo_inv;
    }

    public double getValor_aplic() {
        return valor_aplic;
    }

    public void setValor_aplic(double valor_aplic) {
        this.valor_aplic = valor_aplic;
    }

    
}
