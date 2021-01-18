package agf;
// Java program to play an Audio 
// file using Clip Object 
import java.io.File;
import java.io.IOException;
// C�digo extra�do de: https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * @autor: Ot�vio Pato
 * @email: otaviopato2017@outlook.com
 * @objetivo: Criar uma linguagem para empregar os conceitos de programa��o
 *            Orientada � Objetos aprendidos durante a mat�ria de Programa��o I
 *            do Curso Ci�ncia da Computa��o da Universidade Federal da
 *            Fronteira sul Campus Chapec�.
 */
public class Musica {
	// size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 4096;
	public static String caminho = "audio/a_grande_familia_tema_de_abertura_completo.wav";
	/**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    void play(String audioFilePath) {
        try {
        	File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            audioLine.open(format);
 
            audioLine.start();
             
            System.out.println("\n--------------------------------------\nEssa fam�lia � muito unida!!...");
             
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
 
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
             
            audioLine.drain();
            audioLine.close();
            audioStream.close();
             
            System.out.println("Catuca pai, catuca m�e, catuca filha,.....");
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            //ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            //ex.printStackTrace();
        } catch (java.io.FileNotFoundException ex) {
        	System.out.println("Arquivo n�o encontrado: \"" + caminho + "\".");
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            //ex.printStackTrace();
        }
    }
}