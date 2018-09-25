package com.dcits.galaxy.base.util;

import java.io.UnsupportedEncodingException;

/**
 * 十六进制帮助类
 *
 * @author 匿名
 * @since EAI1.1
 */

public final class HexUtils {

    private static final char HEX_CHARS[] = {

            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

            'a', 'b', 'c', 'd', 'e', 'f'

    };

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @param i
     * @return
     */

    public static String asHex(byte abyte0[], int i) {

        return asHex(abyte0, i, true);

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param s
     * @return
     */

    public static String asUnicode(String s) {

        String s1 = "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            sb.append("\\u").append(asHex(s.charAt(i) >> 8)).append(asHex(s.charAt(i)));

        }

        s1 = sb.toString();

        return s1;

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param c
     * @return
     */

    public static String asUnicode(char c) {

        StringBuffer stringbuffer = new StringBuffer();

        stringbuffer.append("\\u");

        stringbuffer.append(asHex(c >> 8) + asHex(c));

        return stringbuffer.toString();

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param s
     * @param s1
     * @return
     * @throws UnsupportedEncodingException
     */

    public static String asHex(String s, String s1)
            throws UnsupportedEncodingException {

        byte abyte0[] = s.getBytes(s1);

        return asHex(abyte0);

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @param i
     * @param flag
     * @return
     */

    public static byte[] asHexBytes(byte abyte0[], int i, boolean flag) {

        int j = Math.min(i, abyte0.length);

        int k = 0;

        byte abyte1[];

        if (flag) {

            abyte1 = new byte[j * 2 + 2];

            abyte1[0] = 48;

            abyte1[1] = 120;

            k += 2;

        } else {

            abyte1 = new byte[j * 2];

        }

        for (int l = 0; l < j; l++) {

            abyte1[k++] = (byte) HEX_CHARS[(abyte0[l] & 0xf0) >> 4];

            abyte1[k++] = (byte) HEX_CHARS[(abyte0[l] & 0xf) >> 0];

        }

        return abyte1;

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @param i
     * @param flag
     * @return
     */

    public static String asHex(byte abyte0[], int i, boolean flag) {

        byte b[] = asHexBytes(abyte0, i, flag);

        String s = "";

        try {

            s = new String(b, "ASCII");

        } catch (java.io.UnsupportedEncodingException e) {

            ;

        }

        return s;

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @param i
     * @return
     */

    public static byte[] fromHexString(byte abyte0[], int i) {

        int j = 0;

        if (abyte0[0] == 48 && (abyte0[1] == 120 || abyte0[1] == 88)) {

            j += 2;

            i -= 2;

        }

        int k = i / 2;

        byte abyte1[] = new byte[k];

        // modify for sonar
        for (int l = 0; l < k; l++) {

            abyte1[l] = (byte) ((hexValueOf(abyte0[j]) << 4 | hexValueOf(abyte0[j + 1])) & 0xff);

            j += 2;

        }

        return abyte1;

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param i
     * @return
     */

    public static String asHex(int i) {

        char ac[] = new char[2];

        ac[0] = HEX_CHARS[(i & 0xf0) >> 4];

        ac[1] = HEX_CHARS[(i & 0xf) >> 0];

        return new String(ac);

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @return
     */

    public static String asHex(byte abyte0[]) {

        return asHex(abyte0, abyte0.length);

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param i
     * @return
     */

    public static int hexValueOf(int i) {

        if (i >= 48 && i <= 57)

            return i - 48;

        if (i >= 97 && i <= 102)

            return (i - 97) + 10;

        if (i >= 65 && i <= 70)

            return (i - 65) + 10;

        else

            return 0;

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @return
     */

    public static String dump(byte abyte0[]) {

        if (abyte0 == null)

            return "" + abyte0;

        else

            return dump(abyte0, 0, abyte0.length);

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param abyte0
     * @param i
     * @param j
     * @return
     */

    public static String dump(byte abyte0[], int i, int j) {

        if (i < 0)

            i = 0;

        int k = Math.min(abyte0.length, i + j);

        int l = i & 0xfffffff0;

        int i1 = k + 15 & 0xfffffff0;

        StringBuffer stringbuffer = new StringBuffer();

        int j1 = l;

        for (int k1 = l; k1 < i1; k1++) {

            if (k1 % 16 == 0) {

                lineLabel(stringbuffer, k1);

                j1 = k1;

            }

            if (k1 < i || k1 >= k)

                stringbuffer.append("  ");

            else

                stringbuffer.append(asHex(abyte0[k1]));

            if (k1 % 2 == 1)

                stringbuffer.append(' ');

            if (k1 % 16 == 15) {

                stringbuffer.append("  ");

                for (int l1 = j1; l1 < j1 + 16; l1++)

                    if (l1 < i || l1 >= k)

                        stringbuffer.append(' ');

                    else

                        stringbuffer.append(toPrint(abyte0[l1]));

                stringbuffer.append('\n');

            }

        }

        return stringbuffer.toString();

    }

    /**
     * 此处撰写关于该方法的描述信息
     *
     * @param i
     * @return
     */

    public static final boolean isHexChar(int i) {

        switch (i) {

            case 48: // '0'

            case 49: // '1'

            case 50: // '2'

            case 51: // '3'

            case 52: // '4'

            case 53: // '5'

            case 54: // '6'

            case 55: // '7'

            case 56: // '8'

            case 57: // '9'

            case 65: // 'A'

            case 66: // 'B'

            case 67: // 'C'

            case 68: // 'D'

            case 69: // 'E'

            case 70: // 'F'

            case 97: // 'a'

            case 98: // 'b'

            case 99: // 'c'

            case 100: // 'd'

            case 101: // 'e'

            case 102: // 'f'

                return true;

            case 58: // ':'

            case 59: // ';'

            case 60: // '<'

            case 61: // '='

            case 62: // '>'

            case 63: // '?'

            case 64: // '@'

            case 71: // 'G'

            case 72: // 'H'

            case 73: // 'I'

            case 74: // 'J'

            case 75: // 'K'

            case 76: // 'L'

            case 77: // 'M'

            case 78: // 'N'

            case 79: // 'O'

            case 80: // 'P'

            case 81: // 'Q'

            case 82: // 'R'

            case 83: // 'S'

            case 84: // 'T'

            case 85: // 'U'

            case 86: // 'V'

            case 87: // 'W'

            case 88: // 'X'

            case 89: // 'Y'

            case 90: // 'Z'

            case 91: // '['

            case 92: // '\\'

            case 93: // ']'

            case 94: // '^'

            case 95: // '_'

            case 96: // '`'

            default:

                return false;

        }

    }

    private static char toPrint(byte byte0) {

        byte byte1 = byte0;

        if (byte1 < 32 || byte1 > 126)

            return '.';

        else

            return (char) byte1;

    }

    private static void lineLabel(StringBuffer stringbuffer, int i) {

        String s = (new Integer(i)).toString();

        StringBuffer stringbuffer1;

        if (s.length() <= 5) {

            stringbuffer1 = new StringBuffer("    ");

            stringbuffer1.insert(5 - s.length(), s);

            stringbuffer1.setLength(5);

        } else {

            stringbuffer1 = new StringBuffer(s);

        }

        stringbuffer.append(stringbuffer1);

        stringbuffer.append(": ");

    }

}
