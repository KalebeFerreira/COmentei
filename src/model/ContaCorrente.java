// Pacote model, que contém as classes que representam o modelo de dados do sistema
package model;

// Classe que representa uma conta corrente, que herda de Conta
public class ContaCorrente extends Conta {
    private double limite; // Limite adicional para conta corrente (cheque especial)

    // Construtor da classe ContaCorrente
    public ContaCorrente(int numero, String nome, String cpf, double limite) {
        // Chama o construtor da classe mãe (Conta) para inicializar os atributos comuns
        super(numero, "Corrente", nome, cpf);
        this.limite = limite; // Inicializa o limite da conta corrente
    }

    // Método que retorna os detalhes específicos de uma Conta Corrente
    @Override
    public String consultarDetalhes() {
        return "Limite: " + limite; // Retorna o limite da conta corrente
    }
}
