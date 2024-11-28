// Pacote model, que contém as classes que representam o modelo de dados do sistema
package model;

// Classe que representa uma conta poupança, que herda de Conta
public class ContaPoupanca extends Conta {

    // Construtor da classe ContaPoupanca
    public ContaPoupanca(int numero, String nome, String cpf) {
        // Chama o construtor da classe mãe (Conta) para inicializar os atributos comuns
        super(numero, "Poupança", nome, cpf);
    }

    // Método que retorna os detalhes específicos de uma Conta Poupança
    @Override
    public String consultarDetalhes() {
        return "Conta Poupança - Saldo: " + getSaldo(); // Retorna o saldo da conta poupança
    }
}
