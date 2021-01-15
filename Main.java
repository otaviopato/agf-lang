import agf.Variavel;
import agf.Interpretador;
import agf.Miscelanea;

public class Main {
    public static void main(String[] args) {
        try {
        //Interpretador interpretador = new Interpretador("testes/expressoes.agf");
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
        } catch (IndexOutOfBoundsException e) {
            Miscelanea.limpaTela("Caminho para código fonte não econtrado.");
        }
    }
}
