package view;

import controller.BancoController;  // Importa o controlador que gerencia as ações de banco

import javax.swing.*;  // Importa classes para componentes gráficos como botões e janelas
import java.awt.event.ActionEvent;  // Importa o evento de ação (quando um botão é clicado)
import java.awt.event.ActionListener;  // Importa o listener para capturar os cliques nos botões

// Classe que representa o menu principal do funcionário
public class MenuFuncionario extends JFrame {
    private BancoController bancoController;  // Declaração do controlador de banco

    // Construtor do menu do funcionário, recebe o controlador de banco como parâmetro
    public MenuFuncionario(BancoController bancoController) {
        this.bancoController = bancoController;  // Inicializa o controlador de banco

        setTitle("Menu Funcionário");  // Define o título da janela
        setSize(400, 300);  // Define o tamanho da janela (largura 400px, altura 300px)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define o comportamento ao fechar a janela (encerra o programa)
        setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes
        getContentPane().add(panel);  // Adiciona o painel ao conteúdo da janela

        // Criação dos botões que irão aparecer no menu
        JButton btnAbrirConta = new JButton("Abrir Conta");  // Botão para abrir conta
        JButton btnEncerrarConta = new JButton("Encerrar Conta");  // Botão para encerrar conta
        JButton btnConsultarConta = new JButton("Consultar Conta");  // Botão para consultar conta
        JButton btnAlterarDados = new JButton("Alterar Dados");  // Botão para alterar dados
        JButton btnCadastroFuncionario = new JButton("Cadastro Funcionario");  // Botão para cadastrar funcionário
        JButton btnGerarRelatorios = new JButton("Gerar Relatórios");  // Botão para gerar relatórios
        JButton btnSair = new JButton("Sair");  // Botão para sair do menu

        // Adiciona os botões ao painel
        panel.add(btnAbrirConta);
        panel.add(btnEncerrarConta);
        panel.add(btnConsultarConta);
        panel.add(btnAlterarDados);
        panel.add(btnCadastroFuncionario);
        panel.add(btnGerarRelatorios);
        panel.add(btnSair);

        // Ação do botão "Abrir Conta"
        btnAbrirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConta();  // Chama o método para abrir conta
            }
        });

        // Ação do botão "Encerrar Conta"
        btnEncerrarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarConta();  // Chama o método para encerrar conta
            }
        });

        // Ação do botão "Consultar Conta"
        btnConsultarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarConta();  // Chama o método para consultar conta
            }
        });

        // Ação do botão "Alterar Dados"
        btnAlterarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarDados();  // Chama o método para alterar dados
            }
        });

        // Ação do botão "Cadastro Funcionario"
        btnCadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();  // Chama o método para cadastrar funcionário
            }
        });

        // Ação do botão "Gerar Relatórios"
        btnGerarRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorios();  // Chama o método para gerar relatórios (não implementado ainda)
            }
        });

        // Ação do botão "Sair"
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha o menu do funcionário
            }
        });

        setVisible(true);  // Torna a janela visível para o usuário
    }

    // Método que será chamado quando o botão "Abrir Conta" for clicado
    public void abrirConta() {
        new FormularioAbrirConta(bancoController);  // Cria uma nova janela para abrir conta
    }

    // Método que será chamado quando o botão "Encerrar Conta" for clicado
    public void encerrarConta() {
        new FormularioEncerrarConta(bancoController);  // Cria uma nova janela para encerrar conta
    }

    // Método que será chamado quando o botão "Consultar Conta" for clicado
    public void consultarConta() {
        new FormularioConsultarConta(bancoController);  // Cria uma nova janela para consultar conta
    }

    // Método que será chamado quando o botão "Alterar Dados" for clicado
    public void alterarDados() {
        new FormularioAlterarDados(bancoController);  // Cria uma nova janela para alterar dados
    }

    // Método que será chamado quando o botão "Cadastro Funcionario" for clicado
    public void cadastrarFuncionario() {
        new FormularioCadastroFuncionario(bancoController);  // Cria uma nova janela para cadastro de funcionário
    }

    // Método que será chamado quando o botão "Gerar Relatórios" for clicado
    public void gerarRelatorios() {
        JOptionPane.showMessageDialog(this, "Gerar Relatórios não implementado.");  // Exibe uma mensagem dizendo que a funcionalidade não está implementada
    }
}
