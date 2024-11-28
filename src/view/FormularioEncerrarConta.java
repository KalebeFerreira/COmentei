package view;

import controller.BancoController;  // Importa o controlador que gerencia as ações de banco

import javax.swing.*;  // Importa classes para componentes gráficos como botões, rótulos, e campos de texto
import java.awt.event.ActionEvent;  // Importa o evento de ação (quando um botão é clicado)
import java.awt.event.ActionListener;  // Importa o listener para capturar os cliques nos botões

// Classe que representa o formulário para encerrar uma conta
public class FormularioEncerrarConta extends JFrame {
    private BancoController bancoController;  // Declaração do controlador de banco

    // Construtor do formulário para encerrar a conta, recebe o controlador de banco como parâmetro
    public FormularioEncerrarConta(BancoController bancoController) {
        this.bancoController = bancoController;  // Inicializa o controlador de banco

        setTitle("Encerrar Conta");  // Define o título da janela
        setSize(300, 150);  // Define o tamanho da janela (largura 300px, altura 150px)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Define o comportamento ao fechar a janela (apenas fecha a janela)
        setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes
        getContentPane().add(panel);  // Adiciona o painel ao conteúdo da janela

        // Criação dos componentes do formulário
        JLabel lblNumeroConta = new JLabel("Número da Conta:");  // Rótulo para o campo de número da conta
        JTextField txtNumeroConta = new JTextField(15);  // Campo de texto para inserir o número da conta
        JButton btnEncerrar = new JButton("Encerrar");  // Botão para encerrar a conta

        // Adiciona os componentes ao painel
        panel.add(lblNumeroConta);
        panel.add(txtNumeroConta);
        panel.add(btnEncerrar);

        // Ação do botão "Encerrar"
        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o texto inserido no campo de número da conta
                String numeroContaStr = txtNumeroConta.getText().trim();

                // Verifica se o campo de número da conta está vazio
                if (numeroContaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, "O número da conta deve ser preenchido.");
                    return;
                }

                // Tenta converter o número da conta para um inteiro e encerrar a conta
                try {
                    int numeroConta = Integer.parseInt(numeroContaStr);  // Converte o texto para inteiro
                    // Chama o método do controlador para excluir a conta e obtém o resultado
                    String resultado = bancoController.excluirConta(numeroConta);
                    // Exibe o resultado da operação (sucesso ou erro)
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, resultado);
                    dispose();  // Fecha a janela após encerrar a conta
                } catch (NumberFormatException ex) {
                    // Caso o número da conta não seja válido (não seja um inteiro)
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, "O número da conta deve ser um valor válido.");
                }
            }
        });

        setVisible(true);  // Torna a janela visível para o usuário
    }
}
