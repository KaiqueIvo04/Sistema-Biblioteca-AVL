package Registros;

import java.util.ArrayList;

import Dados.Usuario;

public class RegistroDeUsuario {
//GERA UM ARRAYLIST DE USUARIOS
    private ArrayList<Usuario> rU;

    public RegistroDeUsuario() {
        rU = new ArrayList<Usuario>();
    }

    public void addUsuario (Usuario usuario) {
        rU.add(usuario);
    }

    public void deleteUsuario(int indice){
        Usuario u = rU.get(indice);
        rU.remove(u);
    }

    public Usuario getUsuario (int indice) {//Pega usuario pelo ID
        return rU.get(indice);
    }

    public void removeContato (Usuario usuario) {//Remove usuario pelo nome
        rU.remove(usuario);
    }

    public void removeContato (int indice) {//Remove usuario pelo Id
        rU.remove(indice);
    }

    public int size() {//Ver tamanho do arraylist (quantos usuarios tem no momento)
        return rU.size();
    }

}
