package com.ucab.javachat.Cliente.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**  Clase que se encarga de reproducir mediante sonidos de codigo morse los mensajes enviados
 *  por los usuarios.
 * @author Grupo 3
 */


public class ReproducirSonido extends Thread{
	private String mensaje;
	private File miDir = new File (".");
	
	/** Constructores con y sin parametros
	 */
    public ReproducirSonido() { }
    
    public ReproducirSonido(String mensaje) {
    	this.mensaje = mensaje;
    }
    
    /** Setter para ek mensaje que se reproducira
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
    	this.mensaje = mensaje;
    }
    
    /** Implementacioon del metodo run. en este metodo se debe implementar la funcionalidad de
     * la clase. Se implementa en este metodo porque hereda de la clase Thread.
     */
    public void run() {
    	for (char letra : mensaje.toCharArray()) {
    		if (letra == '-') {
    			try {
					playRaya();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
    		} else if (letra == '.') {
    			try {
					playPunto();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} else if (letra == ' ') {
    			try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} else if (letra == '/') {
    			try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }

    /**
     * Metodo que se encarga de la reproduccion del archivo de sonido Punto
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    private void playPunto() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    	String ruta = miDir.getCanonicalPath() +"/Documentos/Sonidos/punto.wav";
        File soundFile = new File(ruta);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.start();
        clip.drain();
        clip.close();
    }
    
    /**
     *  Metodo que se encarga de la reproduccion del archivo de sonido Raya
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    private void playRaya() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    	String ruta = miDir.getCanonicalPath() +"/Documentos/Sonidos/raya.wav";
        File soundFile = new File(ruta);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.start();
        clip.drain();
        clip.close();
    }
    
    /**Metodo que se encarga del espacio de sonido que hay entre cada caracter del mensaje
     * @return duracion
     */
    public long duracionSonido() {
    	long duracion = 0;
    	for (char letra : mensaje.toCharArray()) {
    		if (letra == '-') {
    			duracion = duracion + 750;
    		} else if (letra == '.') {
    			duracion = duracion + 550;
    		} else if (letra == ' ') {
    			duracion = duracion + 300;
    		} else if (letra == '/') {
    			duracion = duracion + 700;
    		}
    	}
    	return duracion;

    }
}
