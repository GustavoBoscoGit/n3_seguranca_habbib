package usandoLIB.RSA;

import main.Encripta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (!RSA.hasChavesNoSO())
            criarChaves.GerarKey();

        final String mensagemOriginal = Encripta.getSourceText();

        final byte[] mensagemCriptografada = RSA.encripta(mensagemOriginal);

        final String mensagemDescriptografada = RSA.descripta(mensagemCriptografada);

        System.out.println("Texto Original: " + mensagemOriginal);
        System.out.println("Texto criptografado: " + mensagemCriptografada.toString());
        System.out.println("Texto descriptografado: " + mensagemDescriptografada);
    }
}
