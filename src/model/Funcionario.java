// Pacote model, que contém as classes que representam o modelo de dados do sistema
package model;

import java.io.Serializable; // Importa a interface Serializable para permitir a serialização do objeto

// Classe Funcionario, que representa os funcionários do banco
public class Funcionario implements Serializable {
    private String nome; // Nome do funcionário
    private String cpf; // CPF do funcionário
    private String telefone; // Telefone do funcionário
    private String endereco; // Endereço do funcionário
    private String usuario; // Nome de usuário para login
    private String senha; // Senha para login

    // Construtor completo para inicializar todos os dados do funcionário
    public Funcionario(String nome, String cpf, String telefone, String endereco, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = senha;
    }

    // Construtor simplificado para login (apenas usuário e senha)
    public Funcionario(String usuario, String senha) {
        this.usuario = usuario; // Inicializa o nome de usuário
        this.senha = senha; // Inicializa a senha
        this.nome = ""; // Inicializa os outros campos com valores padrão
        this.cpf = "";
        this.telefone = "";
        this.endereco = "";
    }

    // Getters e Setters para acessar e modificar os atributos do funcionário

    public String getNome() {
        return nome; // Retorna o nome do funcionário
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do funcionário
    }

    public String getCpf() {
        return cpf; // Retorna o CPF do funcionário
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; // Define o CPF do funcionário
    }

    public String getTelefone() {
        return telefone; // Retorna o telefone do funcionário
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Define o telefone do funcionário
    }

    public String getEndereco() {
        return endereco; // Retorna o endereço do funcionário
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco; // Define o endereço do funcionário
    }

    public String getUsuario() {
        return usuario; // Retorna o nome de usuário do funcionário
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario; // Define o nome de usuário do funcionário
    }

    public String getSenha() {
        return senha; // Retorna a senha do funcionário
    }

    public void setSenha(String senha) {
        this.senha = senha; // Define a senha do funcionário
    }

   @Override
   public String toString() {
       // Método que retorna uma string com as informações do funcionário
       return "Funcionario{" +
               "nome='" + nome + '\'' +
               ", cpf='" + cpf + '\'' +
               ", telefone='" + telefone + '\'' +
               ", endereco='" + endereco + '\'' +
               ", usuario='" + usuario + '\'' +
               '}'; 
   }
}
