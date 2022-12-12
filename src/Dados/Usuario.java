package Dados;

public class Usuario {

    private String nome, telefone;
    private static int codUsuario=1;
    private int codigoDoUsuario;//id para saber qual usuário está pegando empréstimo

    public Usuario(String nome, String telefone) {
        super();
        this.nome = nome;
        this.telefone = telefone;
        this.codigoDoUsuario = codUsuario;
        codUsuario++;//Gera Id automatico, primeiro é 1 e depois incrementa
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodigoDoUsuario() {
        return codigoDoUsuario;
    }

}