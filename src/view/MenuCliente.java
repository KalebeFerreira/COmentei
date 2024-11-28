package view;

import model.Conta;  // Importa a classe Conta do pacote model para interagir com informações bancárias
import utils.DataManager;  // Importa a classe DataManager que gerencia o carregamento e salvamento de dados
import javax.swing.*;  // Importa as classes necessárias para construir a interface gráfica
import java.awt.*;  // Importa as classes relacionadas ao layout e disposição dos componentes
import java.util.List;  // Importa a classe List para manipular listas de objetos

public class MenuCliente extends JFrame {

    // Construtor da classe MenuCliente
    public MenuCliente() {
        setTitle("Banco Malvader - Menu Cliente");  // Define o título da janela
        setSize(400, 300);  // Define o tamanho da janela (largura 400px e altura 300px)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define a ação ao fechar a janela (encerrar o aplicativo)
        setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes da interface
        panel.setLayout(new GridLayout(3, 2, 10, 10));  // Define o layout do painel para organizar os botões em uma grade 3x2 com espaçamento de 10px

        // Criação de botões para cada ação disponível no menu
        JButton saldoButton = new JButton("Saldo");  // Cria o botão "Saldo"
        saldoButton.addActionListener(e -> consultarSaldo());  // Define a ação ao clicar no botão (chama a função consultarSaldo)
        panel.add(saldoButton);  // Adiciona o botão ao painel

        JButton depositoButton = new JButton("Depósito");  // Cria o botão "Depósito"
        depositoButton.addActionListener(e -> realizarDeposito());  // Define a ação ao clicar no botão (chama a função realizarDeposito)
        panel.add(depositoButton);  // Adiciona o botão ao painel

        JButton saqueButton = new JButton("Saque");  // Cria o botão "Saque"
        saqueButton.addActionListener(e -> realizarSaque());  // Define a ação ao clicar no botão (chama a função realizarSaque)
        panel.add(saqueButton);  // Adiciona o botão ao painel

        JButton extratoButton = new JButton("Extrato");  // Cria o botão "Extrato"
        extratoButton.addActionListener(e -> consultarExtrato());  // Define a ação ao clicar no botão (chama a função consultarExtrato)
        panel.add(extratoButton);  // Adiciona o botão ao painel

        JButton limiteButton = new JButton("Consultar Limite");  // Cria o botão "Consultar Limite"
        limiteButton.addActionListener(e -> consultarLimite());  // Define a ação ao clicar no botão (chama a função consultarLimite)
        panel.add(limiteButton);  // Adiciona o botão ao painel

        JButton sairButton = new JButton("Sair");  // Cria o botão "Sair"
        sairButton.addActionListener(e -> {  // Define a ação ao clicar no botão (retorna à tela de login e fecha a janela atual)
            new TelaLogin();  // Cria uma nova instância da tela de login
            dispose();  // Fecha a janela atual (menu do cliente)
        });
        panel.add(sairButton);  // Adiciona o botão ao painel

        add(panel);  // Adiciona o painel contendo os botões à janela principal
        setVisible(true);  // Torna a janela visível para o usuário
    }

    // Função que simula a consulta de saldo da conta
    private void consultarSaldo() {
        criarJanelaInteracao("Consultar Saldo", "Número da Conta:", (numeroConta) -> {  // Cria uma janela para o usuário inserir o número da conta
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");  // Carrega a lista de contas do arquivo "contas.dat"
                Conta conta = contas.stream()  // Filtra as contas para encontrar a que tem o número correspondente
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()  // Pega a primeira conta que corresponder
                        .orElse(null);  // Se não encontrar, retorna null

                if (conta == null) {  // Se a conta não for encontrada, exibe uma mensagem
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {  // Caso a conta seja encontrada, exibe o saldo
                    JOptionPane.showMessageDialog(null,
                            "Número da Conta: " + conta.getNumero() + "\n" +
                                    "Saldo: R$ " + conta.getSaldo());
                }
            } catch (Exception ex) {  // Caso ocorra um erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao consultar saldo: " + ex.getMessage());
                ex.printStackTrace();  // Imprime a stack trace do erro no console
            }
        });
    }

