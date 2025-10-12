package model.transacao;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Boleto extends Transacao{
    private String cod_barras;
    private String data_venc;

    public Boleto(String forma_pag, Timestamp data_pag, BigDecimal valor, int conta_orig, int conta_dest, String cod_barras, String data_venc){
        super(forma_pag, data_pag, valor, conta_orig, conta_dest);
        this.cod_barras = cod_barras;
        this.data_venc = data_venc;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getData_venc() {
        return data_venc;
    }

    public void setData_venc(String data_venc) {
        this.data_venc = data_venc;
    }

    
}
