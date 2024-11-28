package utils;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe DataManager, responsável por salvar e carregar dados de contas e funcionários em arquivos
public class DataManager {

    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.dat"; // Nome do arquivo para salvar os dados de funcionários
    private static final String ARQUIVO_CONTAS = "contas.dat"; // Nome do arquivo para salvar os dados das contas

    // Método para salvar a lista de contas em um arquivo
    public static void salvarContas(List<Conta> contas, String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) { // Abre o arquivo para escrita
            oos.writeObject(contas); // Escreve a lista de contas no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro se houver uma exceção ao salvar
        }
    }

    // Método para carregar a lista de contas a partir de um arquivo
    public static List<Conta> carregarContas(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) { // Abre o arquivo para leitura
            return (List<Conta>) ois.readObject(); // Lê e retorna a lista de contas do arquivo
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se houver erro
        }
    }

    // Método para salvar a lista de funcionários em um arquivo
    public static void salvarFuncionarios(List<Funcionario> funcionarios, String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) { // Abre o arquivo para escrita
            oos.writeObject(funcionarios); // Escreve a lista de funcionários no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro se houver uma exceção ao salvar
        }
    }

    // Método para carregar a lista de funcionários a partir de um arquivo
    public static List<Funcionario> carregarFuncionarios(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) { // Abre o arquivo para leitura
            return (List<Funcionario>) ois.readObject(); // Lê e retorna a lista de funcionários do arquivo
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se houver erro
        }
    }

    // Método para inicializar o arquivo de funcionários com um usuário padrão
    public static void inicializarFuncionarios() {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS); // Cria um objeto File para verificar a existência do arquivo
        if (!arquivo.exists()) { // Se o arquivo não existir
            List<Funcionario> funcionarios = new ArrayList<>(); // Cria uma lista de funcionários vazia
            // Adiciona usuários padrão à lista
            funcionarios.add(new Funcionario("admin", "1234", "1234567890", "Rua A", "admin", "1234")); 
            funcionarios.add(new Funcionario("funcionario1", "senha123", "9876543210", "Rua B", "func1", "senha123"));
            salvarFuncionarios(funcionarios, ARQUIVO_FUNCIONARIOS); // Salva a lista no arquivo
            System.out.println("Arquivo de funcionários inicializado com usuário padrão.");
        } else {
            System.out.println("Arquivo de funcionários já existe. Não foi necessário inicializar.");
        }
    }

    // Método para validar o login de um funcionário
    public static boolean validarLoginFuncionario(String usuario, String senha, String arquivo) {
        List<Funcionario> funcionarios = carregarFuncionarios(arquivo); // Carrega a lista de funcionários do arquivo
        System.out.println("Verificando login para: " + usuario);
        
        // Verifica se o usuário e senha informados correspondem a algum funcionário
        for (Funcionario f : funcionarios) {
            System.out.println("Usuário carregado: " + f.getUsuario() + ", Senha: " + f.getSenha());
            if (f.getUsuario().equals(usuario) && f.getSenha().equals(senha)) {
                return true; // Se o login for válido, retorna true
            }
        }
        return false; // Retorna false se o login for inválido
    }

    // Método para inicializar o arquivo de contas com contas padrão
    public static void inicializarContas() {
        File arquivo = new File(ARQUIVO_CONTAS); // Cria um objeto File para verificar a existência do arquivo
        if (!arquivo.exists()) { // Se o arquivo não existir
            List<Conta> contas = new ArrayList<>(); // Cria uma lista de contas vazia
            // Adiciona contas padrão à lista
            contas.add(new ContaCorrente(1001, "João", "12345678901", 5000.0)); // Exemplo de Conta Corrente
            contas.add(new ContaPoupanca(2002, "Maria", "98765432100")); // Exemplo de Conta Poupança
            salvarContas(contas, ARQUIVO_CONTAS); // Salva a lista no arquivo
            System.out.println("Arquivo de contas inicializado com contas padrão.");
        }
    }
}
