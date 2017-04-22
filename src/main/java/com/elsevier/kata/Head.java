package com.elsevier.kata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Head {
    private static final int ERR_EXIT = 1;
    private static final int MAX_CHARS = 400;
    private static final int NUM_LINES = 10;
    private static final char RETURN_CHAR = '\n';

    public static void main(String args[]) {
        PrintHead printer = new SimplePrintHead();
        if (2 <= args.length) {
            printer = new PrintHeadDecorator(printer);
        }

        try {
            for (String fileName : args) {
                System.out.println(printer.getHead(fileName, NUM_LINES));
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(ERR_EXIT);
        }
    }

    private static interface PrintHead {
        String getHead(String fileName, int maxLines) throws IOException;
    }

    private static class SimplePrintHead implements PrintHead {

        @Override
        public String getHead(String fileName, int maxLines) throws IOException {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder output = new StringBuilder();
            int charCount = 0;
            int lineCount = 0;
            int charAsInt;

            while ((charAsInt = reader.read()) != -1 && lineCount < maxLines && charCount < MAX_CHARS) {
                charCount += 1;
                char currentChar = (char) charAsInt;
                output.append(currentChar);
                if (currentChar == RETURN_CHAR) {
                    lineCount += 1;
                    charCount = 0;
                }
            }
            fileReader.close();
            return output.toString();
        }
    }

    private static class PrintHeadDecorator implements PrintHead {
        private PrintHead decoratedPrintHead;
        private int callCount = 0;

        PrintHeadDecorator(PrintHead decoratedPrintHead) {
            this.decoratedPrintHead = decoratedPrintHead;
        }

        @Override
        public String getHead(String fileName, int maxLines) throws IOException {
            StringBuilder output = new StringBuilder();
            if (callCount != 0) {
                output.append("\n");
            }
            output.append("==> ").append(fileName).append(" <==\n");
            output.append(decoratedPrintHead.getHead(fileName, maxLines));
            callCount += 1;
            return output.toString();
        }
    }
}