    // Função que simula a realização de depósito
    private void realizarDeposito() {
        criarJanelaInteracaoComValor("Realizar Depósito", (numeroConta, valor) -> {  // Cria uma janela para o usuário inserir número da conta e valor para depósito
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");  // Carrega as contas do arquivo
                Conta conta = contas.stream()  // Filtra a conta com o número fornecido
                        .filter(c -> c.getNumero() == numeroConta)
                        .findFirst()  // Pega a primeira conta encontrada
                        .orElse(null);  // Se não encontrar, retorna null

                if (conta == null) {  // Se a conta não for encontrada, exibe uma mensagem
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {  // Caso a conta seja encontrada, realiza o depósito
                    conta.depositar(valor);  // Chama o método depositar da classe Conta
                    DataManager.salvarContas(contas, "contas.dat");  // Salva as contas atualizadas no arquivo
                    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");  // Exibe uma mensagem de sucesso
                }
            } catch (Exception ex) {  // Caso ocorra um erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao realizar depósito: " + ex.getMessage());
                ex.printStackTrace();  // Imprime a stack trace do erro no console
            }
        });
    }

    // Função que simula a realização de saque
    private void realizarSaque() {
        criarJanelaInteracaoComValor("Realizar Saque", (numeroConta, valor) -> {  // Cria uma janela para o usuário inserir número da conta e valor para saque
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");  // Carrega as contas do arquivo
                Conta conta = contas.stream()  // Filtra a conta com o número fornecido
                        .filter(c -> c.getNumero() == numeroConta)
                        .findFirst()  // Pega a primeira conta encontrada
                        .orElse(null);  // Se não encontrar, retorna null

                if (conta == null) {  // Se a conta não for encontrada, exibe uma mensagem
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else if (conta.getSaldo() < valor) {  // Se o saldo for insuficiente, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                } else {  // Caso a conta tenha saldo suficiente, realiza o saque
                    conta.sacar(valor);  // Chama o método sacar da classe Conta
                    DataManager.salvarContas(contas, "contas.dat");  // Salva as contas atualizadas no arquivo
                    JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");  // Exibe uma mensagem de sucesso
                }
            } catch (Exception ex) {  // Caso ocorra um erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao realizar saque: " + ex.getMessage());
                ex.printStackTrace();  // Imprime a stack trace do erro no console
            }
        });
    }

    // Função que simula a consulta de extrato
    private void consultarExtrato() {
        criarJanelaInteracao("Consultar Extrato", "Número da Conta:", (numeroConta) -> {  // Cria uma janela para o usuário inserir o número da conta
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");  // Carrega as contas do arquivo
                Conta conta = contas.stream()  // Filtra a conta com o número fornecido
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()  // Pega a primeira conta encontrada
                        .orElse(null);  // Se não encontrar, retorna null

                if (conta == null) {  // Se a conta não for encontrada, exibe uma mensagem
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {  // Caso a conta seja encontrada, exibe o extrato
                    StringBuilder extrato = new StringBuilder("Extrato da Conta " + conta.getNumero() + ":\n\n");
                    List<String> transacoes = conta.getExtrato();  // Obtém o extrato da conta

                    if (transacoes.isEmpty()) {  // Se não houver transações, informa que o extrato está vazio
                        extrato.append("Nenhuma transação encontrada.");
                    } else {
                        for (String transacao : transacoes) {  // Exibe cada transação no extrato
                            extrato.append(transacao).append("\n");
                        }
                    }

                    JOptionPane.showMessageDialog(null, extrato.toString());  // Exibe o extrato na tela
                }
            } catch (Exception ex) {  // Caso ocorra um erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + ex.getMessage());
                ex.printStackTrace();  // Imprime a stack trace do erro no console
            }
        });
    }

    // Função que simula a consulta de limite
    private void consultarLimite() {
        criarJanelaInteracao("Consultar Limite", "Número da Conta:", (numeroConta) -> {  // Cria uma janela para o usuário inserir o número da conta
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");  // Carrega as contas do arquivo
                Conta conta = contas.stream()  // Filtra a conta com o número fornecido
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()  // Pega a primeira conta encontrada
                        .orElse(null);  // Se não encontrar, retorna null

                if (conta == null) {  // Se a conta não for encontrada, exibe uma mensagem
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {  // Caso a conta seja encontrada, exibe o limite
                    JOptionPane.showMessageDialog(null,
                            "Número da Conta: " + conta.getNumero() + "\n" +
                                    "Limite: R$ " + conta.getLimite());
                }
            } catch (Exception ex) {  // Caso ocorra um erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao consultar limite: " + ex.getMessage());
                ex.printStackTrace();  // Imprime a stack trace do erro no console
            }
        });
    }

