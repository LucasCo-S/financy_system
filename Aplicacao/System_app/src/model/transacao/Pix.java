package model.transacao;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pix extends Transacao{
    private String chave_orig;
    private String chave_dest;

    public Pix(String forma_pag, Timestamp data_pag, BigDecimal valor, int conta_orig, int conta_dest, String chave_orig, String chave_dest){
        super(forma_pag, data_pag, valor, conta_orig, conta_dest);
        this.chave_orig = chave_orig;
        this.chave_dest = chave_dest;
    }

    public String getChave_orig(){
        return chave_orig;
    }

    public void setChave_orig(String chave_orig){
        this.chave_orig = chave_orig;
    }

    public String getChave_dest(){
        return chave_dest;
    }

    public void setChave_dest(String chave_dest){
        this.chave_dest = chave_dest;
    }
}
