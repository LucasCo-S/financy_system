package model.conta;

import java.time.LocalDate;

public class Conta {
    private int id;
    private String n_conta;
    private double saldo;
    private String data_abert;
    private int id_cliente;
    private String tipo;

    public Conta(String n_conta, double saldo, String data_abert, int id_cliente, String tipo){
        this.n_conta = n_conta;
        this.saldo = saldo;
        this.data_abert = data_abert;
        this.id_cliente = id_cliente;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN_conta() {
        return n_conta;
    }

    public void setN_conta(String n_conta) {
        this.n_conta = n_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getData_abert() {
        return data_abert;
    }

    public void setData_abert(String data_abert) {
        this.data_abert = data_abert;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
