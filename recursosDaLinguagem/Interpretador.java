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
            } else if (PalavrasReservadas.identificaImpressao(linhaAtual) != "false") {
                String conteudo = PalavrasReservadas.identificaImpressao(linhaAtual);
                // String;
                if (conteudo.indexOf("\"") != -1) {
                        System.out.println(conteudo.substring(1, conteudo.length()-1));
                    }
                /* Variável */ 
                else {
                    if (this.variaveis.get(conteudo) == null)
                        Miscelanea.limpaTela("A variável: \"" + conteudo + "\", não existe.");
                    System.out.println(this.variaveis.get(conteudo));
                }
            }/*
            else {
                Miscelanea.limpaTela("Linha inválida: \n" + linhaAtual);
            }*/
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
