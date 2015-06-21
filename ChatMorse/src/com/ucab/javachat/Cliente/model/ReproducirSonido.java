package com.ucab.javachat.Cliente.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ReproducirSonido extends Thread{
	private String mensaje;
	private File miDir = new File (".");

    public ReproducirSonido() { }
    
    public ReproducirSonido(String mensaje) {
    	this.mensaje = mensaje;
    }
    
    public void setMensaje(String mensaje) {
    	this.mensaje = mensaje;
    }
    
    public void run() {
    	for (char letra : mensaje.toCharArray()) {
    		System.out.println("entre");
    		if (letra == '-') {
    			try {
					playRaya();
					System.out.println("RAYA");
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
					System.out.println("PUNTO");
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
    
    private void playRaya() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    	String ruta = miDir.getCanonicalPath() +"/Documentos/Sonidos/ralla.wav";
        File soundFile = new File(ruta);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.start();
        clip.drain();
        clip.close();
    }
}  