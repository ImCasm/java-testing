package casm.javatests.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsUtilTest {

    @Test
    public void repeat() {
        assertEquals("holahola", StringsUtil.repeat("hola",2));
    }

    @Test
    public void emptyStringTest() {
        assertTrue(StringsUtil.isEmpty(""));
        assertTrue(StringsUtil.isEmpty(" "));
        assertTrue(StringsUtil.isEmpty(null));
    }

    @Test
    public void noEmptyStringTest() {
        assertFalse(StringsUtil.isEmpty("hola"));
        assertFalse(StringsUtil.isEmpty("d"));
        assertFalse(StringsUtil.isEmpty("holaa"));
    }
}