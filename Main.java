import agf.Variavel;
import agf.Interpretador;
import agf.Miscelanea;

public class Main {
    public static void main(String[] args) {
            //Interpretador interpretador = new Interpretador("testes/expressoes.agf");
            if (!(args.length > 0))
                Miscelanea.limpaTela("Não foi passado o argumento do caminho de código fonte.");
            Interpretador interpretador = new Interpretador(args[0]);
            /*
                TODO: Ler os primeiros caracteres de uma linha e verificar se eles compõem exatamente alguma palavra reservada:
                    mendonca:
                    bebel:
                    carrarinha|
                    taxi|
                    beicola|
                    etelvina{;
                TODO: "Como comparar partes de strigs", "Algoritmos para comparar prefixos"
            */
            //Variavel variavel = new Variavel("mendonca", "teste", "0,0");
    }
}
