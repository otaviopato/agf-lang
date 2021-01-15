import agf.Variavel;
import agf.Interpretador;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Interpretador interpretador = new Interpretador("testes/aninhamento.agf");
        interpretador.identificaPalavrasReservadas();
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
