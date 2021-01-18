package agf;
/**
 * 
 */
import agf.Miscelanea;
import agf.Variavel;
//
import java.util.*;

/**
 * @autor: Ot�vio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programa��o
 *            Orientada � Objetos aprendidos durante a mat�ria de Programa��o I
 *            do Curso Ci�ncia da Computa��o da Universidade Federal da
 *            Fronteira sul Campus Chapec�.
 */
public class PalavrasReservadas {
    public static final String inteiro = "bebel";
    public static final String pontoFlutuante = "mendonca";
    public static final String exibe = "carrarinha";
    public static final String leitura = "tuco";
    public static final String lacoRepeticao = "lineu";
    public static final String condicionalSe = "beicola";
    public static final String condicionalEntao = "etelvina";
    // Não deve ser mudado: Operações {"Adição", "Subtração", "Multiplicação", "Divisão", "Resto"}
    public static final String[] operacoes = {"+", "-", "/", "*", "%"};
    // Comparações {"Igualdade", "Diferença", "Maior", "Menor"}
    public static final String[] comparacoes = {"==", "!=", ">", "<"};

    public static String identificaImpressao(String linhaAtual, Map<String, Variavel> vars, boolean executa) {
        boolean newLine = false;

        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("|") == -1)
            return "false";

        // Verifica se é impressão
        if (!linhaAtual.contains(PalavrasReservadas.exibe))
            return "false";

        // Verifica se possui quebra de linha
        if ((PalavrasReservadas.exibe+"ln").equals(linhaAtual.substring(linhaAtual.indexOf(PalavrasReservadas.exibe), linhaAtual.indexOf("|"))))
            newLine = true;
        // Verifica se antes de | a declaração possui a palavra reservada respectiva a impressão
        else if (!PalavrasReservadas.exibe.equals(linhaAtual.substring(linhaAtual.indexOf(PalavrasReservadas.exibe), linhaAtual.indexOf("|"))))
            return "false";

        // Verifica se possui o fechamento da área de conteúdo
        if (!linhaAtual.substring(linhaAtual.length()-2, linhaAtual.length()-1).equals("|"))
            Miscelanea.limpaTela("Sintaxe inválida, falta fechar | em:\n-> " + linhaAtual);
    
