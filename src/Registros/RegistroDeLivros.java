package Registros;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import Dados.*;

public class RegistroDeLivros {
    //funcionamento igual da classe RegistroDeUsuario
    private ArrayList<Livro> rL;

    public RegistroDeLivros() {
        rL = new ArrayList<Livro>();
    }

    public void addLivro (Livro livro) {
        rL.add(livro);
    }

    public Livro getLivro (int indice) {
        return rL.get(indice);
    }

    public void removeLivro(Livro livro) {
        rL.remove(livro);
    }

    public void removeLivro (int indice) {
        rL.remove(indice);
    }

    public int size() {
        return rL.size();
    }
//Retorna o dia e hora do emprestimo, e seta usuario que pegou
    public void emprestaLivro (Livro livro, Usuario usuario){
        LocalDateTime now = LocalDateTime.now();
        livro.setDataEmprestimo(now);
        livro.setUsuarioComLivro(usuario);
    }

    public void ordenaTituloAZ(){//ordena alfabeticamente
        Collections.sort(rL);
    }

    public Livro retornaLivro(int chave){//retorna um livro apartir do seu Id
        for(int i = 0; i < size(); i++ ){
            if(getLivro(i).codigoDoLivro == chave){
                return getLivro(i);
            }
        }
        return null;
    }
}
