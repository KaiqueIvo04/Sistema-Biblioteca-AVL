package Testes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import AVL.*;

public class Test0_AVL {
    
    static ArvoreAvl t;

    @Before
    public void intanciaAVL(){
        t = new ArvoreAvl();
    }

    @Test
    public void inserirBuscarAVL(){
        t.inserir(7);
        t.inserir(8);
        t.inserir(155);
        t.inserir(0);
        assertTrue(t.busca(7) );
        assertTrue(t.busca(0));
        assertTrue(t.busca(155));
        assertFalse(t.busca(100));
        assertFalse(t.busca(157));
    }

    @Test
    public void inorderAVL(){
        t.inserir(7);
        t.inserir(8);
        t.inserir(155);
        t.inserir(0);
        String string = "[0, 7, 8, 155]";
        assertEquals(string, t.inorder().toString());
    }

    @Test
    public void removerAVL(){
        t.inserir(7);
        t.inserir(8);
        t.inserir(155);
        t.inserir(0);
        t.remover(8);
        t.remover(0);
        String string = "[7, 155]";
        assertEquals(string, t.inorder().toString());
    }

    @Test
    public void alturaAVL(){
        t.inserir(7);
        t.inserir(8);
        t.inserir(155);
        t.inserir(0);
        assertEquals(2, t.altura(t.raiz));
    }


}
