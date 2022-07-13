package usandoLIB.RSA;

import javax.crypto.Cipher;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSA {

    public static final String algorithm = "RSA";

    public static final String path_chave_privada = "C:/keys/private.key";

    public static final String path_chave_publica = "C:/keys/public.key";

    public static boolean hasChavesNoSO() {

        File chavePrivada = new File(path_chave_privada);
        File chavePublica = new File(path_chave_publica);

        return chavePrivada.exists() && chavePublica.exists();
    }

    public static byte[] encripta(String texto) {
        PublicKey chave = criarChaves.getChavePublica();

        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    public static String descripta(byte[] texto) {
        PrivateKey chave = criarChaves.getChavePrivada();

        byte[] dectyptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

}
