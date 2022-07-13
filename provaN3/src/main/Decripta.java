package main;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decripta {
    public static void main(String[] args) {

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
}
