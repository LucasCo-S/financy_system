package model.cliente;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String data_nasc;

    public Cliente(String nome, String cpf, String data_nasc){
        this.nome = nome;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }    

    
}
