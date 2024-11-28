package view;

import controller.BancoController;  // Importa o controlador de banco para o gerenciamento de contas e operações
import utils.DataManager;           // Importa o DataManager para carregar e validar os dados dos funcionários

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {  // Inicia a classe TelaLogin, que estende JFrame (janela do aplicativo)

    public TelaLogin() {
        // Inicializa o arquivo de funcionários com um usuário padrão, se necessário
        DataManager.inicializarFuncionarios();  // Chama o método para garantir que os dados dos funcionários estejam inicializados

        setTitle("Banco Malvader - Login");  // Define o título da janela como "Banco Malvader - Login"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define que, ao fechar a janela, o aplicativo será encerrado
        setSize(400, 200);  // Define o tamanho da janela (largura: 400px, altura: 200px)
        setLayout(new GridLayout(3, 1));  // Organiza os componentes na janela em um grid (3 linhas, 1 coluna)

        JTextField txtUsuario = new JTextField();  // Cria um campo de texto para o usuário digitar o nome de usuário
        JPasswordField txtSenha = new JPasswordField();  // Cria um campo de senha para o usuário digitar a senha

        add(new JLabel("Usuário:"));  // Adiciona um rótulo "Usuário:" à janela
        add(txtUsuario);  // Adiciona o campo de texto para o usuário à janela
        add(new JLabel("Senha:"));  // Adiciona um rótulo "Senha:" à janela
        add(txtSenha);  // Adiciona o campo de senha à janela

        // Criação de botões para as opções "Cliente" e "Funcionário"
        JButton btnCliente = new JButton("Cliente");
        JButton btnFuncionario = new JButton("Funcionário");

        JPanel panelButtons = new JPanel();  // Cria um painel para organizar os botões
        panelButtons.setLayout(new FlowLayout());  // Organiza os botões com fluxo (alinhamento horizontal)
        panelButtons.add(btnCliente);  // Adiciona o botão "Cliente" ao painel
        panelButtons.add(btnFuncionario);  // Adiciona o botão "Funcionário" ao painel
        add(panelButtons);  // Adiciona o painel com os botões à janela

        // Evento do botão Cliente
        btnCliente.addActionListener(new ActionListener() {  // Define a ação quando o botão "Cliente" for clicado
            @Override
            public void actionPerformed(ActionEvent e) {  // Método que será executado ao clicar no botão
                new MenuCliente();  // Cria uma nova instância do menu do cliente
                dispose();  // Fecha a janela de login
            }
        });

        // Evento do botão Funcionário
        btnFuncionario.addActionListener(new ActionListener() {  // Define a ação quando o botão "Funcionário" for clicado
            @Override
            public void actionPerformed(ActionEvent e) {  // Método que será executado ao clicar no botão
                String usuario = txtUsuario.getText().trim();  // Obtém o texto do campo "Usuário" e remove espaços extras
                String senha = new String(txtSenha.getPassword()).trim();  // Obtém a senha, converte para String e remove espaços extras

                // Verifica se o usuário ou senha estão vazios
                if (usuario.isEmpty() || senha.isEmpty()) {  
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");  // Exibe uma mensagem de erro se algum campo estiver vazio
                    return;  // Retorna, não permitindo o login
                }

                // Verificar login no arquivo "funcionarios.dat"
                if (DataManager.validarLoginFuncionario(usuario, senha, "funcionarios.dat")) {  // Verifica se as credenciais são válidas
                    try {
                        BancoController bancoController = new BancoController();  // Cria uma instância do controlador do banco
                        new MenuFuncionario(bancoController);  // Cria uma nova instância do menu do funcionário
                        dispose();  // Fecha a janela de login
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao iniciar o menu do funcionário: " + ex.getMessage());  // Exibe uma mensagem de erro caso ocorra algum problema
                        ex.printStackTrace();  // Exibe o rastreamento de erro no console
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");  // Mensagem de erro caso o login seja inválido
                }
            }
        });

        setVisible(true);  // Torna a janela visível para o usuário
    }

    public static void main(String[] args) {
        new TelaLogin();  // Inicia a aplicação criando uma nova instância da TelaLogin
    }
}
