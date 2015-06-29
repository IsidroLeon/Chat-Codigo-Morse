package com.ucab.javachat.Cliente.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * esta clase se encarga de guardar el historial de las conversaciones de el usuario
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 *
 */
public class Historial {
	
	/**
	 * Crea un archivo con el nombre de los participantes de la conversación en el cual almacenara lo escrito
	 * si el archivo o directorio no existe lo crea, si existe le agrega los nuevos datos sin borrar los viejos
	 * @param participantes - los participantes de la conversación
	 * @param texto - todo el texto enviado durante esta sesion
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	
	public static void guardarHistorial(String participantes, String texto) throws URISyntaxException, IOException {
		URI uri ;
		File file;
		PrintWriter pw;
		File miDir = new File(".");
        uri = new URI("file:"+miDir.getCanonicalPath()+"/Documentos/Historial/"+participantes+".txt");
        file = new File(uri);
        if (!file.isDirectory()) { // si no existe el archivo , se crea 
        	file.getParentFile().mkdirs();
        	file.createNewFile();
        } else if (!file.exists()) {
        	file.createNewFile();
        }
        pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		pw.println(texto);
		pw.close();
     }
}