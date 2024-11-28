package view;

import controller.BancoController;  // Importa o controlador de banco
import javax.swing.*;  // Importa as classes de interface gráfica
import java.awt.event.ActionEvent;  // Importa o evento de ação
import java.awt.event.ActionListener;  // Importa o listener de ação

public class FormularioAbrirConta extends JFrame {  // Define a classe de formulário para abrir uma conta, que herda de JFrame
    private BancoController bancoController;  // Declaração do controlador de banco

    // Construtor que recebe o controlador como parâmetro
    public FormularioAbrirConta(BancoController bancoController) {
        this.bancoController = bancoController;  // Atribui o objeto bancoController à variável de instância

        setTitle("Abrir Conta");  // Define o título da janela
        setSize(400, 300);  // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Define a operação de fechamento da janela
        setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes
        getContentPane().add(panel);  // Adiciona o painel ao conteúdo da janela

        // Criação dos componentes do formulário
        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);  // Campo de texto para o número da conta
        JLabel labelNome = new JLabel("Nome:");
        JTextField tfNome = new JTextField(15);  // Campo de texto para o nome
        JLabel labelCpf = new JLabel("CPF:");
        JTextField tfCpf = new JTextField(15);  // Campo de texto para o CPF
        JLabel labelTipoConta = new JLabel("Tipo de Conta:");
        JComboBox<String> comboTipoConta = new JComboBox<>(new String[]{"Corrente", "Poupança"});  // Combo box para selecionar o tipo de conta

        JButton btnAbrir = new JButton("Abrir Conta");  // Botão para abrir a conta

        // Adiciona os componentes ao painel
        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(labelNome);
        panel.add(tfNome);
        panel.add(labelCpf);
        panel.add(tfCpf);
        panel.add(labelTipoConta);
        panel.add(comboTipoConta);
        panel.add(btnAbrir);

        // Ação do botão Abrir Conta
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Tenta criar a conta a partir dos dados inseridos
                    int numeroConta = Integer.parseInt(tfNumeroConta.getText());
                    String nome = tfNome.getText();
                    String cpf = tfCpf.getText();
                    String tipoConta = (String) comboTipoConta.getSelectedItem();

                    bancoController.criarConta(numeroConta, nome, cpf, tipoConta);  // Cria a conta através do controlador

                    JOptionPane.showMessageDialog(FormularioAbrirConta.this, "Conta criada com sucesso!");  // Exibe mensagem de sucesso
                    dispose();  // Fecha a janela
                } catch (NumberFormatException ex) {  // Erro se o número da conta não for válido
                    JOptionPane.showMessageDialog(FormularioAbrirConta.this, "Número da conta deve ser um número válido.");
                } catch (IllegalArgumentException ex) {  // Erro se algum campo estiver vazio ou inválido
                    JOptionPane.showMessageDialog(FormularioAbrirConta.this, ex.getMessage());
                }
            }
        });

        setVisible(true);  // Torna a janela visível
    }
}
