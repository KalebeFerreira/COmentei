// Pacote model, que contém as classes que representam o modelo de dados do sistema
package model;

import java.io.Serializable; // Importa a interface Serializable, para possibilitar a serialização dos objetos
import java.util.ArrayList; // Importa a classe ArrayList, que é usada para criar listas dinâmicas
import java.util.List; // Importa a interface List, que define uma coleção de elementos

// Classe abstrata Conta, que serve como base para outros tipos de contas
public abstract class Conta implements Serializable {
    private int numero; // Número da conta bancária
    private double saldo; // Saldo disponível na conta
    private double limite; // Limite da conta, usado em contas correntes (cheque especial)
    private String tipoConta; // Tipo da conta (ex.: "Corrente", "Poupança")
    private String nome; // Nome do titular da conta
    private String cpf;  // CPF do titular da conta
    private List<String> extrato; // Lista que vai armazenar o histórico de transações (depósitos, saques, etc.)

    // Construtor da classe Conta
    public Conta(int numero, String tipoConta, String nome, String cpf) {
        this.numero = numero; // Inicializa o número da conta
        this.tipoConta = tipoConta; // Inicializa o tipo da conta
        this.saldo = 0.0; // Inicializa o saldo como zero
        this.limite = 0.0; // Inicializa o limite como zero
        this.nome = nome; // Inicializa o nome do titular
        this.cpf = cpf; // Inicializa o CPF do titular
        this.extrato = new ArrayList<>(); // Cria uma lista vazia para armazenar o extrato
    }

    // Métodos de acesso (Getters) e modificação (Setters) para as variáveis da classe

    public int getNumero() {
        return numero; // Retorna o número da conta
    }

    public double getSaldo() {
        return saldo; // Retorna o saldo da conta
    }

    public double getLimite() {
        return limite; // Retorna o limite da conta
    }

    public void setLimite(double limite) {
        this.limite = limite; // Define o limite da conta
    }

    public String getTipoConta() {
        return tipoConta; // Retorna o tipo da conta
    }

    public String getNome() {
        return nome; // Retorna o nome do titular da conta
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do titular da conta
    }

    public String getCpf() {
        return cpf; // Retorna o CPF do titular da conta
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; // Define o CPF do titular da conta
    }

    // Métodos para operação de depósito e saque

    public void depositar(double valor) {
        saldo += valor; // Adiciona o valor depositado ao saldo da conta
        extrato.add("Depósito: R$ " + valor); // Registra a transação de depósito no extrato
    }

    public boolean sacar(double valor) {
        // Verifica se o valor solicitado para saque é menor ou igual ao saldo mais o limite
        if (valor <= saldo + limite) {
            saldo -= valor; // Deduz o valor do saldo da conta
            extrato.add("Saque: R$ " + valor); // Registra a transação de saque no extrato
            return true; // Retorna verdadeiro, indicando que o saque foi realizado
        }
        return false; // Retorna falso se o saldo e o limite não forem suficientes para o saque
    }

    public List<String> getExtrato() {
        return extrato; // Retorna o extrato com todas as transações registradas
    }

    // Método abstrato que será implementado pelas subclasses, retornando detalhes específicos de cada tipo de conta
    public abstract String consultarDetalhes();
}
