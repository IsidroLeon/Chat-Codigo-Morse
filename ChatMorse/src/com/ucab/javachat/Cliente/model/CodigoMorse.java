package com.ucab.javachat.Cliente.model;

 /**
  * Esta clase se encarga de traducir un texto a codigo morse y viceversa 
  * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
  *
  */
    public class CodigoMorse {
		
    	/**
		 * Este metodo se encarga de transformar un texto a codigo morse
		 * @param mensaje Mensaje a traducir
		 * @return morse 
		 */
		public static String traducirAlfabeto(String mensaje){
			
			String letras = "abcdefghijklmnopqrstuvwxyz0123456789áéíóú.,?!()[]&:;=+-_$@";
			mensaje = mensaje.toLowerCase();
			
			String codigo[] = new String[60];
			String slash = "/";
			String morse = " ";
	
			codigo[0] = ".-"; 		//a
			codigo[1] = "-...";		//b
			codigo[2] = "-.-.";		//c
			codigo[3] = "-..";		//d
			codigo[4] = ".";		//e
			codigo[5] = "..-.";		//f
			codigo[6] = "--.";		//g
			codigo[7] = "....";		//h
			codigo[8] = "..";		//i
			codigo[9] = ".---";		//j
			codigo[10] = "-.-";		//k
			codigo[11] = ".-..";	//l
			codigo[12] = "--";		//m
			codigo[13] = "-.";		//n
			codigo[14] = "---";		//o
			codigo[15] = ".--.";	//p
			codigo[16] = "--.-";	//q
			codigo[17] = ".-.";		//r
			codigo[18] = "...";		//s
			codigo[19] = "-";		//t
			codigo[20] = "..-";		//u
			codigo[21] = "...-";	//v
			codigo[22] = ".--";		//w
			codigo[23] = "-..-";	//x
			codigo[24] = "-.--";	//y
			codigo[25] = "--..";	//z
			codigo[26] = "-----";	//0
			codigo[27] = ".----";	//1
			codigo[28] = "..---";	//2
			codigo[29] = "...--";	//3
			codigo[30] = "....-";	//4
			codigo[31] = ".....";	//5
			codigo[32] = "-....";	//6
			codigo[33] = "--...";	//7
			codigo[34] = "---..";	//8
			codigo[35] = "----.";	//9
			codigo[36] = ".-";		//a
			codigo[37] = ".";		//e
			codigo[38] = "..";		//i
			codigo[39] = "---";		//o
			codigo[40] = "..-";		//u
			codigo[41] = ".-.-.-";	//.
			codigo[42] = "--..--";	//,
			codigo[43] = "..--..";	//?
			codigo[44] = ".-.-..";	//!
			codigo[45] = "-.--.";	//(
			codigo[46] = "-.--.-";	//)
			codigo[47] = "-.--.";	//[
			codigo[48] = "-.--.-";	//]
			codigo[49] = ".-...";	//&
			codigo[50] = "---...";	//:
			codigo[51] = "-.-.-.";	//;
			codigo[52] = "-...-";	//=
			codigo[53] = ".-.-.";	//+
			codigo[54] = "-....-";	//-
			codigo[55] = "..--.-";	//_
			codigo[56] = "...-..-";	//$
			codigo[57] = ".--.-.";	//@
			
			for  (int i = 0; i < mensaje.length() ; i++){ 
				if (mensaje.charAt(i)==' ') morse = morse+slash+' ';
				else {
					for  (int j = 0; j < letras.length() ; j++) {
						if (mensaje.charAt(i) == letras.charAt(j))
							morse = morse + codigo[j]+' ';	// Poner el if si no hay letras
						}
					}
				}
			return morse = morse.substring(1,morse.length());//quita el espacio que se genera de mas
			}

			
		/**
		 * Este metodo traduce el codigo morse a alfabeto 
		 * @param mensaje
		 * @return morse
		 */
		public static String traducirMorse(String mensaje){
		
				String letras = "abcdefghijklmnopqrstuvwxyz 0123456789.,?!()[]&:;=+-_$@";
				String codigo[] = new String[60];
				String morse = " ";
		
				codigo[0] = ".-"; //a
				codigo[1] = "-...";//b
				codigo[2] = "-.-.";//c
				codigo[3] = "-..";//d
				codigo[4] = ".";//e
				codigo[5] = "..-.";//f
				codigo[6] = "--.";//g
				codigo[7] = "....";//h
				codigo[8] = "..";//i
				codigo[9] = ".---";//j
				codigo[10] = "-.-";//k
				codigo[11] = ".-..";//l
				codigo[12] = "--";//m
				codigo[13] = "-.";//n
				codigo[14] = "---";//o
				codigo[15] = ".--.";//p
				codigo[16] = "--.-";//q
				codigo[17] = ".-.";//r
				codigo[18] = "...";//s
				codigo[19] = "-";//t
				codigo[20] = "..-";//u
				codigo[21] = "...-";//v
				codigo[22] = ".--";//w
				codigo[23] = "-..-";//x
				codigo[24] = "-.--";//y
				codigo[25] = "--..";//z
				codigo[26] = "/";//espacio
				codigo[27] = "-----";//0
				codigo[28] = ".----";//1
				codigo[29] = "..---";//2
				codigo[30] = "...--";//3
				codigo[31] = "....-";//4
				codigo[32] = ".....";//5
				codigo[33] = "-....";//6
				codigo[34] = "--...";//7
				codigo[35] = "---..";//8
				codigo[36] = "----.";//9
				codigo[37] = ".-.-.-";//.
				codigo[38] = "--..--";//,
				codigo[39] = "..--..";//?
				codigo[40] = ".-.-..";//!
				codigo[41] = "-.--.";//(
				codigo[42] = "-.--.-";//)
				codigo[43] = "-.--.";//[
				codigo[44] = "-.--.-";//]
				codigo[45] = ".-...";//&
				codigo[46] = "---...";//:
				codigo[47] = "-.-.-.";//;
				codigo[48] = "-...-";//=
				codigo[49] = ".-.-.";//+
				codigo[50] = "-....-";//-
				codigo[51] = "..--.-";//_
				codigo[52] = "...-..-";//$
				codigo[53] = ".--.-.";//@
				
				String oracion[] = mensaje.split(" ");
				int max = oracion.length;
				
		        for  (int i = 0; i < max; i++){
		        	for (int j = 0; j < 60; j++)
		        		if (oracion[i].equals(codigo[j])) {
		        			morse = morse+letras.charAt(j);
		        			break;
		        		}
		        }
				return morse = morse.substring(1,morse.length()); //quita el espacio que se genera de mas
	}
}