        String conteudo = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);

        if (!executa)
            return "false";

        // Imprime Variável
        if (conteudo.indexOf("\"") == -1) {
            verificaPalavrasReservadas(conteudo, linhaAtual);
            if (vars.get(conteudo) == null)
                Miscelanea.limpaTela("A variável: \"" + conteudo + "\", não existe.");
            if (newLine)
                System.out.println(vars.get(conteudo).retornaValor());
            else
                System.out.print(vars.get(conteudo).retornaValor());
            return "variável";
        } else {
            if (newLine)
                System.out.println(conteudo.substring(1, conteudo.length()-1));
            else
                System.out.print(conteudo.substring(1, conteudo.length()-1));
            return "string";
        }
    }

    public static String identificaLeitura(String linhaAtual, Map<String, Variavel> vars) {
        Map<String, Variavel> variaveis = vars;
        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("|") == -1)
            return "false";

        // Verifica se é leitura
        if (!linhaAtual.contains(PalavrasReservadas.leitura))
            return "false";
        
        // Verifica se antes de | a declaração possui a palavra reservada respectiva a um inteiro
        if (!PalavrasReservadas.leitura.equals(linhaAtual.substring(linhaAtual.indexOf(PalavrasReservadas.leitura), linhaAtual.indexOf("|"))))
            return "false";

        // Verifica se possui o fechamento da área de conteúdo
        if (!linhaAtual.substring(linhaAtual.length()-2, linhaAtual.length()-1).equals("|"))
            Miscelanea.limpaTela("Sintaxe inválida, falta fechar | em:\n-> " + linhaAtual);
    
        String conteudo = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);

        // Verifica se está no padrão de variável
        if(!conteudo.matches("[\\w]"))
            return "false";

        // Verifica se não é uma palavra reservada
        verificaPalavrasReservadas(conteudo, linhaAtual);

        String chave = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
        // Verifica se a variável existe
        if (variaveis.get(chave) != null) {
            return "true";
        } else {
            Miscelanea.limpaTela("Variável: \"" + chave + "\" não existe.");
        }
        return "false";
    }

    public static int identificaDeclaracaoDeVariavel(String linhaAtual) {
        // Vefificando se a "declaração" possui : e ;
        if ( linhaAtual.indexOf(":") == -1)
            return 0;

        // Verifica se antes de : a declaração possui a palavra reservada respectiva a um inteiro
        if(!verificaTipoVariveis(linhaAtual.substring(0, linhaAtual.indexOf(":"))))
            return 0;

        // Foi declarado apenas uma variável
        if(linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).matches("[\\w]+")) {
            return 1;
        }

        // Foi declarado mais de uma variável
        return (linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).split(",")).length;
    }

    public static String identificaExpressao(String linhaAtual, Map<String, Variavel> vars) {
        Map<String, Variavel> variaveis = vars;
        // Verifica se não é uma operação de comparação
        if (linhaAtual.contains("=="))
            return "false";

        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("=") == -1)
            return "false";

        if (linhaAtual.indexOf("|") != -1)
            return "false";

        // Verifica se antes de = a declaração possui uma palavra reservada
        verificaPalavrasReservadas(linhaAtual.substring(0, linhaAtual.indexOf("=")), linhaAtual);

        // Verifica se é uma variável existente
        if(vars.get(linhaAtual.substring(0, linhaAtual.indexOf("="))) == null)
            Miscelanea.limpaTela("Variável não declarada previamente: \"" + linhaAtual.substring(0, linhaAtual.indexOf("=")) + "\".");
        String[] valoresOperandos = linhaAtual.substring(linhaAtual.indexOf("=")+1, linhaAtual.length()-1).split("[\\+\\-\\*\\/%]+");
        // Atribuição de valor à uma variável
        if (valoresOperandos.length == 1) {
            if (valoresOperandos[0].matches("[0-9,]+"))
                return valoresOperandos[0];
            if (variaveis.get(valoresOperandos[0]) != null)
                return variaveis.get(valoresOperandos[0]).retornaValor();
        }
        /* Atribuição do valor de uma expressão à uma variável */
        else if (valoresOperandos.length == 2) {
            String valor;
            if (valoresOperandos[0].matches("[.]+") || valoresOperandos[1].matches("[.]+"))
                Miscelanea.limpaTela("Casa decimal deve ser específicada por , e não .");

            if (valoresOperandos[0].matches("[0-9,]+")) {
                valor = valoresOperandos[0];
            } else {
                valor = variaveis.get(valoresOperandos[0]).retornaValor();
            }
            if (valoresOperandos[1].matches("[0-9,]+")) {
                valor = realizaOperacao(valor, valoresOperandos[1], retornaOperacao(linhaAtual));
            } else {
                valor = realizaOperacao(valor, variaveis.get(valoresOperandos[1]).retornaValor(), retornaOperacao(linhaAtual));
            }

            return valor;
        }
        return "false";
    }

    //TODO
    public static String retornaRegexOperacoes() {
        String regexOperacoes = "^.*(";//teste|long|2353|\+).*$
        String aux;
        for (int i = 0; i < PalavrasReservadas.operacoes.length; i++) {
            aux = PalavrasReservadas.operacoes[i];
            if (PalavrasReservadas.operacoes[i].matches("[\\\\^\\$\\|\\[\\]\\?\\{\\}\\(\\)]+"))
                Miscelanea.limpaTela("Nome de operação definido inválido: " + PalavrasReservadas.operacoes[i]);
            if (aux.matches("[\\+]+"))
                aux = aux.replace("+", "\\+");
            if (aux.matches("[\\*]+"))
                aux = aux.replace("*", "\\*");
            if (aux.matches("[\\/]+"))
                aux = aux.replace("/", "\\/");
            regexOperacoes += aux + "|";
        }
        regexOperacoes = regexOperacoes.substring(0, regexOperacoes.length()-1); // Tira | desnecessário
        regexOperacoes += ").*$";
        return regexOperacoes;
    }

    //TODO
    public static boolean operacaoValida(String operacao) {
        for (int i = 0; i < PalavrasReservadas.operacoes.length; i++) {
            if (operacao.equals(PalavrasReservadas.operacoes[i]))
                return true;
        }
        return false;
    }

    private static String retornaOperacao(String linha) {
        if (linha.indexOf(PalavrasReservadas.operacoes[0]) != -1)
            return linha.substring(linha.indexOf(PalavrasReservadas.operacoes[0]), linha.indexOf(PalavrasReservadas.operacoes[0])+1);
        if (linha.indexOf(PalavrasReservadas.operacoes[1]) != -1)
            return linha.substring(linha.indexOf(PalavrasReservadas.operacoes[1]), linha.indexOf(PalavrasReservadas.operacoes[1])+1);
        if (linha.indexOf(PalavrasReservadas.operacoes[2]) != -1)
            return linha.substring(linha.indexOf(PalavrasReservadas.operacoes[2]), linha.indexOf(PalavrasReservadas.operacoes[2])+1);
        if (linha.indexOf(PalavrasReservadas.operacoes[3]) != -1)
            return linha.substring(linha.indexOf(PalavrasReservadas.operacoes[3]), linha.indexOf(PalavrasReservadas.operacoes[3])+1);
        if (linha.indexOf(PalavrasReservadas.operacoes[4]) != -1)
            return linha.substring(linha.indexOf(PalavrasReservadas.operacoes[4]), linha.indexOf(PalavrasReservadas.operacoes[4])+1);
        return "";
    }

    private static String realizaOperacao(String valor1, String valor2, String operacao) {
        String resultado = "";
        if (!(valor1.indexOf(",") == valor1.indexOf(",")))
            Miscelanea.limpaTela("Operação inválida, tipos diferentes.");

        Float valorConvertido1 = Float.parseFloat(valor1.replace(",", "."));
        Float valorConvertido2 = Float.parseFloat(valor2.replace(",", "."));
        if (operacao.equals(PalavrasReservadas.operacoes[0]))
            resultado = Float.toString(valorConvertido1+valorConvertido2);
        else if (operacao.equals(PalavrasReservadas.operacoes[1]))
            resultado = Float.toString(valorConvertido1-valorConvertido2);
        else if (operacao.equals(PalavrasReservadas.operacoes[2]))
            resultado = Float.toString(valorConvertido1*valorConvertido2);
        else if (operacao.equals(PalavrasReservadas.operacoes[3]))
            resultado = Float.toString(valorConvertido1/valorConvertido2);
        else if (operacao.equals(PalavrasReservadas.operacoes[4]))
            resultado = Float.toString(valorConvertido1%valorConvertido2);

        if (valor1.indexOf(",") != -1 && valor2.indexOf(",") != -1)
            return resultado.replace(".", ",");
        else if (valor1.matches("[0-9]+") && valor2.matches("[0-9]+"))
            return resultado.substring(0, resultado.indexOf("."));
        Miscelanea.limpaTela("Operação inválida:\n-> Valores = [" + valor1 + " -- " + valor2 + "];\nOperação = \"" + operacao + "\"\n");
        return "Nunca retorna";
    }

    public static boolean realizaComparacao(String comparacao, Map<String, Variavel> variaveis) {
        String[] identifica = (comparacao.substring(0, comparacao.length()-1)).split("(==|!=|<|>)");
        if (identifica.length != 2)
            Miscelanea.limpaTela("Comparação inválida: " + comparacao);
        String operacao = comparacao.substring(identifica[0].length(), comparacao.length()-identifica[1].length()-1);
        boolean resultado = false;

        Float valorConvertido1 = 0.0f;
        Float valorConvertido2 = 0.0f;

        // Valor 1
        if (!identifica[0].matches("[0-9,]+")) {
            // Verifica se a variável existe
            if (variaveis.get(identifica[0]) == null)
                Miscelanea.limpaTela("Variável não existe: " + identifica[0]);
            valorConvertido1 = Float.parseFloat((variaveis.get(identifica[0]).retornaValor()).replace(",", "."));
        } else
            valorConvertido1 = Float.parseFloat(identifica[0].replace(",", "."));
        // Valor 2 
        if (!identifica[1].matches("[0-9,]+")) {
            if (variaveis.get(identifica[1]) == null)
                Miscelanea.limpaTela("Variável não existe: " + identifica[1]);
            valorConvertido2 = Float.parseFloat((variaveis.get(identifica[1]).retornaValor()).replace(",", "."));
        } else
            valorConvertido2 = Float.parseFloat(identifica[1].replace(",", "."));

        // Verifica tipos
        if (!(valorConvertido1.getClass() == valorConvertido2.getClass()))
            Miscelanea.limpaTela("Comparação inválida, tipos diferentes.");
    
        if (operacao.equals(PalavrasReservadas.comparacoes[0]))
            return (Float.compare(valorConvertido1, valorConvertido2) == 0 ? true : false);
        else if (operacao.equals(PalavrasReservadas.comparacoes[1]))
            return (Float.compare(valorConvertido1, valorConvertido2) == 0 ? false : true);
        else if (operacao.equals(PalavrasReservadas.comparacoes[2]))
            return (Float.compare(valorConvertido1, valorConvertido2) > 0 ? true : false);
        else if (operacao.equals(PalavrasReservadas.comparacoes[3]))
            return (Float.compare(valorConvertido1, valorConvertido2) < 0 ? true : false);

        Miscelanea.limpaTela("Comparação inválida:\n-> Valores = [" + identifica[0] + " -- " + identifica[1] + "];\nOperação = \"" + operacao + "\"\n");
        return false;
    }

    private static boolean verificaTipoVariveis(String tipoVariavel) {
        String[] tiposVariaveis = {
            inteiro,
            pontoFlutuante
        };
        return Arrays.stream(tiposVariaveis).anyMatch(tipoVariavel::equals);
    }

    private static void verificaPalavrasReservadas(String palavra, String linhaAtual) {
        String[] reservadas = {
            inteiro,
            pontoFlutuante,
            exibe,
            exibe+"ln",
            leitura,
            lacoRepeticao,
            condicionalSe,
            condicionalEntao,
            operacoes[0],
            operacoes[1],
            operacoes[2],
            operacoes[3],
            operacoes[4],
            comparacoes[0],
            comparacoes[1],
            comparacoes[2],
            comparacoes[3]
        };
        if(Arrays.stream(reservadas).anyMatch(palavra::equals))
            Miscelanea.limpaTela("Uso inválido de palavra reservada:\n-> " + linhaAtual);
    }

}