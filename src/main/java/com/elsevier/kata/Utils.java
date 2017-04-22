package com.elsevier.kata;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class Utils {
    final static int MAXCHARS = 1024;
    final static Byte[] printableArray = { 0x09, 0x0A, 0x0D };
    final static List<Byte> PRINTABLE = Arrays.asList(printableArray);

    public boolean isText(final Reader reader) throws IOException {
        reader.mark(MAXCHARS);
        int charCount = 0;
        int printableCount = 0;
        int charInt;
        while ((charInt = reader.read()) != -1 && charCount < MAXCHARS) {
            if (charInt == 0) {
                return false;
            }
            if (isPrintable(charInt)) {
                printableCount += 1;
            }
            charCount += 1;
        }
        return (0 < charCount) && (0.75 <= ((float) printableCount / (float) charCount));
    }

    private boolean isPrintable(int charInt) {
        return (0x20 <= charInt && charInt <= 0x7E) || (PRINTABLE.contains(charInt));
    }
}
