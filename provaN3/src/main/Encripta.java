package main;
//15674072714476064793527837826854266709745611164021585568778275820994967733576731318696969069319006095210903659088035116439911633521890906582037103909196504511662781886222750693590637206754262526760739127767665204302509573373581161652470416969220879313114002837564130557948639813023762171396282451551559187127253018962407113648493034244058334987607757618098841367095739869065156548028431928369395451812118926211795388407897463159429307313473647522076295273330990513618653184412016027543952303682888849354997792382993978505656440985909180366707535136795871982254190412
import utils.TextChunk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;


public class Encripta {

    public static void main(String[] args) throws IOException {

        TextChunk originalChunk = new TextChunk(getSourceText());
        String codedText = Base64.getEncoder().encodeToString(originalChunk.toString().getBytes());
        System.out.println(codedText);

        BigInteger n = originalChunk.bigIntValue();

        String chunkSize = String.valueOf(TextChunk.blockSize(getKeyModule()));

        System.out.println(chunkSize);

        //System.out.println("biginteger value of: " + originalChunk + "\n" + " is: " + n);

        BigInteger e = getPublicKey();
        BigInteger module =getKeyModule();
        BigInteger key = getPrivateKey();

        //String chunkOriginal = String.valueOf(encodedChunk.modPow(key, module));

        //for (int i =0; i <= codedText.length(); i++){System.out.println(Arrays.toString(codedText.split(chunkSize)));}


        while(codedText.length() > Integer.valueOf(chunkSize)) {
            System.out.println(codedText.substring(0, Integer.valueOf(chunkSize)));
            codedText = codedText.substring(Integer.valueOf(chunkSize));
        }

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
