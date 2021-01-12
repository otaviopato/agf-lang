package agf;

import agf.Miscelanea;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Arquivo {
    private ArrayList<String> linhas = new ArrayList<String>();
    public String nomeDoArquivo;

    public Arquivo(String nomeDoArquivo) {

        try {
            this.nomeDoArquivo = nomeDoArquivo;
            if (!nomeDoArquivo.substring(nomeDoArquivo.length()-4).equals(".agf"))
                Miscelanea.limpaTela("Extensão inválida.\nA Grande Família possui como extensão \".agf\"\n");
            File arquivo = new File(nomeDoArquivo);
            Scanner leitor = new Scanner(arquivo);
            String aux;
            while (leitor.hasNextLine()) {
                aux = leitor.nextLine();
                if (!aux.contains("\""))
                    aux = aux.replace(' ', '\0');
                adicionaLinha(aux);
            }
            leitor.close();
            exibeLinhas();
        } catch (Exception e) {
            Miscelanea.limpaTela("Não foi possível abrir o arquivo: \"" + nomeDoArquivo + "\"\nEle existe mesmo ?\n");
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

    private ArrayList<String> retornaLinhas(){
        return this.linhas;
    }

    public String retornaLinha(int indice){
        return this.linhas.get(indice);
    }

    public int retornaQuantidadeDeLinhas(){
        return this.linhas.size();
    }

}