package agf;

/**
 * @autor: Otávio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programação
 *            Orientada à Objetos aprendidos durante a matéria de Programação I
 *            do Curso Ciência da Computação da Universidade Federal da
 *            Fronteira sul Campus Chapecó.
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