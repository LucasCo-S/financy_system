package model.conta;

public class Poupanca extends Conta{
    private String data_rend;
    private double rendimento;

    public Poupanca(String n_conta, double saldo, String data_abert, int id_cliente, String tipo, String data_rend, double rendimento){
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

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

}
