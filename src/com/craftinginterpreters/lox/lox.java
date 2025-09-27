package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class lox {
    public static void main(String[] args) throws IOException {
        if ( args.length > 1 ) {
            System.out.println("Usage : jlox [script]");
            System.exit(64);
        } else if ( args.length == 1 ) {
            runFile( args[0] );
        } else {
            runPrompt();
        }
    }

    private static void runFile( final String path ) throws IOException {
        final byte[] bytes = Files.readAllBytes( Paths.get( path ) );
        run( new String( bytes, Charset.defaultCharset()));
    }

    private static void runPrompt() throws IOException {
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.println("> ");
            final String line = reader.readLine();
            if (line == null) break;
            run(line);
        }
    }

    private static void run(final String source) {
        final Scanner scanner = new Scanner(source);
        // TODO : the below was .scanTokens() but that may be deprecated?
        final List<Token> tokens = scanner.tokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void error(final int line, final String where, final String message) {
        
    }
}