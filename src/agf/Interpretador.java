package agf;
/**
 * 
 */
import agf.Miscelanea;
import agf.Variavel;
import agf.PalavrasReservadas;
//
import java.util.*;


/**
 * @author Otávio Pato
 *
 */
public class Interpretador {
    private Arquivo arquivo;
    public Map<String, Variavel> variaveis = new HashMap<String, Variavel>();
    private int aberturasDeEscopo;
    private int fechamentosDeEscopo;
    private final String cabecalho = "#catucaPai#";
    private final String rodape = "#catucaMae#";

    public Interpretador(String nomeDoArquivo) {
        atribuiAberturasDeEscopo(0);
        atribuiFechamentosDeEscopo(0);
        atribuiLinhasArquivo(nomeDoArquivo);
        verificaCabecalho();
        verificaRodape();
        identificaComandos(1, this.arquivo.retornaLinhas().size()-1);
        System.out.println("Código fonte lido com sucesso.");
    }

    public void atribuiAberturasDeEscopo(int aberturasDeEscopo) {
        this.aberturasDeEscopo = aberturasDeEscopo;
    }

    public void atribuiFechamentosDeEscopo(int fechamentosDeEscopo) {
        this.fechamentosDeEscopo = fechamentosDeEscopo;
    }

    public int retornaAberturasDeEscopo() {
        return this.aberturasDeEscopo = aberturasDeEscopo;
    }

    public int retornaFechamentosDeEscopo() {
        return this.fechamentosDeEscopo = fechamentosDeEscopo;
    }

    public void validaAberturasEFechamentosDeEscopo() {
        if (this.retornaAberturasDeEscopo() != this.retornaFechamentosDeEscopo())
            Miscelanea.limpaTela("Erro de escopo, faltam ou sobram declarações de escopo.");
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
            } else if (this.identificaEscopo(linhaAtual)) {
            } else {
                Miscelanea.limpaTela("Linha inválida: \n" + linhaAtual);
            }
        }
        this.validaAberturasEFechamentosDeEscopo();
    }

    private boolean identificaEscopo(String linhaAtual) {
        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("|") == -1 && !linhaAtual.contains(PalavrasReservadas.condicionalEntao) && !linhaAtual.equals("};"))
            return false;

        // Verifica abertura de escopo
        if (linhaAtual.substring(linhaAtual.length()-2).equals("{;")) {
            this.atribuiAberturasDeEscopo((this.retornaAberturasDeEscopo()+1));
            if (linhaAtual.indexOf("|") != -1) {
                // Verifica se possui o fechamento da área de conteúdo
                if (!linhaAtual.substring(linhaAtual.length()-3, linhaAtual.length()-2).equals("|"))
                    Miscelanea.limpaTela("Sintaxe inválida, falta fechar | em:\n-> " + linhaAtual);
                String comparacao = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
                System.out.println(linhaAtual + " = " + PalavrasReservadas.realizaComparacao(comparacao, this.variaveis));
            }
            return true;
        } else if (linhaAtual.substring(linhaAtual.length()-2).equals("};")) {
            this.atribuiFechamentosDeEscopo((this.retornaFechamentosDeEscopo()+1));
            return true;
        }
        return false;
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
