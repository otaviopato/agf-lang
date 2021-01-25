package agf;

/**
 * @autor: Otavio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programacao
 *            Orientada a Objetos aprendidos durante a materia de Programacao I
 *            do Curso Ciencia da Computacao da Universidade Federal da
 *            Fronteira sul Campus Chapeco.
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