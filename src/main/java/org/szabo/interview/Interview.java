package org.szabo.interview;

public class Interview {
    private static String INVALID_CHARACTERS_REGEX = "[^a-zA-Z0-9]";

    public static String encode(String plainText, int lanes) {
        String clearedText = cleanCharacters(plainText);
        Character[][] table = new Character[lanes][clearedText.length()];
        int row = 0;
        int column = 0;
        boolean goingDown = true;
        for (int i = 0; i < clearedText.length(); i++) {
            table[row][column] = clearedText.charAt(i);
            if (row == 0) {
                goingDown = true;
            } else if (row == lanes - 1) {
                goingDown = false;
            }
            if (goingDown) {
                row++;
            } else {
                row--;
            }
            column++;
        }
        return printEncoded(table);
    }

    public static String decode(String encodedText, int lanes) {
        String clearedText = encodedText.replace(" ", "");
        Integer[][] table = getCodePattern(lanes, clearedText);
        return readDecoded(table, clearedText);
    }

    private static Integer[][] getCodePattern(final int lanes, final String clearedText) {
        Integer[][] table = new Integer[lanes][clearedText.length()];
        int row = 0;
        int column = 0;
        boolean goingDown = true;
        for (int i = 0; i < clearedText.length(); i++) {
            table[row][column] = i;
            if (row == 0) {
                goingDown = true;
            } else if (row == lanes - 1) {
                goingDown = false;
            }
            if (goingDown) {
                row++;
            } else {
                row--;
            }
            column++;
        }
        return table;
    }

    private static String readDecoded(final Integer[][] table, String text) {
        String result = " ".repeat(text.length());
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[0].length; k++) {
                final Integer originalLocation = table[i][k];
                if (originalLocation != null) {
                    if (originalLocation == 0) {
                        result = text.charAt(index) + result.substring(originalLocation + 1);
                    } else if (originalLocation == text.length() - 1) {
                        result = result.substring(0, text.length() - 1) + text.charAt(index);
                    } else {
                        result = result.substring(0, originalLocation) + text.charAt(index) + result.substring(originalLocation + 1, text.length());
                    }
                    index++;
                }
            }
        }
        return result;
    }

    private static String printEncoded(final Character[][] table) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[0].length; k++) {
                if (table[i][k] != null) {
                    result.append(table[i][k]);
                    if (result.length() % 6 == 5) {
                        result.append(" ");
                    }
                }
            }
        }
        return result.toString().trim();
    }

    private static String cleanCharacters(String plainText) {
        return plainText.replaceAll(INVALID_CHARACTERS_REGEX, "");
    }

}
