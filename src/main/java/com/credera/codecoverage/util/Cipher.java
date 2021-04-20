package com.credera.codecoverage.util;

public class Cipher {
    // This private access modifier prevents the shift value to be changed without using the setter.
    // It reduces causes tight coupling.
    private int shift;
    private int base;

    /**
     * Sets the shift for the cipher.
     * @param shift The shift value to be used by the cipher
     */
    public void setShift(int shift) {
        this.shift = shift;
    }

    /**
     *
     * @param base The cipher base value
     */
    public void setBase(int base) {
        this.base = base;
    }

    /**
     *
     * @param val The value to "reverse" a shift operation
     * @return The unshifted value
     */
    public int unshift(int val) {
        return (val - shift + base) % base;
    }
}
