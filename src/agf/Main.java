package agf;
/**
 * 
 */
import agf.Interpretador;
import agf.Miscelanea;

/**
 * @autor: Ot·vio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programaÁ„o
 *            Orientada ‡ Objetos aprendidos durante a matÈria de ProgramaÁ„o I
 *            do Curso CiÍncia da ComputaÁ„o da Universidade Federal da
 *            Fronteira sul Campus ChapecÛ.
 */
public class Main {
    public static void main(String[] args) {
            //Interpretador interpretador = new Interpretador("testes/expressoes.agf");
            if (!(args.length > 0))
                Miscelanea.limpaTela("N√£o foi passado o argumento do caminho de c√≥digo fonte.");
            Interpretador interpretador = new Interpretador(args[0]);
            /*
                TODO: Ler os primeiros caracteres de uma linha e verificar se eles comp√µem exatamente alguma palavra reservada:
                    taxi|
                    beicola{;
                    };
                    };etelvina{;
            */
    }
}
