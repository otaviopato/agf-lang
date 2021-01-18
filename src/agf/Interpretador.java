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
    public int condicionalAberto;
    public int lacoAberto;
    private final String cabecalho = "#catucaPai#";
    private final String rodape = "#catucaMae#";

    public Interpretador(String nomeDoArquivo) {
        this.condicionalAberto = 0;
        this.lacoAberto = 0;
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
            } else if (PalavrasReservadas.identificaImpressao(linhaAtual, variaveis, true) != "false") {
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
            } else if (this.identificaEscopo(linhaAtual, atual, fim, true)) {
            } else {
                Miscelanea.limpaTela("Linha inválida: \n" + linhaAtual);
            }
        }
        this.validaAberturasEFechamentosDeEscopo();
    }

    /*
        @executa: true se os comandos identificados devem ser executados, ou false caso devam ser ignorados
     */
    public void identificaComandosEmEscopo(boolean executa, int atual, int fim) {
        String[] declaracao;
        String linhaAtual;
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
                if (executa) {
                    // Reconhece variáveis
                    for (int j = 0; j < declaracao.length; j++) {
                        Variavel novaVariavel = new Variavel(linhaAtual.substring(0, linhaAtual.indexOf(":")), declaracao[j]);
                        this.variaveis.put(declaracao[j], novaVariavel);
                    }
                }
            } else if (PalavrasReservadas.identificaImpressao(linhaAtual, variaveis, executa) != "false") {
            } else if (PalavrasReservadas.identificaLeitura(linhaAtual, variaveis) != "false") {
                // Lê Variável
                Scanner entrada = new Scanner(System.in);
                String chave = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
                String conteudo = this.variaveis.get(chave).retornaValor();
                if (executa) {
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
                }
            } else if (PalavrasReservadas.identificaExpressao(linhaAtual, variaveis) != "false") {
                String conteudo = PalavrasReservadas.identificaExpressao(linhaAtual, variaveis);
                String chave = linhaAtual.substring(0, linhaAtual.indexOf("="));
                if (executa)
                    this.variaveis.get(chave).atribuiValor(conteudo);
            } else if (identificaEscopo(linhaAtual, atual, fim, executa)) {
            } else {
                Miscelanea.limpaTela("Linha inválida: \n" + linhaAtual);
            }
        }
        this.validaAberturasEFechamentosDeEscopo();
    }

    private boolean identificaEscopo(String linhaAtual, int atual, int fim, boolean executa) {
        if (linhaAtual.equals("};")) {
            this.atribuiFechamentosDeEscopo((this.retornaFechamentosDeEscopo()+1));
            return true;
        }

        // Verifica se possui o caractere de inicio de conteúdo
        if (linhaAtual.indexOf("|") == -1 && !(PalavrasReservadas.condicionalEntao+"{;").equals(linhaAtual))
            return false;
        if (linhaAtual.substring(linhaAtual.length()-3).equals("|{;")) {
            this.atribuiAberturasDeEscopo((this.retornaAberturasDeEscopo()+1));
            String comparacao = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-3);
            //PalavrasReservadas.realizaComparacao(comparacao, this.variaveis);
            return true;
        }
        if (linhaAtual.equals(PalavrasReservadas.condicionalEntao+"{;")) {
            this.atribuiAberturasDeEscopo((this.retornaAberturasDeEscopo()+1));
            return true;
        }
/*
        // Verifica abertura de escopo
        if (linhaAtual.substring(linhaAtual.length()-2).equals("{;")) {
            this.atribuiAberturasDeEscopo((this.retornaAberturasDeEscopo()+1));
            // Verifica
            if (linhaAtual.indexOf("|") != -1 || (PalavrasReservadas.condicionalEntao+"{;").equals(linhaAtual)) {
                // Verifica se possui o fechamento da área de conteúdo
                if (!linhaAtual.substring(linhaAtual.length()-3, linhaAtual.length()-2).equals("|") && !(PalavrasReservadas.condicionalEntao+"{;").equals(linhaAtual))
                    Miscelanea.limpaTela("Sintaxe inválida, falta fechar | em:\n-> " + linhaAtual);
                String comparacao = linhaAtual.substring(linhaAtual.indexOf("|")+1, linhaAtual.length()-2);
                // Identifica o laço de repetição
                if (PalavrasReservadas.lacoRepeticao.equals(linhaAtual.substring(0, linhaAtual.length()-(comparacao.length()+3)))) {
                    return true;
                    /*
                    this.lacoAberto++;
                    while (!PalavrasReservadas.realizaComparacao(comparacao, this.variaveis))
                        identificaComandosEmEscopo(true, atual, fim);
                } else if (PalavrasReservadas.condicionalSe.equals(linhaAtual.substring(0, linhaAtual.length()-(comparacao.length()+3)))) {
                    return true;
                    /*this.condicionalAberto++;
                    identificaComandosEmEscopo(PalavrasReservadas.realizaComparacao(comparacao, this.variaveis), atual, fim);
                } else if ((PalavrasReservadas.condicionalEntao+"{;").equals(linhaAtual)) {
                    return true;
                    /*TODO
                    
                }
            }
        }*/
        System.out.println(linhaAtual);
        System.out.println((PalavrasReservadas.condicionalEntao+"{;").equals(linhaAtual));
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
