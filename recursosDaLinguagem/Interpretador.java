package agf;
import agf.Miscelanea;
import agf.Variavel;
import agf.PalavrasReservadas;

import java.util.*;

public class Interpretador {
    private Arquivo arquivo;
    public Hashtable<String, Variavel> variaveis = new Hashtable<String, Variavel>();
    private final String cabecalho = "#catucaPai#";
    private final String rodape = "#catucaMae#";

    public Interpretador(String nomeDoArquivo) {
        atribuiLinhasArquivo(nomeDoArquivo);
        verificaCabecalho();
        verificaRodape();
        identificaComandos(1, this.arquivo.retornaLinhas().size()-1);
        System.out.println("Código fonte lido com sucesso.");
    }

    public void identificaComandos(int atual, int fim) {
        String linhaAtual;
        String[] declaracao;
        for (; atual < fim; atual++) {
            // Recebe a linha atual
            linhaAtual = this.arquivo.retornaLinhas().get(atual);

            //Verifica se possui ; no final
            try {
                if (!linhaAtual.substring(linhaAtual.length()-1).equals(";"))
                    Miscelanea.limpaTela("Faltando \";\" em:\n\"" + linhaAtual +"\"\n");
            } catch (IndexOutOfBoundsException e) {
                Miscelanea.limpaTela("Faltando \";\" em:\n\"" + linhaAtual +"\"\n");
            }
            // Verifica se é uma declaração de variável
            if (PalavrasReservadas.identificaDeclaracaoDeVariavel(linhaAtual) > 0) {
                // Recebe todas variáveis
                declaracao = linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).split(",");
                // Reconhece variáveis
                for (int j = 0; j < declaracao.length; j++) {
                    Variavel novaVariavel = new Variavel(linhaAtual.substring(0, linhaAtual.indexOf(":")), declaracao[j]);
                    this.variaveis.put(declaracao[j], novaVariavel);
                }
            } else if (PalavrasReservadas.identificaImpressao(linhaAtual, variaveis) != "false") {
            } else if (PalavrasReservadas.identificaLeitura(linhaAtual, variaveis) != "false") {
                // Lê Variável
                Scanner entrada = new Scanner(System.in);
                String chave = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
                String conteudo = this.variaveis.get(chave).retornaValor();
                // Lê
                System.out.print("Digite uma variável do tipo \"" + variaveis.get(chave).retornaTipo() + "\" -> ");
                try {
                    if (variaveis.get(chave).retornaTipo().equals(PalavrasReservadas.inteiro))
                        conteudo = String.valueOf(entrada.nextInt());
                    else if (variaveis.get(chave).retornaTipo().equals(PalavrasReservadas.pontoFlutuante))
                        conteudo = Float.toString(entrada.nextFloat()).replace(".", ",");
                    this.variaveis.get(chave).atribuiValor(conteudo);
                } catch (InputMismatchException e) {
                    Miscelanea.limpaTela("Atribuição inválida, tipo esperado: \"" + variaveis.get(chave).retornaTipo() + "\".");
                }
            } else if (PalavrasReservadas.identificaExpressao(linhaAtual, variaveis) != "false") {
                String conteudo = PalavrasReservadas.identificaExpressao(linhaAtual, variaveis);
                String chave = linhaAtual.substring(0, linhaAtual.indexOf("="));
                this.variaveis.get(chave).atribuiValor(conteudo);
            } else {
                Miscelanea.limpaTela("Linha inválida: \n" + linhaAtual);
            }
        }
    }

    private void atribuiLinhasArquivo(String nomeDoArquivo) {
        this.arquivo = new Arquivo(nomeDoArquivo);
    }

    private String retornaCabecalho() {
        return this.cabecalho;
    }

    private String retornaRodape() {
        return this.rodape;
    }

    private void verificaCabecalho() {
        try {
            if ( !this.arquivo.retornaLinha(0).equals(retornaCabecalho()))
                Miscelanea.limpaTela("Cabeçalho não instanciado adequamente.\nCabeçalho tem que ser: -> \"" + retornaCabecalho() + "\".\nE não -> \"" + this.arquivo.retornaLinha(0) + "\".");
        } catch (IndexOutOfBoundsException e) {
            Miscelanea.limpaTela("Código fonte incorreto:\n\"" + this.arquivo +"\"\n");
        }
    }

    private void verificaRodape() {
        try {
            if ( !this.arquivo.retornaLinha(this.arquivo.retornaQuantidadeDeLinhas()-1).equals(retornaRodape()))
                Miscelanea.limpaTela("Rodapé não instanciado adequamente.\nCabeçalho tem que ser: -> \"" + retornaRodape() + "\".\nE não -> \"" + this.arquivo.retornaLinha(this.arquivo.retornaQuantidadeDeLinhas()-1) + "\".");
        } catch (IndexOutOfBoundsException e) {
            Miscelanea.limpaTela("Código fonte incorreto:\n\"" + this.arquivo +"\"\n");
        }
    }
}
