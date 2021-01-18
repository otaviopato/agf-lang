package agf;
/**
 * 
 */
import agf.Interpretador;
import agf.Miscelanea;

/**
 * @autor: Ot�vio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programa��o
 *            Orientada � Objetos aprendidos durante a mat�ria de Programa��o I
 *            do Curso Ci�ncia da Computa��o da Universidade Federal da
 *            Fronteira sul Campus Chapec�.
 */
public class Main {
    public static void main(String[] args) {
            //Interpretador interpretador = new Interpretador("testes/expressoes.agf");
            if (!(args.length > 0))
                Miscelanea.limpaTela("Não foi passado o argumento do caminho de código fonte.");
            Interpretador interpretador = new Interpretador(args[0]);
            /*
                TODO: Ler os primeiros caracteres de uma linha e verificar se eles compõem exatamente alguma palavra reservada:
                    taxi|
                    beicola{;
                    };
                    };etelvina{;
            */
    }
}
