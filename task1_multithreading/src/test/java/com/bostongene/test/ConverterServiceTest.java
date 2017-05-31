package com.bostongene.test;

import com.bostongene.ConverterService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Andry on 30.05.17.
 *
 * Test Converter Service
 */
public class ConverterServiceTest {

    private ConverterService cs;

    @Before
    public void init() {
        try {
            cs = new ConverterService();
        }catch (Exception e) {
            assertNotNull(null);
        }
    }

    @Test
    public void testConverter() {
        assertEquals(1, cs.convertToNumber("one"));
        assertEquals(382, cs.convertToNumber("three hundred eighty two"));
        assertEquals(17, cs.convertToNumber("seventeen"));
        assertEquals(4034, cs.convertToNumber("four thousand thirty four"));
        assertEquals(9999, cs.convertToNumber("nine thousand nine hundred ninety nine"));

        assertNotEquals(65, cs.convertToNumber("sixty six"));
    }
}
