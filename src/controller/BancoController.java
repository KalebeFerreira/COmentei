package controller;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Funcionario;
import utils.DataManager;
import view.FormularioConsultarConta;
import view.FormularioEncerrarConta;
import view.FormularioCadastroFuncionario;

import java.util.ArrayList;
import java.util.List;

// Classe BancoController, que controla as operações do banco, como criação, consulta e exclusão de contas.
public class BancoController {
    private List<Conta> contas; // Lista para armazenar todas as contas bancárias
    private List<Funcionario> funcionarios; // Lista para armazenar os funcionários do banco

    // Construtor da classe BancoController, que inicializa as listas de contas e funcionários e carrega os dados salvos
    public BancoController() {
        contas = new ArrayList<>(); // Inicializa a lista de contas como uma lista vazia
        funcionarios = new ArrayList<>(); // Inicializa a lista de funcionários como uma lista vazia
        carregarDados(); // Chama o método para carregar os dados salvos de contas e funcionários
    }

    // Método para criar uma nova conta
    public void criarConta(int numeroConta, String nome, String cpf, String tipoConta) {
        // Verifica se algum dos campos obrigatórios está vazio e lança uma exceção caso afirmativo
        if (nome.isEmpty() || cpf.isEmpty() || tipoConta == null || tipoConta.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }

        Conta novaConta; // Declara uma variável para armazenar a nova conta a ser criada
        // Verifica o tipo de conta e cria a conta apropriada
        if ("Corrente".equals(tipoConta)) {
            double limite = 1000.0; // Define um limite de 1000.0 para contas Corrente
            novaConta = new ContaCorrente(numeroConta, nome, cpf, limite); // Cria uma nova ContaCorrente
        } else if ("Poupança".equals(tipoConta)) {
            novaConta = new ContaPoupanca(numeroConta, nome, cpf); // Cria uma nova ContaPoupanca
        } else {
            // Se o tipo de conta não for válido, lança uma exceção
            throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        contas.add(novaConta); // Adiciona a nova conta à lista de contas
        salvarDados(); // Salva os dados das contas no arquivo
    }

    // Método para consultar os detalhes de uma conta
    public String consultarConta(int numeroConta) {
        for (Conta conta : contas) { // Itera sobre a lista de contas
            if (conta.getNumero() == numeroConta) { // Verifica se o número da conta corresponde ao número procurado
                // Se encontrar a conta, monta uma string com os detalhes da conta
                StringBuilder detalhesConta = new StringBuilder();
                detalhesConta.append("Número da Conta: ").append(conta.getNumero()).append("\n");
                detalhesConta.append("Nome do Titular: ").append(conta.getNome()).append("\n");
                detalhesConta.append("CPF do Titular: ").append(conta.getCpf()).append("\n");
                detalhesConta.append("Tipo de Conta: ").append(conta.getTipoConta()).append("\n");
                detalhesConta.append("Saldo: ").append(conta.getSaldo()).append("\n");
                detalhesConta.append(conta.consultarDetalhes()); // Chama um método específico da conta para detalhar mais informações
                return detalhesConta.toString(); // Retorna a string com os detalhes
            }
        }
        return "Conta não encontrada."; // Se não encontrar a conta, retorna uma mensagem informando isso
    }

    // Método para excluir uma conta
    public String excluirConta(int numeroConta) {
        for (int i = 0; i < contas.size(); i++) { // Itera sobre a lista de contas
            if (contas.get(i).getNumero() == numeroConta) { // Verifica se o número da conta corresponde ao procurado
                contas.remove(i); // Remove a conta da lista
                salvarDados(); // Salva os dados atualizados das contas no arquivo
                return "Conta excluída com sucesso!"; // Retorna uma mensagem de sucesso
            }
        }
        return "Conta não encontrada."; // Se a conta não for encontrada, retorna uma mensagem de erro
    }

    // Método para alterar os dados de uma conta
    public boolean alterarDados(int numeroConta, String novoNome, String novoCpf) {
        for (Conta conta : contas) { // Itera sobre a lista de contas
            if (conta.getNumero() == numeroConta) { // Verifica se o número da conta corresponde ao procurado
                conta.setNome(novoNome); // Altera o nome da conta
                conta.setCpf(novoCpf); // Altera o CPF da conta
                salvarDados(); // Salva os dados atualizados das contas no arquivo
                return true; // Retorna verdadeiro indicando que a conta foi encontrada e os dados alterados
            }
        }
        return false; // Retorna falso se a conta não for encontrada
    }

    // Método para cadastrar um novo funcionário
    public void cadastrarFuncionario(String usuario, String senha) {
        // Verifica se os campos de usuário e senha não estão vazios
        if (usuario.isEmpty() || senha.isEmpty()) {
            throw new IllegalArgumentException("Usuário e Senha devem ser preenchidos.");
        }

        Funcionario novoFuncionario = new Funcionario(usuario, senha); // Cria um novo objeto Funcionario
        funcionarios.add(novoFuncionario); // Adiciona o novo funcionário à lista de funcionários
        salvarDados(); // Salva os dados atualizados dos funcionários no arquivo
    }

    // Método para salvar os dados de contas e funcionários no arquivo
    public void salvarDados() {
        DataManager.salvarContas(contas, "contas.dat"); // Salva a lista de contas no arquivo "contas.dat"
        DataManager.salvarFuncionarios(funcionarios, "funcionarios.dat"); // Salva a lista de funcionários no arquivo "funcionarios.dat"
    }

    // Método para carregar os dados de contas e funcionários a partir do arquivo
    public void carregarDados() {
        contas = DataManager.carregarContas("contas.dat"); // Carrega a lista de contas do arquivo "contas.dat"
        funcionarios = DataManager.carregarFuncionarios("funcionarios.dat"); // Carrega a lista de funcionários do arquivo "funcionarios.dat"
    }
}
