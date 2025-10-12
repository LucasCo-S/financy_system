package model.transacao;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transacao {
    private int id;
    private String forma_pag;
    private Timestamp data_pag;
    private BigDecimal valor;
    private int conta_orig;
    private int conta_dest;

    public Transacao(String forma_pag, Timestamp data_pag, BigDecimal valor, int conta_orig, int conta_dest){
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

    public Timestamp getData_pag() {
        return data_pag;
    }

    public void setData_pag(Timestamp data_pag) {
        this.data_pag = data_pag;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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
