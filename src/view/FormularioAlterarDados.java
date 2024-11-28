package view;

import controller.BancoController;  // Importa a classe BancoController, que gerencia as operações bancárias
import javax.swing.*;  // Importa as classes do pacote Swing para criar interfaces gráficas
import java.awt.*;  // Importa classes para layout de componentes
import java.awt.event.ActionEvent;  // Importa o pacote para eventos de ação
import java.awt.event.ActionListener;  // Importa o pacote para escutar os eventos de ação

public class FormularioAlterarDados extends JFrame {  // Define a classe FormularioAlterarDados que herda de JFrame (janela de interface gráfica)
    private BancoController bancoController;  // Declara a variável bancoController para manipular dados bancários

    // Construtor que recebe o BancoController como parâmetro
    public FormularioAlterarDados(BancoController bancoController) {
        this.bancoController = bancoController;  // Atribui o objeto bancoController à variável de instância

        setTitle("Alterar Dados da Conta");  // Define o título da janela
        setSize(400, 250);  // Define o tamanho da janela (400x250 pixels)
        setLocationRelativeTo(null);  // Centraliza a janela na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Define a operação de fechamento da janela (fechar sem encerrar o programa)

        JPanel panel = new JPanel();  // Cria um painel para adicionar os componentes
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Define o layout do painel para empilhar os componentes na vertical

        // Criação dos rótulos e campos de texto para o formulário
        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);  // Campo para o número da conta (15 caracteres)
        JButton btnBuscarConta = new JButton("Buscar Conta");  // Botão para buscar a conta

        JLabel labelNome = new JLabel("Novo Nome:");
        JTextField tfNome = new JTextField(15);  // Campo para o novo nome
        JLabel labelCpf = new JLabel("Novo CPF:");
        JTextField tfCpf = new JTextField(15);  // Campo para o novo CPF

        // Inicialmente desabilita os campos de nome e CPF, e o botão de alteração
        tfNome.setEnabled(false);
        tfCpf.setEnabled(false);

        JButton btnAlterar = new JButton("Alterar Dados");  // Botão para alterar os dados
        btnAlterar.setEnabled(false);  // Desabilita o botão inicialmente

        // Adiciona todos os componentes ao painel
        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnBuscarConta);
        panel.add(labelNome);
        panel.add(tfNome);
        panel.add(labelCpf);
        panel.add(tfCpf);
        panel.add(btnAlterar);

        getContentPane().add(panel);  // Adiciona o painel ao conteúdo da janela

        // Ação do botão Buscar Conta
        btnBuscarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();  // Pega o número da conta inserido

                if (numeroConta.isEmpty()) {  // Verifica se o número da conta foi preenchido
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "O número da conta deve ser preenchido.");
                    return;
                }

                try {
                    // Tenta consultar a conta usando o controlador
                    String resultadoConsulta = bancoController.consultarConta(Integer.parseInt(numeroConta));

                    if (!resultadoConsulta.equals("Conta não encontrada.")) {  // Verifica se a conta foi encontrada
                        // Se encontrada, habilita os campos de nome, CPF e o botão de alteração
                        tfNome.setEnabled(true);
                        tfCpf.setEnabled(true);
                        btnAlterar.setEnabled(true);
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta encontrada. Pode alterar os dados.");
                    } else {
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta não encontrada.");
                    }

                } catch (NumberFormatException ex) {  // Caso ocorra erro na conversão do número da conta
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Número da conta inválido.");
                } catch (Exception ex) {  // Em caso de erro genérico
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Erro ao buscar a conta.");
                }
            }
        });

        // Ação do botão Alterar Dados
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();
                String novoNome = tfNome.getText().trim();
                String novoCpf = tfCpf.getText().trim();

                if (novoNome.isEmpty() || novoCpf.isEmpty()) {  // Verifica se os campos de nome e CPF estão preenchidos
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "O nome e o CPF devem ser preenchidos.");
                    return;
                }

                try {
                    // Tenta alterar os dados da conta
                    boolean contaAlterada = bancoController.alterarDados(Integer.parseInt(numeroConta), novoNome, novoCpf);

                    if (contaAlterada) {  // Se os dados forem alterados com sucesso
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Dados da conta alterados com sucesso!");
                        dispose();  // Fecha a janela
                    } else {
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta não encontrada.");
                    }
                } catch (NumberFormatException ex) {  // Erro na conversão do número da conta
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Número da conta inválido.");
                } catch (Exception ex) {  // Qualquer outro erro
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Erro ao alterar os dados da conta.");
                }
            }
        });

        setVisible(true);  // Torna a janela visível
    }
}
