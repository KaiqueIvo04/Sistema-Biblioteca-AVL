package Dados;

import java.time.LocalDateTime;
import java.util.Random;

public class Livro implements Comparable<Livro>{

    private String titulo, autor;
    private LocalDateTime dataEmprestimo;
    private double valor;
    private static int codLv=1;
    public int codigoDoLivro;
    Usuario usuarioComLivro;
    Random gerador;

    public Livro(String titulo, String autor, double valor) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.valor = valor;
        dataEmprestimo = null;//não foi emprestado ainda
        usuarioComLivro = null;//não foi emprestado ainda
        this.codigoDoLivro = codLv;
        gerador = new Random();
        codLv = gerador.nextInt(100);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigoDoLivro() {
        return codigoDoLivro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getUsuarioComLivro() {//Ver qual usuario está com o livro
        return usuarioComLivro.getNome();
    }

    public void setUsuarioComLivro(Usuario usuarioComLivro) {
        this.usuarioComLivro = usuarioComLivro;
    }

    public int compareTo(Livro l) {
        return this.titulo.compareToIgnoreCase(l.getTitulo());
    }


}