package main;
//15674072714476064793527837826854266709745611164021585568778275820994967733576731318696969069319006095210903659088035116439911633521890906582037103909196504511662781886222750693590637206754262526760739127767665204302509573373581161652470416969220879313114002837564130557948639813023762171396282451551559187127253018962407113648493034244058334987607757618098841367095739869065156548028431928369395451812118926211795388407897463159429307313473647522076295273330990513618653184412016027543952303682888849354997792382993978505656440985909180366707535136795871982254190412
import utils.TextChunk;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Encripta {

    public static void main(String[] args) throws IOException {

        TextChunk originalChunk = new TextChunk(getSourceText());
        String codedText = Base64.getEncoder().encodeToString(originalChunk.toString().getBytes());

        BigInteger n = originalChunk.bigIntValue();

        String chunkSize = String.valueOf(TextChunk.blockSize(getKeyModule()));

        BigInteger e = getPublicKey();
        BigInteger module =getKeyModule();
        BigInteger key = getPrivateKey();


        String[] chunks = codedText.split("(?<=\\G.{"+chunkSize+"})");

        Path path = Paths.get("textoCriptografado.txt");


        FileWriter arq = new FileWriter("D:\\Projetos\\n3_seguranca_habbib\\provaN3\\src\\main\\textoCriptografado.txt");
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
