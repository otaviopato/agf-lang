package agf;

/**
 * @autor: Ot�vio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programa��o
 *            Orientada � Objetos aprendidos durante a mat�ria de Programa��o I
 *            do Curso Ci�ncia da Computa��o da Universidade Federal da
 *            Fronteira sul Campus Chapec�.
 */
public class Miscelanea {
    public static void limpaTela(String mensagem) {
    	int spaces = 2;
        for(int i = 0; i < spaces; i++)
            System.out.println();
        System.out.println(mensagem);
        System.exit(1);
    }
}