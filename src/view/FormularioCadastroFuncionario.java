package view;

import controller.BancoController;  // Importa o controlador que gerencia as ações relacionadas ao banco

import javax.swing.*;  // Importa as classes para criar a interface gráfica (botões, campos de texto, etc.)
import java.awt.event.ActionEvent;  // Importa o evento que ocorre quando um botão é clicado
import java.awt.event.ActionListener;  // Importa a interface para capturar os eventos de ação (cliques)

public class FormularioCadastroFuncionario extends JFrame {
    private BancoController bancoController;  // Declaração do controlador de banco

    // Construtor do formulário para cadastrar um novo funcionário, recebe o controlador de banco como parâmetro
    public FormularioCadastroFuncionario(BancoController bancoController) {
        this.bancoController = bancoController;  // Inicializa o controlador de banco

        setTitle("Cadastro de Funcionário");  // Define o título da janela
        setSize(400, 250);  // Define o tamanho da janela (largura 400px, altura 250px)
        setLocationRelativeTo(null);  // Centraliza a janela na tela

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes
        getContentPane().add(panel);  // Adiciona o painel ao conteúdo da janela
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Define o layout do painel como vertical (em linha)

        // Criação dos componentes do formulário
        JLabel labelUsuario = new JLabel("Usuário:");  // Rótulo para o campo de usuário
        JTextField tfUsuario = new JTextField(15);  // Campo de texto para inserir o nome do usuário
        JLabel labelSenha = new JLabel("Senha:");  // Rótulo para o campo de senha
        JPasswordField pfSenha = new JPasswordField(15);  // Campo de senha para inserir a senha do usuário

        // Adiciona os componentes ao painel
        panel.add(labelUsuario);
        panel.add(tfUsuario);
        panel.add(labelSenha);
        panel.add(pfSenha);

        // Criação do botão "Cadastrar"
        JButton btnCadastrar = new JButton("Cadastrar");
        panel.add(btnCadastrar);  // Adiciona o botão ao painel

        // Ação do botão "Cadastrar"
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores inseridos nos campos de usuário e senha
                String usuario = tfUsuario.getText().trim();  // Remove espaços extras antes e depois do nome do usuário
                String senha = new String(pfSenha.getPassword()).trim();  // Obtém a senha e remove espaços extras

                // Verifica se algum dos campos está vazio
                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, "Usuário e Senha devem ser preenchidos.");
                    return;  // Interrompe a execução se algum campo estiver vazio
                }

                try {
                    // Chama o método do controlador para cadastrar o novo funcionário
                    bancoController.cadastrarFuncionario(usuario, senha);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, "Funcionário cadastrado com sucesso!");
                    dispose();  // Fecha a janela de cadastro após a conclusão do cadastro
                } catch (IllegalArgumentException ex) {
                    // Exibe uma mensagem de erro caso ocorra uma exceção (por exemplo, dados inválidos)
                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, ex.getMessage());
                }
            }
        });

        setVisible(true);  // Torna a janela visível para o usuário
    }
}
