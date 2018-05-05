package com.example.troli.p2help;

import com.example.troli.p2help.DAO.Sistema;
import com.example.troli.p2help.Util.Util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilTest {
    @Test
    public void testUtil_md5_ResultOk() throws Exception {
        assertEquals("e7c0d7d93961b23714d814eb0c7ad724", Util.toMD5("Henrique"));
    }

    public void testUtil_md5_ResultError() throws Exception {
        assertNotEquals("HmajshYnamskasugsbnaksy", Util.toMD5("Tiago"));
    }


}