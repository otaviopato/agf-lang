package agf;
/**
 * 
 */
import agf.Miscelanea;
//
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @autor: Otávio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programação
 *            Orientada à Objetos aprendidos durante a matéria de Programação I
 *            do Curso Ciência da Computação da Universidade Federal da
 *            Fronteira sul Campus Chapecó.
 */
public class Arquivo {
    private ArrayList<String> linhas = new ArrayList<String>();
    public String nomeDoArquivo;

    public Arquivo(String nomeDoArquivo) {

        try {
            this.nomeDoArquivo = nomeDoArquivo;
            if (!nomeDoArquivo.substring(nomeDoArquivo.length()-4).equals(".agf"))
                Miscelanea.limpaTela("ExtensÃ£o invÃ¡lida.\nA Grande FamÃ­lia possui como extensÃ£o \".agf\"");
            File arquivo = new File(nomeDoArquivo);
            Scanner leitor = new Scanner(arquivo);
            String aux;
            while (leitor.hasNextLine()) {
                aux = leitor.nextLine();
                if (!aux.contains("\"")) {
                    aux = aux.replace(" ", "");
                    aux = aux.replace("\t", "");
                }
                if(!"".equals(aux))
                    adicionaLinha(aux);
            }
            leitor.close();
        } catch (Exception e) {
            Miscelanea.limpaTela("NÃ£o foi possÃ­vel abrir o arquivo: \"" + nomeDoArquivo + "\"\nEle existe mesmo ?\n");
            e.printStackTrace();
        }
    }

    private void adicionaLinha(String linha) {
        this.linhas.add(linha);
    }

    public void exibeLinhas() {
        int n = retornaQuantidadeDeLinhas();
        System.out.println("Arquivo: \"" + this.nomeDoArquivo + "\"\n------------------------------------\n" );
        for (int i=0; i<n; i++) {
            System.out.println("Linha " + i + ": " + retornaLinha(i));
        }
    }

    public ArrayList<String> retornaLinhas(){
        return this.linhas;
    }

    public String retornaLinha(int indice){
        return this.linhas.get(indice);
    }

    public int retornaQuantidadeDeLinhas(){
        return this.linhas.size();
    }

}