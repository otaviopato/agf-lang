package agf;

/**
 * 
 */
import agf.Interpretador;
import agf.Miscelanea;
import agf.Musica;

/**
 * @autor: Otávio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programação
 *            Orientada à Objetos aprendidos durante a matéria de Programação I
 *            do Curso Ciência da Computação da Universidade Federal da
 *            Fronteira sul Campus Chapecó.
 */
public class Main {
    public static void main(String[] args) {
        // Interpretador interpretador = new Interpretador("testes/expressoes.agf");
        if (!(args.length > 0))
            Miscelanea.limpaTela("NÃ£o foi passado o argumento do caminho de cÃ³digo fonte.");
        Interpretador interpretador = new Interpretador(args[0]);
        if (args.length == 2) {
            args[1].equals("-m");
            Musica tocaMusica = new Musica();
            tocaMusica.play(Musica.caminho);
        }
        /*
         * TODO: Ler os primeiros caracteres de uma linha e verificar se eles compÃµem
         * exatamente alguma palavra reservada: taxi| beicola{; }; };etelvina{;
         */
    }
}
