package com.ucab.servidorchat.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucab.servidorchat.model.Usuario;

/** Clase que manipula los archivos Json, el guardado y lectura de archivo y cualquier proceso relacionado
*
* @author Luis Valladares
* 
*/

public final class ManejoArchivos {

	private Gson gson = new Gson();
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private final String ruta = "../Documentos/Java.json";
	
	
	public ManejoArchivos() {
		this.listaUsuarios = LeerArchivo();
	}
	
	/**
	 * Lee los datos de un archivo json en una ruta previamente establecida
	 * luego de que lo extrae, lo almacena en un ArrayList de tipo usuario
	 * que tendra a todos los usuarios del sistemas
	 *
	 * @return      La lista de usuarios
	 * @author		Luis Valladares
	 */
	public ArrayList<Usuario> LeerArchivo() {
		Logger log = Logger.getLogger(ManejoArchivos.class);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(ruta)); // Variable para el lector del archivo
		} catch (FileNotFoundException ex) {
			log.error("Archivo no encontrado: " + ex.getMessage());
		}
		// Aca paso el archivo JSON a la lista
		listaUsuarios = gson.fromJson(br, new TypeToken<ArrayList<Usuario>>() {}.getType()); 
		return listaUsuarios;
	}
	
	/**
	 * Almacena los datos que se encuentran en la lista pasada
	 * a un archivo Json
	 * @author		Luis Valladares
	 * @param 		Lista de usuarios a guardar
	 */
	
	public void EscribirArchivo(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
		String json = gson.toJson(this.listaUsuarios); //Convierto la lista a json
		try {  
			FileWriter writer = new FileWriter(ruta); //Guardo la lista en el archivo
			writer.write(json);  
			writer.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  	
	}
}
