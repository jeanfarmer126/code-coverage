package com.credera.codecoverage.service;

import com.credera.codecoverage.util.Cipher;

public class UnscrambleService {
    private static final int BASE = 42;
    private static final int SHIFT = 11;

    /**
     * Unscrambles an integer matrix into lines of text.
     *
     * @param matrix An integer matrix
     * @return Lines of text
     */
    public String unscramble(int[][] matrix) {
        Cipher cipher = new Cipher();
        cipher.setBase(BASE);
        cipher.setShift(SHIFT);
        StringBuilder out = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < matrix[row].length; col++) {
                line.append(translate(cipher.unshift(abs(subtract(matrix[row][col]), row % 2 == 0))));
            }
            if (row < matrix.length - 1) line.append("\n");
            out.append(line.toString());
        }

        return out.toString();
    }

    /**
     * Subtracts 66 from the input if the input is a multiple of 2, 3, and 5.
     * Notice there will need to be 8 (3^2) test cases to test all branches.
     * These test cases can be reduced via MC/DC. Given the following truth table (A: val % 2 == 0, B: val % 3 == 0,
     * C: val % 5 == 0),
     * <table>
     *   <tr>
     *     <th>A</td>
     *     <th>B</th>
     *     <th>C</th>
     *     <th>Output</th>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>true</td>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>false</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>false</td>
     *     <td>true</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>false</td>
     *     <td>false</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>false</td>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>false</td>
     *     <td>true</td>
     *     <td>false</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>false</td>
     *     <td>false</td>
     *     <td>true</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>false</td>
     *   </tr>
     * </table>
     * we see that the cases that only one boolean value affects the output expression are
     * <table>
     *   <tr>
     *     <th>A</td>
     *     <th>B</th>
     *     <th>C</th>
     *     <th>Output</th>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>true</td>
     *   </tr>
     *   <tr>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>true</td>
     *     <td>false</td>
     *   </tr>
     *   <tr>
     *     <td>true</td>
     *     <td>true</td>
     *     <td>false&nbsp;&nbsp;</td>
     *     <td>false</td>
     *   </tr>
     * </table>
     *
     * @param val The input value
     * @return The subtraction result
     */
    int subtract(int val) {
        if (val % 2 == 0 && val % 3 == 0 && val % 5 == 0) {
            return val - 66;
        } else {
            return val;
        }
    }

    /**
     * Modified implementation of absolute value.
     * Notice there are 4 branches in this method.
     *
     * @param val The input value
     * @param add Whether to add 3 to the input value before applying the absolute value logic
     * @return The absolute value
     */
    int abs(int val, boolean add) {
        int newVal = add ? val + 3 : val;
        return (newVal < 0) ? -1 * newVal : newVal;
    }

    /**
     * This method translates an integer value to a character.
     * Notice that if the method used as switch statement instead of using an array of characters, it would have a high
     * cyclomatic complexity (of 42), which makes testing each path tedious.
     *
     * @param val The input integer value
     * @return The corresponding character
     */
    char translate(int val) {
        final char[] values = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '=', '-', '+', '?'
        };
        if (val < values.length) {
            return values[val];
        }
        return Character.MIN_VALUE;
    }
}
