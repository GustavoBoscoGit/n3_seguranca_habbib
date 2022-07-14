package main;

import utils.TextChunk;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Decripta {
    public static void main(String[] args) throws IOException {
        BigInteger key = getPrivateKey();
        BigInteger module =getKeyModule();

        String originalEncondedText = "";

        String sourceString = readSourceFile("D:\\Projetos\\n3_seguranca_habbib\\provaN3\\src\\resources\\textoCriptografado.txt");

        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();

        String[] chunks = sourceString.split("\n");
        for (String chunk : chunks){
            TextChunk chunkToBigInt = new TextChunk(chunk);
            BigInteger encodedChunk = chunkToBigInt.bigIntValue();
            BigInteger originalChunk = encodedChunk.modPow(key, module);
            byte[] bigIntegerBytes = originalChunk.toByteArray();
            String base64EncodedBigIntegerBytes = encoder.encodeToString(bigIntegerBytes);
            originalEncondedText += base64EncodedBigIntegerBytes;
        }
        System.out.println(originalEncondedText);
        String decryptedText = String.valueOf(Base64.getDecoder().decode(originalEncondedText));
        System.out.println(decryptedText);






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

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        return getString(inputStream);
    }

    private static String readSourceFile(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        return readFromInputStream(fileInputStream);
    }

    public static String getString(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
