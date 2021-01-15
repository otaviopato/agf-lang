package agf;
import agf.Miscelanea;
import agf.Variavel;

import java.util.*;

public class PalavrasReservadas {
    public static final String inteiro = "bebel";
    public static final String pontoFlutuante = "mendonca";
    public static final String exibe = "carrarinha";
    public static final String lacoRepeticao = "taxi";
    public static final String condicionalSe = "beicola";
    public static final String condicionalEntao = "etelvina";

    public static int identificaDeclaracaoDeVariavel(String linhaAtual) {
        // Vefificando se a "declaração" possui : e ;
        if ( linhaAtual.indexOf(":") == -1 || linhaAtual.indexOf(";") == -1)
            return 0;

        // Verifica se antes de : a declaração possui a palavra reservada respectiva a um inteiro
        if(!verificaTipoVariveis(linhaAtual.substring(0, linhaAtual.indexOf(":"))))
            return 0;

        // Foi declarado apenas uma variável
        if(linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).matches("[a-zA-Z_0-9]+")) {
            return 1;
        }

        // Foi declarado mais de uma variável
        return (linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).split(",")).length;
    }

    private static boolean verificaTipoVariveis(String tipoVariavel) {
        String[] tiposVariaveis = {inteiro, pontoFlutuante};
        return Arrays.stream(tiposVariaveis).anyMatch(tipoVariavel::equals);
    }
}