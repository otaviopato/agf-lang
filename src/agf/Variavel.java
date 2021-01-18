package agf;
/**
 * 
 */
import agf.Miscelanea;
import agf.PalavrasReservadas;
// 
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * @autor: Otávio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programação
 *            Orientada à Objetos aprendidos durante a matéria de Programação I
 *            do Curso Ciência da Computação da Universidade Federal da
 *            Fronteira sul Campus Chapecó.
 */
public class Variavel {
    private static final Set<String> nomes = new HashSet<String>();
    private String tipo;
    private String nome;
    public String valor;

    public Variavel(String tipo, String nome) {
        atribuiTipo(tipo);
        atribuiNome(nome);
        // "Coletor de lixo"
        atribuiValor(iniciaVariavel());
        this.validaValorVariavel();
    }

    public void exibeVariavel() {
        System.out.println("-> Tipo: " + this.tipo + "\n-> Valor: " + this.valor + "\n-> Nome: " + this.nome);
    }

    private void atribuiNome(String nome) {
        if (nomes.contains(nome))
            Miscelanea.limpaTela("JÃ¡ existe uma variÃ¡vel com esse nome.\nNome -> " + nome);
        if (!nome.matches("[\\w]+"))
            Miscelanea.limpaTela("Nome invÃ¡lido para variÃ¡veis (deve conter apenas letras, nÃºmeros e _).\nNome -> " + nome);
        if (nome.matches("[0-9]+"))
            Miscelanea.limpaTela("Nome invÃ¡lido para variÃ¡veis (deve conter apenas letras, nÃºmeros _).\nNome -> " + nome);
        nomes.add(nome);
        this.nome = nome;
    }

    private void atribuiTipo(String tipo) {
        this.tipo = tipo;
    }

    public void atribuiValor(String valor) {
        this.valor = valor;
        validaValorVariavel();
    }

    public String retornaNome() {
        return this.nome;
    }

    public String retornaTipo() {
        return this.tipo;
    }

    public String retornaValor() {
        return this.valor;
    }

    public void validaValorVariavel() {
        int validaInteiro, formatoInvalidoDecimal=0;
        float validaDecimal;
        char validaCaracter;
        try {
            if (this.retornaTipoParaJava() == "int") {
                validaInteiro = Integer.parseInt(this.valor);
            } else if (this.retornaTipoParaJava() == "float") {
                if (this.valor.contains("."))
                    formatoInvalidoDecimal++;
                validaDecimal = Float.parseFloat(this.valor.replace(',', '.'));
                if (formatoInvalidoDecimal != 0)
                    Miscelanea.limpaTela("Casa decimal deve ser indicada com \",\".\nVariÃ¡vel -> " + this.nome);
            }
        } catch (NumberFormatException e){
            Miscelanea.limpaTela("Valor invÃ¡lido para o tipo descrito.\nTipo: "
             + this.tipo + "\nVariÃ¡vel: " + this.nome 
             + "\nValor informado: " + this.valor);
        }
    }

    public String iniciaVariavel() {
        String aux = PalavrasReservadas.inteiro;
        if (this.tipo.equals(PalavrasReservadas.inteiro)) {
            return "0";
        } else if (this.tipo.equals(PalavrasReservadas.pontoFlutuante)) {
            return "0,0";
        }
        Miscelanea.limpaTela( 
            ("Tipo de variÃ¡vel nÃ£o suportada, erro em:\n"
            + this.tipo + " " + this.nome + ";")
        );

        return "Nunca retorna";
    }

    public String retornaTipoParaJava() {
        if (this.tipo.equals(PalavrasReservadas.inteiro)) {
            return "int";
        } else if (this.tipo.equals(PalavrasReservadas.pontoFlutuante)) {
            return "float";
        }/* else if (this.tipo == "marilda") {
            return "char";
        } else if (this.tipo == "floriano") {
            return "boolean";
        } else if (this.tipo == "paulao") {
            return "String";
        }*/

        Miscelanea.limpaTela( 
            ("Tipo de variÃ¡vel nÃ£o suportada, erro em:\n"
            + this.tipo + " " + this.nome + "=" 
            + this.valor + ";")
        );

        return "Nunca retorna";
    }

    @Override
    public String toString() {
        return this.valor;
    }

}