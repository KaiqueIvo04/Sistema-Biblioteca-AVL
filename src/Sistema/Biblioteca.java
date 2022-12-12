package Sistema;

import java.util.ArrayList;
import java.util.Scanner;

import Registros.*;
import Dados.*;
import AVL.*;

public class Biblioteca {

    private static Scanner tc = new Scanner(System.in);
    private static RegistroDeLivros rL;
    private static RegistroDeUsuario rU;
    private static ArvoreAvl tree  = new ArvoreAvl();

    private static void adicionaColega() {
        String nome, telefone;
        Usuario u;
        System.out.println("==> Adicionar Usuario:\n");
        System.out.print("Nome: ");
        nome = tc.nextLine();
        System.out.print("Telefone: ");
        telefone = tc.nextLine();

        u = new Usuario(nome, telefone);
        rU.addUsuario(u);
    }

    private static void adicionaLivro() {
        String titulo, autor;
        double valor;
        Livro l;
        System.out.println("==> Adicionar Livro:\n");
        System.out.print("Titulo: ");
        titulo = tc.nextLine();
        System.out.print("Autor: ");
        autor = tc.nextLine();
        System.out.print("Valor: ");
        valor = tc.nextDouble();

        l = new Livro(titulo, autor, valor);
        tree.inserir(l.getCodigoDoLivro());
        rL.addLivro(l);
    }

