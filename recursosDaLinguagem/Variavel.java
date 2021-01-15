package agf;
import agf.Miscelanea;
import agf.PalavrasReservadas;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

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
            Miscelanea.limpaTela("Já existe uma variável com esse nome.\nNome -> " + nome);
        if (!nome.matches("[a-zA-Z_0-9]+"))
            Miscelanea.limpaTela("Nome inválido para variáveis (deve conter apenas letras, números e _).\nNome -> " + this.nome);
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
                    Miscelanea.limpaTela("Casa decimal deve ser indicada com \",\".\nVariável -> " + this.nome);
            }
        } catch (NumberFormatException e){
            Miscelanea.limpaTela("Valor inválido para o tipo descrito.\nTipo: "
             + this.tipo + "\nVariável: " + this.nome 
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
            ("Tipo de variável não suportada, erro em:\n"
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
            ("Tipo de variável não suportada, erro em:\n"
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