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

import java.util.InputMismatchException;
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
        scan.close();
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

        //Criando contas especificas
        switch (conta.getTipo()) {
            case "Corrente":
                BigDecimal tarifa = new BigDecimal("0.5");
                Corrente corrente = new Corrente(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), tarifa);

                corrente.setId(conta.getId());

                ContaDAO.InsertCorrente(conexao, corrente);

                break;
            case "Poupança":
                BigDecimal rend = new BigDecimal("0.12");
                Poupanca poupanca = new Poupanca(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), rend);

                poupanca.setId(conta.getId());

                ContaDAO.InsertPoupanca(conexao, poupanca);
                
                break;
            case "Investimento":
                String tipo_inv = "Padrao";
                BigDecimal valor = new BigDecimal("0.0");
                Investimento investimento = new Investimento(conta.getN_conta(), conta.getSaldo(), conta.getId_cliente(), conta.getTipo(), tipo_inv, valor);

                investimento.setId(conta.getId());

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

    public static void depositarSaldo(){
        System.out.println("Digite o id da sua conta:");
        String str_id = scan.nextLine();
        int id = Integer.parseInt(str_id);

        System.out.println("Qual valor deseja depositar?");
        String str_valor = scan.nextLine();
        BigDecimal valor = new BigDecimal(str_valor);

        Corrente corrente = new Corrente(id);
        Object[] dados = ContaDAO.SelectDados(conexao, corrente);

        Corrente novo_corr = (Corrente) dados[0];
        
        valor = valor.add(novo_corr.getSaldo());

        Conta conta = new Conta(valor, id);

        ContaDAO.UpdateSalario(conexao, conta);

        System.out.println("Valor depositado com sucesso.");
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

    public static void apresentarDados() {
    System.out.println("Digite o id da sua conta:");
    String str_id = scan.nextLine();
    int id = Integer.parseInt(str_id);

    Conta conta = new Conta(id);

    // Descobre o tipo de conta (corrente, poupanca ou investimento)
    String tipoConta = ContaDAO.SelectTipoConta(conexao, conta);
    Object[] dados = null;

    if (tipoConta == null) {
        System.out.println("Conta não encontrada.");
        return;
    }

    // Switch baseado apenas no tipo da conta
    switch (tipoConta.toLowerCase()) {
        case "corrente" -> {
            Corrente c = new Corrente(id);
            dados = ContaDAO.SelectDados(conexao, c);
        }
        case "poupanca" -> {
            Poupanca p = new Poupanca(id);
            dados = ContaDAO.SelectDados(conexao, p);
        }
        case "investimento" -> {
            Investimento i = new Investimento(id);
            dados = ContaDAO.SelectDados(conexao, i);
        }
        default -> {
            System.out.println("Tipo de conta desconhecido: " + tipoConta);
            return;
        }
    }

    // Validação de segurança
    if (dados == null || dados[0] == null || dados[1] == null) {
        System.out.println("Nenhum dado encontrado para essa conta.");
        return;
    }

    // Extrai conta e cliente
    Conta contaRetornada = (Conta) dados[0];
    Cliente cliente = (Cliente) dados[1];

    System.out.println("===== Dados do Cliente =====");
    System.out.println("Nome: " + cliente.getNome());
    System.out.println("CPF: " + cliente.getCpf());
    System.out.println("Data de Nascimento: " + cliente.getData_nasc());

    System.out.println("\n===== Dados da Conta =====");
    System.out.println("Número da Conta: " + contaRetornada.getN_conta());
    System.out.println("Saldo: R$ " + contaRetornada.getSaldo());
    System.out.println("Data de Abertura: " + contaRetornada.getData_abert());
    System.out.println("Tipo de Conta: " + contaRetornada.getTipo());

    // Exibe atributos específicos conforme o tipo
    switch (tipoConta.toLowerCase()) {
        case "corrente" -> {
            Corrente c = (Corrente) contaRetornada;
            System.out.println("Tarifa Mensal: R$ " + c.getTariaMensal());
        }
        case "poupanca" -> {
            Poupanca p = (Poupanca) contaRetornada;
            System.out.println("Data de Rendimento: " + p.getData_rend());
            System.out.println("Rendimento: " + p.getRendimento());
        }
        case "investimento" -> {
            Investimento i = (Investimento) contaRetornada;
            System.out.println("Tipo de Investimento: " + i.getTipo_inv());
            System.out.println("Valor Aplicado: R$ " + i.getValor_aplic());
        }
        default -> System.out.println("Tipo de conta não reconhecido.");
    }
}



    public static boolean rodarMenu() {
        int option = 0;
        boolean erro = true;

        while (erro) {
            try {
                System.out.print("""
                        Que operação deseja realizar? Digite apenas o número.
                        
                        1 - Cadastrar conta.
                        2 - Realizar transação.
                        3 - Consultar extrato.
                        4 - Depositar saldo.
                        5 - Apresentar dados.
                        0 - Encerrar programa.\s
                         R:\s""");

                option = scan.nextInt();
                scan.nextLine(); // Limpa o \n do buffer que o .nextInt() não lê

                if (option < 0 || option > 5) {
                    throw new InputMismatchException("\nPor favor, digite uma opção válida");
                } else erro = false;
            }
            catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                limparTela();
            }
        }

        switch (option) {

            case 1 -> {
                cadastroConta();
                limparTela();
                return true;
            }

            case 2 -> {
                realizarTransacao();
                limparTela();
                return true;
            }

            case 3 -> {
                consultarExtrato();
                limparTela();
                return true;
            }

            case 4 -> {
                depositarSaldo();
                limparTela();
                return true;
            }

            case 5 ->{
                apresentarDados();
                limparTela();
                return true;
            }

            case 0 -> {
                System.out.println();
                System.out.println("Encerrando programa...");
                return false;
            }

            default -> {
                return true;
            }

        }

    }

    public static void limparTela() {
        System.out.println("\nAperte ENTER para continuar...");
        scan.nextLine();

        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

        } catch (Exception e1) {
            // Se falhar, tenta limpar via códigos ANSI
            try {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (Exception e2) {
                // Se tudo isso der errado o jeito é imprimir 50 linhas mesmo
                for (int i = 0; i < 50; i++) System.out.println();
            }
        }
    }

}