    private static void listarLivrosLivres() {
        System.out.println("=== Lista de livros disponiveis ===");
        System.out.printf("%5s\t %20s\t\t\t %15s\t\t %15s\n","Nr Registro", "Titulo", "Autor", "Valor" );
        for (int i = 0; i < rL.size(); i++) {
            Livro l = rL.getLivro(i);
            if(l.getDataEmprestimo()==null){
                System.out.printf ("%5s\t\t %20s\t\t\t  %15s\t\t %15s\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor());
            }
        }
    }

    private static void listarTodosUsuarios() {
        System.out.println("=== Lista de Usuarios ===");
        System.out.printf("%5s\t %15s\t\t\t %20s\n","Cod", "Nome", "Telefone" );
        for (int i = 0; i < rU.size(); i++) {
            Usuario u = rU.getUsuario(i);
            System.out.printf ("%5s\t %15s\t\t\t  %20s\n",
                    u.getCodigoDoUsuario(), u.getNome(), u.getTelefone());
        }
    }

    private static void listarTodosLivrosAZ() {
        System.out.println("=== Lista de livros disponiveis ===");
        System.out.printf("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n","Cod", "Titulo", "Autor", "Valor", "Usuario");
        for (int i = 0; i < rL.size(); i++) {
            Livro l = rL.getLivro(i);
            if(l.getDataEmprestimo()==null){
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t Livro Disponivel\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor());
            }
            else{
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor(), l.getUsuarioComLivro());
            }
        }
    }

    private static void emprestarLivro() { //Faz a verificaçao do codigo do usuario e ve se existe ou nao
        boolean flag=false;
        boolean flagUsuario=false;
        int cod;
        int codUsuario;
        System.out.print("Digite o codigo do livro a ser emprestado:");
        cod = tc.nextInt();
        tc.nextLine();
        Biblioteca.listarTodosUsuarios();
        System.out.println();
        System.out.print("Digite o codigo do Usuario a emprestar o livro:");
        codUsuario = tc.nextInt();
        tc.nextLine();
        for (int i = 0; i < rU.size(); i++) {
            Usuario usuario = rU.getUsuario(i);
            if(usuario.getCodigoDoUsuario()==codUsuario){
                flagUsuario=true;
                for (int i1 = 0; i1 < rL.size(); i1++) {
                    Livro l = rL.getLivro(i1);
                    if(l.getCodigoDoLivro()==cod&&l.getDataEmprestimo()==null){
                        rL.emprestaLivro(l,usuario);
                        System.out.println("livro codigo "+l.getCodigoDoLivro()+" emprestado com sucesso para o Usuario: " + usuario.getNome() + ".");
                        System.out.println("Data de emprestimo: "+l.getDataEmprestimo());
                        flag=true;
                    }
                }
                if(flag==false){
                    System.out.println("Livro não encontrado ou já emprestado.");
                }
            }
        }
        if(flagUsuario==false){
            System.out.println("Usuario não encontrado.");
        }


    }

    private static void devolverLivro() {
        boolean flag=false;
        int cod;
        System.out.print("Digite o codigo do livro a ser devolvido:");
        cod = tc.nextInt();
        tc.nextLine();
        for (int i = 0; i < rL.size(); i++) {
            Livro l = rL.getLivro(i);
            if(l.getCodigoDoLivro()==cod){
                l.setDataEmprestimo(null);
                flag=true;
                System.out.println("livro codigo "+l.getCodigoDoLivro()+" devolvido com sucesso.");
            }
        }
        if(flag==false){
            System.out.println("Livro não encontrado.");
        }
    }

    private static void listarLivrosAZ() {
        rL.ordenaTituloAZ();
    }

    private static void buscarLivroId(){ //Busca o livro na árvore apartir do Id
        System.out.println("Digite o Id do livro a ser buscado:");
        int x = tc.nextInt();
        System.out.println();
        if(tree.busca(x)){
            System.out.println("Seu livro foi encontrado!\n");
            Livro l = rL.retornaLivro(x);
            System.out.printf("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n","Cod", "Titulo", "Autor", "Valor", "Usuario");
            if(l.getDataEmprestimo()==null){
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t Livro Disponivel\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor());
            }
            else{
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor(), l.getUsuarioComLivro());
            }
        }
        else System.out.println("Seu livro não foi encontrado!");
    }

    private static void listarLivrosId(){ //Lista os livros apartir do método inorder da árvore
        System.out.println("=== Lista de livros disponiveis ===");
        System.out.printf("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n","Cod", "Titulo", "Autor", "Valor", "Usuario");
        ArrayList<No> aux = tree.inorder();
        for(int i = 0; i < rL.size(); i++){
            Livro l = rL.retornaLivro(aux.get(i).getChave());
            if(l.getDataEmprestimo()==null){
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t Livro Disponivel\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor());
            }
            else{
                System.out.printf ("%5s\t %20s\t\t  %15s\t\t\t %15s\t\t %15s\n",
                        l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getValor(), l.getUsuarioComLivro());
            }
        }
    }

    private static void removeLivro() {
        int id;

        System.out.println("==> Remover Livro:\n");
        System.out.print("Digite o Id do livro: ");
        id = tc.nextInt();

        
        
        if(!tree.busca(id))
            System.out.println("Livro não encontrado!");
        else{
            tree.remover(id);
            rL.removeLivro(rL.retornaLivro(id));
            System.out.println("Livro removido!");
        }
    }

    private static void removeUsuario(){
        int id;

        System.out.println("==> Remover usuário:\n");
        System.out.print("Digite o Id do usuário: ");
        id = tc.nextInt();

        rU.deleteUsuario(id - 1);
        System.out.println("Usuário removido!");
    }

    public static void main(String[] args) {
        //AQUI CRIAMOS 2 ARRAYLISTS A PARTIR DAS CLASSES REGISTROSDEUSUARIOS E REGISTRODELIVROS
        rL = new RegistroDeLivros();
        rU = new RegistroDeUsuario();

        Livro l1 = new Livro ("EDA", "Janderson", 118.50);
        Livro l2 = new Livro ("Calculo III", "Wenia", 10.00);
        Livro l3 = new Livro ("Interface Gráfica", "Demetrio", 60.80);
        Livro l4 = new Livro ("Teoria Da Computação", "Edson", 52.99);
        Livro l5 = new Livro ("APS", "Daniel", 180.00);
        tree.inserir(l1.getCodigoDoLivro());
        tree.inserir(l2.getCodigoDoLivro());
        tree.inserir(l3.getCodigoDoLivro());
        tree.inserir(l4.getCodigoDoLivro());
        tree.inserir(l5.getCodigoDoLivro());
        rL.addLivro(l1);
        rL.addLivro(l2);
        rL.addLivro(l3);
        rL.addLivro(l4);
        rL.addLivro(l5);
        Usuario c1 = new Usuario ("Lucas Santos", "(83) 57099-0926");
        Usuario c2 = new Usuario ("Kaique Ivo", "(83) 47118-2686");
        Usuario c3 = new Usuario ("Pedro França", "(83) 75280-1018");
        Usuario c4 = new Usuario ("Lucas Fausto", "(83) 88788-5569");
        rU.addUsuario(c1);
        rU.addUsuario(c2);
        rU.addUsuario(c3);
        rU.addUsuario(c4);

        int op;
        do {
            System.out.println("\n==> Menu:\n");
            System.out.println("   0 - Sair");
            System.out.println("   1 - Adicionar livro");
            System.out.println("   2 - Adicionar colega");
            System.out.println("   3 - Buscar livro apartir do Id");
            System.out.println("   4 - Emprestar livro");
            System.out.println("   5 - Devolver livro");
            System.out.println("   6 - Listar livros por ordem alfabetica");
            System.out.println("   7 - Listar livros por ordem do Id");
            System.out.println("   8 - Listar usuários");
            System.out.println("   9 - Remover livro");
            System.out.println("   10 - Remover usuário");
            System.out.print("\n   Opcao: ");
            op = tc.nextInt();
            System.out.println();
            tc.nextLine(); // consumir o enter

            switch (op) {
                case 0:
                    System.out.println("Saindo..."); // System.exit(0);
                    break;
                case 1:
                    adicionaLivro();
                    break;
                case 2:
                    adicionaColega();
                    break;
                case 3:
                    buscarLivroId();
                    break;
                case 4:
                    listarLivrosLivres();
                    emprestarLivro();
                    break;
                case 5:
                    devolverLivro();
                    break;
                case 6:
                    listarLivrosAZ();//Ordena os dados dos livros
                    listarTodosLivrosAZ();//Imprime os dados na tela
                    break;
                case 7:
                    listarLivrosId();
                    break;
                case 8:
                    listarTodosUsuarios();
                    break;
                case 9:
                    removeLivro();
                    break;
                case 10:
                    removeUsuario();
                    break;
                default:
                    System.out.println("Opcao inválida!\n\n");
                    break;
            }
        } while (op != 0);

    }


}