package com.elsevier.kata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class Head {
    private static final int ERR_EXIT = 1;
    private static final int MAX_CHARS = 512;
    private static final int NUM_LINES = 10;
    private static final char RETURN_CHAR = '\n';

    public static void main(String args[]) {
        PrintHead printer = new SimplePrintHead();
        if (2 <= args.length) {
            printer = new PrintHeadDecorator(printer);
        }

        for (String fileName : args) {
            try (Reader fileReader = new FileReader(fileName)) {
                printer.getHead(fileReader, fileName, NUM_LINES);
            } catch (Exception e) {
                System.err.println(e);
                System.exit(ERR_EXIT);
            }
        }

    }

    private static interface PrintHead {
        void getHead(Reader reader, String fileName, int maxLines) throws IOException;
    }

    private static class SimplePrintHead implements PrintHead {

        @Override
        public void getHead(Reader reader, String fileName, int maxLines) throws IOException {
            BufferedReader br = new BufferedReader(reader);
            int lineCount = 0;
            int charByte;

            while ((charByte = br.read()) != -1 && lineCount < maxLines) {
                if ((char) charByte == RETURN_CHAR) {
                    lineCount += 1;
                }
                System.out.write(charByte);
            }
            System.out.flush();
        }
    }

    private static class PrintHeadDecorator implements PrintHead {
        private PrintHead decoratedPrintHead;
        private int callCount = 0;

        PrintHeadDecorator(PrintHead decoratedPrintHead) {
            this.decoratedPrintHead = decoratedPrintHead;
        }

        @Override
        public void getHead(Reader reader, String fileName, int maxLines) throws IOException {
            if (callCount != 0) {
                System.out.println("");
            }
            System.out.println("==> " + fileName + " <==");
            decoratedPrintHead.getHead(reader, fileName, maxLines);
            callCount += 1;
        }
    }
}

