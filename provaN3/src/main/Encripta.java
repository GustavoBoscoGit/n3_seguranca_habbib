package main;

import utils.TextChunk;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


public class Encripta {

    public static void main(String[] args) throws IOException {

        TextChunk originalChunk = new TextChunk(getSourceText());
        String codedText = Base64.getEncoder().encodeToString(originalChunk.toString().getBytes());

        String chunkSize = String.valueOf(TextChunk.blockSize(getKeyModule()));

        BigInteger e = getPublicKey();
        BigInteger module =getKeyModule();

        String[] chunks = codedText.split("(?<=\\G.{"+chunkSize+"})");

        FileWriter arq = new FileWriter("D:\\Projetos\\n3_seguranca_habbib\\provaN3\\src\\resources\\textoCriptografado.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        for (String chunk: chunks){
            TextChunk chunkToBigInt = new TextChunk(chunk);
            BigInteger bigIntChunk = chunkToBigInt.bigIntValue();
            BigInteger encodedChunk = bigIntChunk.modPow(e, module);
            gravarArq.printf("%d %n",encodedChunk);
        }
        arq.close();
    }


    public static String getSourceText() throws IOException {
        String file ="src/resources/source.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String textoObtido = reader.readLine();
        reader.close();

        return textoObtido;
    }

    public static BigInteger getPublicKey() throws IOException {
        Path path = Paths.get("src/resources/public.txt");
        String publicKey = Files.readAllLines(path).get(1);

        return new BigInteger(publicKey);
    }

    public static BigInteger getKeyModule() throws IOException {
        Path path = Paths.get("src/resources/private.txt");
        String module = Files.readAllLines(path).get(0);

        return new BigInteger(module);
    }

}