    // Função auxiliar para criar janelas de interação com uma única entrada (número da conta)
    private void criarJanelaInteracao(String titulo, String label, InteracaoSimples acao) {
        JFrame frame = new JFrame(titulo);  // Cria uma nova janela com o título especificado
        frame.setSize(400, 200);  // Define o tamanho da janela
        frame.setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel(new FlowLayout());  // Cria um painel com um layout simples (fluxo)
        JLabel lblNumeroConta = new JLabel(label);  // Cria um rótulo (label) para indicar o que o usuário deve preencher
        JTextField tfNumeroConta = new JTextField(15);  // Cria um campo de texto para o número da conta
        JButton btnAcao = new JButton("Confirmar");  // Cria um botão "Confirmar" para acionar a ação

        panel.add(lblNumeroConta);  // Adiciona o rótulo ao painel
        panel.add(tfNumeroConta);  // Adiciona o campo de texto ao painel
        panel.add(btnAcao);  // Adiciona o botão ao painel

        frame.add(panel);  // Adiciona o painel à janela
        frame.setVisible(true);  // Torna a janela visível

        btnAcao.addActionListener(e -> {  // Define a ação ao clicar no botão "Confirmar"
            String numeroConta = tfNumeroConta.getText().trim();  // Obtém o número da conta digitado pelo usuário
            if (numeroConta.isEmpty()) {  // Se o campo estiver vazio, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(frame, "O número da conta deve ser preenchido.");
            } else {  // Caso contrário, executa a ação fornecida
                acao.executar(numeroConta);
            }
        });
    }

    // Função auxiliar para criar janelas de interação com número da conta e valor (como depósitos e saques)
    private void criarJanelaInteracaoComValor(String titulo, InteracaoComValor acao) {
        JFrame frame = new JFrame(titulo);  // Cria uma nova janela com o título especificado
        frame.setSize(400, 300);  // Define o tamanho da janela
        frame.setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel(new FlowLayout());  // Cria um painel com um layout simples (fluxo)
        JLabel lblNumeroConta = new JLabel("Número da Conta:");  // Cria um rótulo para o número da conta
        JTextField tfNumeroConta = new JTextField(15);  // Cria um campo de texto para o número da conta
        JLabel lblValor = new JLabel("Valor:");  // Cria um rótulo para o valor
        JTextField tfValor = new JTextField(15);  // Cria um campo de texto para o valor
        JButton btnAcao = new JButton("Confirmar");  // Cria o botão "Confirmar"

        panel.add(lblNumeroConta);  // Adiciona o rótulo do número da conta ao painel
        panel.add(tfNumeroConta);  // Adiciona o campo de texto do número da conta ao painel
        panel.add(lblValor);  // Adiciona o rótulo do valor ao painel
        panel.add(tfValor);  // Adiciona o campo de texto do valor ao painel
        panel.add(btnAcao);  // Adiciona o botão ao painel

        frame.add(panel);  // Adiciona o painel à janela
        frame.setVisible(true);  // Torna a janela visível

        btnAcao.addActionListener(e -> {  // Define a ação ao clicar no botão "Confirmar"
            try {
                int numeroConta = Integer.parseInt(tfNumeroConta.getText().trim());  // Obtém e converte o número da conta para inteiro
                double valor = Double.parseDouble(tfValor.getText().trim());  // Obtém e converte o valor para double
                acao.executar(numeroConta, valor);  // Executa a ação fornecida
            } catch (NumberFormatException ex) {  // Se ocorrer um erro de formatação (entrada inválida), exibe uma mensagem de erro
                JOptionPane.showMessageDialog(frame, "Número da conta e valor devem ser válidos.");
            }
        });
    }

    // Interface funcional para interações simples (como consultar saldo ou extrato)
    @FunctionalInterface
    private interface InteracaoSimples {
        void executar(String numeroConta);  // Método para executar a ação fornecida com o número da conta
    }

    // Interface funcional para interações que envolvem um valor (como depósitos ou saques)
    @FunctionalInterface
    private interface InteracaoComValor {
        void executar(int numeroConta, double valor);  // Método para executar a ação fornecida com o número da conta e o valor
    }

    // Função principal que inicializa a janela do menu cliente
    public static void main(String[] args) {
        new MenuCliente();  // Cria uma nova instância da classe MenuCliente, que exibe o menu ao usuário
    }
}
