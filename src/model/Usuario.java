// Pacote model, que contém as classes que representam o modelo de dados do sistema
package model;

// Classe Usuario, que representa as credenciais de login
public class Usuario {
    private String username; // Nome de usuário
    private String password; // Senha do usuário
    private String role; // Papel do usuário (funcionario ou cliente)

    // Construtor da classe Usuario para inicializar os dados do usuário
    public Usuario(String username, String password, String role) {
        this.username = username; // Define o nome de usuário
        this.password = password; // Define a senha
        this.role = role; // Define o papel do usuário
    }

    // Getters e Setters para acessar e modificar os atributos do usuário

    public String getUsername() {
        return username; // Retorna o nome de usuário
    }

    public void setUsername(String username) {
        this.username = username; // Define o nome de usuário
    }

    public String getPassword() {
        return password; // Retorna a senha
    }

    public void setPassword(String password) {
        this.password = password; // Define a senha
    }

    public String getRole() {
        return role; // Retorna o papel do usuário (funcionario ou cliente)
    }

    public void setRole(String role) {
        this.role = role; // Define o papel do usuário
    }
}
