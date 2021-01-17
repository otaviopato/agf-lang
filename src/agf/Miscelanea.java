package agf;

/**
 * @author devp
 *
 */

public class Miscelanea {
    public static void limpaTela(String mensagem) {
        for(int i = 0; i < 2; i++)
            System.out.println();
        System.out.println(mensagem);
        System.exit(1);
    }
}