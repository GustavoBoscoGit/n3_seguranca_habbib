package main;

import utils.TextChunk;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decripta {
    public static void main(String[] args) throws IOException {
        TextChunk encriptedText = new TextChunk(getEncriptedText());
        BigInteger key = getPrivateKey();
        BigInteger module =getKeyModule();

        String originalEncondedText = "";



    }
    public static String getEncriptedText() throws IOException {
        String file ="src/resources/textoCriptografado.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String textoObtido = reader.readLine();
        reader.close();

        return textoObtido;
    }
    public static BigInteger getPrivateKey() throws IOException {
        Path path = Paths.get("src/resources/private.txt");
        String privateKey = Files.readAllLines(path).get(1);

        return new BigInteger(privateKey);
    }

    public static BigInteger getKeyModule() throws IOException {
        Path path = Paths.get("src/resources/private.txt");
        String module = Files.readAllLines(path).get(0);

        return new BigInteger(module);
    }

    public static long countLineNumberReader(String fileName) {

        File file = new File(fileName);

        long lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) ;

            lines = lnr.getLineNumber();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }
}
