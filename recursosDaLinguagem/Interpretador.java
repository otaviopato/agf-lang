package agf;
import agf.Miscelanea;
import agf.Variavel;

import java.util.*;

public class Interpretador {
    private Arquivo arquivo;
    Dictionary variaveis = new Hashtable<String, Variavel>();
    private final String cabecalho = "#catucaPai#";
    private final String rodape = "#catucaMae#";

    public Interpretador(String nomeDoArquivo) {
        atribuiLinhasArquivo(nomeDoArquivo);
        verificaCabecalho();
        verificaRodape();
        tokeniza();
        System.out.println("Código fonte lido com sucesso.");
    }

    private void tokeniza() { 
        
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
