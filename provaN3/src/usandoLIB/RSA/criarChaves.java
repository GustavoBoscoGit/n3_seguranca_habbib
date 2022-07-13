package usandoLIB.RSA;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class criarChaves {

    public static final String algorithm = "RSA";

    public static final String path_chave_privada = "C:/keys/private.key";

    public static final String path_chave_publica = "C:/keys/public.key";

    public static void GerarKey(){
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(path_chave_privada);
            File publicKeyFile = new File(path_chave_publica);

            //cria o arquivo caso não exista
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }

            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }

            publicKeyFile.createNewFile();

            //salva a chave publica no arquivo criado acima
            ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            chavePublicaOS.writeObject(key.getPublic());
            chavePublicaOS.close();

            //salva a chave privada no arquivo criado acima
            ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            chavePrivadaOS.writeObject(key.getPrivate());
            chavePrivadaOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PublicKey getChavePublica() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RSA.path_chave_publica));
            return (PublicKey) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getChavePrivada() {
        try {
            ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream(RSA.path_chave_privada));
            return (PrivateKey) inputStream2.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
