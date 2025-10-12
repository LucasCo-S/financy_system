package model.tranferencia;

public class Transacao {
    private int id;
    private String forma_pag;
    private String data_pag;
    private double valor;
    private int conta_orig;
    private int conta_dest;

    public Transacao(String forma_pag, String data_pag, double valor, int conta_orig, int conta_dest){
        this.forma_pag = forma_pag;
        this.data_pag = data_pag;
        this.valor = valor;
        this.conta_orig = conta_orig;
        this.conta_dest = conta_dest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForma_pag() {
        return forma_pag;
    }

    public void setForma_pag(String forma_pag) {
        this.forma_pag = forma_pag;
    }

    public String getData_pag() {
        return data_pag;
    }

    public void setData_pag(String data_pag) {
        this.data_pag = data_pag;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getConta_orig() {
        return conta_orig;
    }

    public void setConta_orig(int conta_orig) {
        this.conta_orig = conta_orig;
    }

    public int getConta_dest() {
        return conta_dest;
    }

    public void setConta_dest(int conta_dest) {
        this.conta_dest = conta_dest;
    }

    
}
