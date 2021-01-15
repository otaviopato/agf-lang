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

    public static String identificaImpressao(String linhaAtual) {
        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("|") == -1)
            return "false";

        
        // Verifica se antes de | a declaração possui a palavra reservada respectiva a um inteiro
        if (!PalavrasReservadas.exibe.equals(linhaAtual.substring(linhaAtual.indexOf(PalavrasReservadas.exibe), linhaAtual.indexOf("|"))))
            return "false";

        // Verifica se possui o fechamento da área de conteúdo
        if (!linhaAtual.substring(linhaAtual.length()-2, linhaAtual.length()-1).equals("|"))
            Miscelanea.limpaTela("Sintaxe inválida, falta fechar | em:\n-> " + linhaAtual);
    
        String conteudo = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
        
        // Imprime Variável
        if (conteudo.indexOf("\"") == -1) {
            if (verificaPalavrasReservadas(conteudo))
                return "false";
            return conteudo;
        } else {
            return conteudo;
        }
    }

    public static int identificaDeclaracaoDeVariavel(String linhaAtual) {
        // Vefificando se a "declaração" possui : e ;
        if ( linhaAtual.indexOf(":") == -1)
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
        String[] tiposVariaveis = {
            inteiro,
            pontoFlutuante
        };
        return Arrays.stream(tiposVariaveis).anyMatch(tipoVariavel::equals);
    }

    private static boolean verificaPalavrasReservadas(String palavra) {
        String[] palavrasReservadas = {
            inteiro,
            pontoFlutuante,
            exibe,
            lacoRepeticao,
            condicionalSe,
            condicionalEntao
        };
        return Arrays.stream(palavrasReservadas).anyMatch(palavra::equals);
    }

}