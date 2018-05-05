package com.example.troli.p2help;

import com.example.troli.p2help.DAO.Sistema;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SistemaTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

    }

    @Test
    public void testSistema_setNOme_SetIgualGet () throws Exception {
        Sistema sistema = new Sistema();
        sistema.setNome("Windows XP");
        assertEquals("Windows XP", sistema.getNome() );
    }

    public List<Sistema> mockListagemSistemas ()  {
        List<Sistema> sistemas = new ArrayList<Sistema>() ;
        Sistema sistema = new Sistema();

        sistema.setID(1);
        sistema.setNome("Windows");
        sistema.setVersao("XP");
        sistemas.add(sistema);

        sistema.setID(2);
        sistema.setNome("Corel Draw");
        sistema.setVersao("X10");
        sistemas.add(sistema);


        return sistemas;
    }

    @Test
    public void testSistema_Listagem_DoisElementosResult () throws Exception {
        List<Sistema> sistemas = new ArrayList<Sistema>() ;
        sistemas = mockListagemSistemas();
        assertEquals(2, sistemas.size() );
    }

    @Test
    public void testSistema_Listagem_DoisElementosResultError () throws Exception {
        List<Sistema> sistemas = new ArrayList<Sistema>() ;
        sistemas = mockListagemSistemas();
        assertNotEquals(5, sistemas.size() );
    }

}