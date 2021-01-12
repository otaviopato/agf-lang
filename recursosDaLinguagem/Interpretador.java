package agf;
import agf.Miscelanea;
import agf.Variavel;

public class Interpretador {
    private String linhasDoCodigoFonte;
    private final String cabecalho = "#catucaPai#";
    private final String rodape = "#catucaMae#";

    public Interpretador(String linhasDoCodigoFonte) {
        atribuiLinhasDoCodigoFonte(linhasDoCodigoFonte);
        verificaCabecalho();
        verificaRodape();
        System.out.println("Código fonte lido com sucesso.");
    }

    private void atribuiLinhasDoCodigoFonte(String linhasDoCodigoFonte) {
        this.linhasDoCodigoFonte = linhasDoCodigoFonte;
    }

    private String retornaLinhasDoCodigoFonte() {
        return this.linhasDoCodigoFonte;
    }

    private String retornaCabecalho() {
        return this.cabecalho;
    }

    private String retornaRodape() {
        return this.rodape;
    }

    private void verificaCabecalho() {
        try {
            if ( !retornaLinhasDoCodigoFonte().substring(0, retornaCabecalho().length()).equals(retornaCabecalho()))
                Miscelanea.limpaTela("Cabeçalho não instanciado adequamente.\nCabeçalho tem que ser: -> \"" + retornaCabecalho() + "\".\nE não -> \"" + retornaLinhasDoCodigoFonte().substring(0, retornaCabecalho().length()) + "\".");
            atribuiLinhasDoCodigoFonte(retornaLinhasDoCodigoFonte().substring(retornaCabecalho().length()));
        } catch (IndexOutOfBoundsException e) {
            Miscelanea.limpaTela("Código fonte incorreto:\n\"" + retornaLinhasDoCodigoFonte() +"\"\n");
        }
    }

    private void verificaRodape() {
        try {
            if ( !retornaLinhasDoCodigoFonte().substring(retornaLinhasDoCodigoFonte().length()-retornaRodape().length()).equals(retornaRodape()))
                Miscelanea.limpaTela("Rodapé não instanciado adequamente.\nCabeçalho tem que ser: -> \"" + retornaRodape() + "\".\nE não -> \"" + retornaLinhasDoCodigoFonte().substring(0, retornaCabecalho().length()) + "\".");
            atribuiLinhasDoCodigoFonte( retornaLinhasDoCodigoFonte().substring(0, retornaLinhasDoCodigoFonte().length()-retornaRodape().length()));
        } catch (IndexOutOfBoundsException e) {
            Miscelanea.limpaTela("Código fonte incorreto:\n\"" + retornaLinhasDoCodigoFonte() +"\"\n");
        }
    }
}
