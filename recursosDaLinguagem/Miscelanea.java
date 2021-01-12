package agf;
import java.io.IOException;
import java.util.Scanner;

public class Miscelanea {
    public static void limpaTela(String mensagem) {
        for(int i = 0; i < 1000; i++)
            System.out.println();
        System.out.println(mensagem);
        System.exit(1);
    }
}