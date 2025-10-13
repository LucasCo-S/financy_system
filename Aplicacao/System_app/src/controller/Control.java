package controller;

import model.ClienteDAO;
import model.ContaDAO;
import model.Model;
import model.TransacaoDAO;
import model.cliente.Cliente;
import model.conta.*;
import model.transacao.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;

import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class Control {
    protected static Connection conexao = null;
    private static Scanner scan = null;

    public static void iniciar(Scanner app_scan){
        conexao = Model.connection_db();
        scan = app_scan;
    }

    public static void encerrar(){
        Model.close_connection(conexao);
    }

    public static void cadastroConta(){
        //Criando objeto cliente
        System.out.println("Digite o seu nome:");
        String nome = scan.nextLine();

        System.out.println("Digite seu CPF:");
        String cpf = scan.nextLine();

        if (cpf.length() > 11) {
            cpf = cpf.substring(0, 11);
        }

        System.out.println("Digite sua data de nascimento:");
        String data_nasc = scan.nextLine();

        String[] aux = data_nasc.split("/");
        data_nasc = aux[2] + "-" + aux[1] + "-" + aux[0];

        Cliente cliente = new Cliente(nome, cpf, data_nasc);

        //Inserindo cliente no banco
        ClienteDAO.InsertCliente(conexao, cliente);

        //Gerando objeto conta
        Random rand = new Random();
        int n = 100000 + rand.nextInt(900000);
        String num = Integer.toString(n);

        BigDecimal saldo = new BigDecimal("0.0");

        System.out.println("Escolha o tipo:\n [1]Corrente | [2]Poupança | [3]Investimento");
        String tipo = scan.nextLine();

        switch (tipo) {
            case "1":
                tipo = "Corrente";
                break;
            case "2":
                tipo = "Poupança";
                break;
            case "3":
                tipo = "Investimento";
                break;
        
            default:
                System.out.println("Erro ao escolher tipo");
                break;
        }

        Conta conta = new Conta(num, saldo, cliente.getId(), tipo);

        //Inserindo conta no banco
        ContaDAO.InsertConta(conexao, conta);

        switch (conta.getTipo()) {
            case "Corrente":
                BigDecimal tarifa = new BigDecimal("0.5");
                Corrente corrente = new Corrente(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), tarifa);

                ContaDAO.InsertCorrente(conexao, corrente);

                break;
            case "Poupança":
                BigDecimal rend = new BigDecimal("0.12");
                Poupanca poupanca = new Poupanca(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), rend);

                ContaDAO.InsertPoupanca(conexao, poupanca);
                
                break;
            case "Investimento":
                String tipo_inv = "Padrao";
                BigDecimal valor = new BigDecimal("0.0");
                Investimento investimento = new Investimento(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), tipo_inv, valor);

                ContaDAO.InsertInvestimento(conexao, investimento);
                
                break;
            default:
                System.out.println("Tipo indeterminado de conta");
                break;
        }
    }

    public static void realizarTransacao(){
        //Criando objeto transacao
        System.out.println("Selecione a forma de pagamento:\n[1]Boleto | [2]Pix");
        String forma = scan.nextLine();

        switch (forma) {
            case "1":
                forma = "Boleto";
                break;
            case "2":
                forma = "Pix";
                break;
        
            default:
                System.out.println("Forma de pagamento inválida");
                return;
        }

        System.out.println("Digite o valor.");
        String str_valor = scan.nextLine();
        BigDecimal valor = new BigDecimal(str_valor);

        System.out.println("Digite id conta de origem");
        String id_org = scan.nextLine();
        int id_origem = Integer.parseInt(id_org);

        System.out.println("Digite id conta de destino");
        String id_dest = scan.nextLine();
        int id_destino = Integer.parseInt(id_dest);

        Timestamp data_pag = new Timestamp(System.currentTimeMillis());

        Transacao transacao = new Transacao(forma, data_pag, valor, id_origem, id_destino);

        //Inserindo objeto no banco
        TransacaoDAO.InsertTransferencia(conexao, transacao);
    }

    public static void consultarExtrato() {
        System.out.println("Digite o id da conta:");
        String str_id = scan.nextLine();
        int id_conta = Integer.parseInt(str_id);

        List<Transacao> extrato = TransacaoDAO.SelectExtrato(conexao, id_conta);

        System.out.println("----- EXTRATO DA CONTA " + id_conta + " -----");
        for (Transacao item : extrato) {
            System.out.println("ID: " + item.getId());
            System.out.println("Forma de pagamento: " + item.getForma_pag());
            System.out.println("Data: " + item.getData_pag());
            System.out.println("Valor: " + item.getValor());
            System.out.println("Conta de origem: " + item.getConta_orig());
            System.out.println("Conta de destino: " + item.getConta_dest());
            System.out.println("-----------------------------------------");
        }

        if (extrato.isEmpty()) {
            System.out.println("Não há transações para esta conta.");
        }
    }
}
