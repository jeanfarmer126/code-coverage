package com.credera.codecoverage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CipherTest {
    private Cipher cipher;

    @BeforeEach
    public void setUp() {
        cipher = new Cipher();
    }

    @Test
    void valueUnshifts() {
        final int shift = 11;
        final int base = 42;
        final int val = 37;
        final int expectedVal = 26;

        cipher.setShift(shift);
        cipher.setBase(base);

        int shiftedVal = cipher.unshift(val);
        assertEquals(shiftedVal, expectedVal);
    }
}
