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
        identificaComandos();
        System.out.println("Código fonte lido com sucesso.");
    }

    public void identificaComandos() {
        String linhaAtual;
        String[] declaracao;
        for (int flag = 0, i = 1; i < this.arquivo.retornaLinhas().size()-1; i++) {
            // Recebe a linha atual
            linhaAtual = this.arquivo.retornaLinhas().get(i);

            //Verifica se possui ; no final
            try {
                if (!linhaAtual.substring(linhaAtual.length()-1).equals(";"))
                    Miscelanea.limpaTela("Faltando \";\" em:\n\"" + linhaAtual +"\"\n");
            } catch (IndexOutOfBoundsException e) {
                Miscelanea.limpaTela("Faltando \";\" em:\n\"" + linhaAtual +"\"\n");
            }
            // Verifica se é uma declarração de variável
            if (PalavrasReservadas.identificaDeclaracaoDeVariavel(linhaAtual) > 0) {
                // Recebe todas variáveis
                declaracao = linhaAtual.substring(linhaAtual.indexOf(":")+1, linhaAtual.indexOf(";")).split(",");
                // Reconhece variáveis
                for (int j = 0; j < declaracao.length; j++) {
                    variaveis.put(declaracao[j], new Variavel(linhaAtual.substring(0, linhaAtual.indexOf(":")), declaracao[j]));
                }
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
