package model.conta;

public class Corrente extends Conta{
    private double tariaMensal;

    public Corrente(String n_conta, double saldo, String data_abert, int id_cliente, String tipo,double tarifaMensal){
        super(n_conta, saldo, data_abert, id_cliente, tipo);
        this.tariaMensal = tarifaMensal;
    }

    public double getTariaMensal() {
        return tariaMensal;
    }

    public void setTariaMensal(double tariaMensal) {
        this.tariaMensal = tariaMensal;
    }
}
