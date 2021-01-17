package agf;
/**
 * 
 */
import agf.Interpretador;
import agf.Miscelanea;

/**
 * @author Otávio Pato
 *
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
