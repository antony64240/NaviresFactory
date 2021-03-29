package com.mezza.navire;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

public interface InterfaceEnsembleNavire {
	/**
    * Load all ships in a file in Json format
    * @param   Path of our general stockage + Username + IDGame
    * @return  Return if Success
    */
	public boolean SaveFiles(String url, String Username, int ID);
	/**
    *Loads ships from a file. Rebuilds ships with their states
    * @param   a   Path of our general stockage + Username + IDGame
    * @return  Return true if Success / False if Wrong Path Url
    */
	public boolean FilesToNavires(String url, String Username, int IdPartie);
	/**
	    *Loads ships in a String in Json.
	    *Format of the String : 
	    *exemple for one boat : {"TailleGrille:",10,""Bateau:":"Porte_Avion","NbrBateau:":1,
	    *"X:00":1,"Y:00":1,"Orientation:":true,"VALUE:00":false,"VALUE:01":false} 
	    *"VALUE:01":false ( 0 = number of boat, 1 = number of the box, false = state(
	    *destroy or not) of the box 1 of boat 0.
	    *With Orientation and the first Box, i can rebuid the boat
	    *this will not work if the format is didnt like this
	    * @param   String 
	    */
	public void JsonToNavire(String message) throws ParseException;
	/**
	    *Loads ships in a String in Json.
	    *Format of the Json 
	    * @param   String in Json Format
	    * @return  exemple for one boat : {"TailleGrille:",10,""Bateau:":"Porte_Avion",
	    * "NbrBateau:":1,"X:00":1,"Y:00":1,"Orientation:":true} 
	    */
	public String ToJson() throws IOException;
	/**
     *Add a boat
     * @param   name = Name of Boat choose beetwen 
     * (Porte_Avion) / (Sous_Marin) / (Croiseur) / (Torpilleur) / (Contre_Torpilleur)
     * X Y : Position of the First case
     * Orientation : Horizontal / Vertical
     */
	public boolean addBateau(String name ,int X, int Y, boolean Orientation);
	
	/**
	 * Check if all boat were destroy
     * @return  boolean value, true or false
     */
	public boolean FinPartie();	
	/**
	 * Return destroyed ships
     * @return  Vector ships destroyed
     */
	public Vector<Navire> NavireDetruit();	
	
	/**
     * See if there has been an impact 
     * and change the value boolean estDetruis (IsDestroy )
     * to true.
     * @param   coordinated shot (int,int)
     * @return  String (touché/raté/ ClasssimpleName+ coulé)
     */
	public String toucher(int x, int y);

}









