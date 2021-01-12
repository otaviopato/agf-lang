package agf;
import agf.Miscelanea;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Variavel {
    private static final Set<String> nomes = new HashSet<String>();
    private String tipo;
    private String nome;
    private String valor;

    public Variavel(String tipo, String nome, String valor) {
        atribuiTipo(tipo);
        atribuiNome(nome);
        // "Coletor de lixo"
        if (valor.isEmpty())
            atribuiValor(iniciaVariavel(tipo));
        else
            atribuiValor(valor);
        this.validaValorVariavel();
    }

    private void atribuiNome(String nome) {
        if (nomes.contains(nome))
            Miscelanea.limpaTela("Já existe uma variável com esse nome.\nNome -> " + this.nome);
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
            } else if (this.retornaTipoParaJava() == "boolean") {
                if (!(this.valor == "Falso" || this.valor == "Verdadeiro"))
                    Miscelanea.limpaTela("Valor deve ser \"Falso\" ou \"Verdadeiro\".\nVariável -> " + this.nome);
            } else if (this.retornaTipoParaJava() == "char") {
                if (!(this.valor.length() != 1))
                    Miscelanea.limpaTela("Valor inválido para caracter.\n-> " + this.nome);
            }
        } catch (NumberFormatException e){
            Miscelanea.limpaTela("Valor inválido para o tipo descrito.\nTipo: "
             + this.tipo + "\nVariável: " + this.nome 
             + "\nValor informado: " + this.valor);
        }
    }

    public String iniciaVariavel(String tipo) {
        if (tipo == "bebel") {
            return "0";
        } else if (tipo == "mendonca") {
            return "0,0";
        } else if (tipo == "marilda") {
            return "\0";
        } else if (tipo == "floriano") {
            return "falso";
        } else if (tipo == "paulao") {
            return "\0";
        }

        Miscelanea.limpaTela( 
            ("Tipo de variável não suportada, erro em:\n"
            + this.tipo + " " + this.nome + ";")
        );

        return "Nunca retorna";
    }

    public String retornaTipoParaJava() {
        if (this.tipo == "bebel") {
            return "int";
        } else if (this.tipo == "mendonca") {
            return "float";
        } else if (this.tipo == "marilda") {
            return "char";
        } else if (this.tipo == "floriano") {
            return "boolean";
        } else if (this.tipo == "paulao") {
            return "String";
        }

        Miscelanea.limpaTela( 
            ("Tipo de variável não suportada, erro em:\n"
            + this.tipo + " " + this.nome + "=" 
            + this.valor + ";")
        );

        return "Nunca retorna";
    }

}