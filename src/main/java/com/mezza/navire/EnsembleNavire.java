package com.mezza.navire;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.sofianelecubeur.dataserializer.CompilationType;
import fr.sofianelecubeur.dataserializer.FileDeserializerBuilder;
import fr.sofianelecubeur.dataserializer.FileSerializerBuilder;
import fr.sofianelecubeur.dataserializer.JsonFileDeserializer;
import fr.sofianelecubeur.dataserializer.JsonFileSerializer;

@SuppressWarnings("serial")
public class EnsembleNavire extends Vector<Navire> implements InterfaceEnsembleNavire {

	private static List<Integer> TailleNavire;
	private int TailleGrille;

	
	/**
	    * Builds a set of ships to specification of you Game
	    * example : 
	    * EnsembleNavire(10,[1,1,2]) Build an environment of grid of 10.
	    * the constructor was made to automatically generate the boats or have this own boat list.
	    * The list cannot be null.
	    * @param   TailleGrille = your grid size, 
	    * NbrBateau = number of boat
	    * List = Boat List [0 = Porte_Avion (5box),1 = Croisseur(4box),
	    * 2 = Sous_Marin(3box), 3 = Torpilleur(3box),
	    * 4 = Contre_Torpilleur(2box)
	    */
	public EnsembleNavire(int TailleGrille, List<Integer> list) {
		Navire.setEnsembleNavire(this);
		this.TailleGrille = TailleGrille;
		if (list != null) {
			this.TailleNavire = list;
		} else {
			ExceptionNavire e = new ExceptionNavire("List cannot be null");
			e.printStackTrace();
		}
		this.GenererAleatoirement();
	}
	
	/**
	    * Builds a set of ships to specification of you Game
	    * example : 
	    * EnsembleNavire(10,5) Build an environment of 5 ships in grid of 10 
	    * with randomly size of boat. 
	    * @param TailleGrille = Size of grid
	    * NbrBateau = number of ships
	    */
	public EnsembleNavire(int TailleGrille, int NbrBateau) {
		if(NbrBateau<=(TailleGrille*5)/8) {
			Navire.setEnsembleNavire(this);
			this.TailleGrille = TailleGrille;
			TailleNavire = new ArrayList<Integer>();
			int taillebateau;
			for (int i = 0; i < NbrBateau; i++) {
				taillebateau = (int) (Math.random() * (5));
				TailleNavire.add(taillebateau);
			}
			this.GenererAleatoirement();
		}else {
			ExceptionNavire e = new ExceptionNavire("Trop de bateau par apport à la taille de la grille");
			e.printStackTrace();
		}
	}

	/**
	    * Builds a set of ships to specification of you Game
	    * build 5 ships, one of each
	    *@param TailleGrille = Size of grid
	    */
	public EnsembleNavire(int TailleGrille) {
		Navire.setEnsembleNavire(this);
		this.TailleGrille = TailleGrille;
		TailleNavire = new ArrayList<Integer>();
		TailleNavire.add(1);
		TailleNavire.add(2);
		TailleNavire.add(3);
		TailleNavire.add(4);
		TailleNavire.add(5);
		this.GenererAleatoirement();
	}

	/**
	    * Builds a null environment
	    * You can use this constructor for import 
	    * boats from String Json or Files
	    */
	public EnsembleNavire() {

	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < this.size(); i++) {
			result += this.get(i).toString() + "\n";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String ToJson() throws IOException {
		JSONObject json = new JSONObject();
		int i = 0;
		json.put("TailleGrille:", this.TailleGrille);
		json.put("NbrBateau:", this.size());
		for (Navire e : this) {
			json.put("Bateau:" + String.valueOf(i), e.getClass().getSimpleName());
			json.put("X:" + String.valueOf(i), e.getParts().get(0).getX());
			json.put("Y:" + String.valueOf(i), e.getParts().get(0).getY());
			json.put("Orientation:" + String.valueOf(i), e.getOrientation());
			i += 1;
		}
		i = 0;
		for (Navire e : this) {
			int j = 0;
			for (CaseNavire c : e.getParts()) {
				json.put("VALUE:" + String.valueOf(i) + String.valueOf(j), c.estDetruit());
				j++;
			}
		}
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public boolean SaveFiles(String url, String Username, int ID) {
		String path = url + "/" + Username + "/" + String.valueOf(ID) + ".json";
		try {
			JsonFileSerializer serializer = (JsonFileSerializer) new FileSerializerBuilder().type(CompilationType.JSON)
					.get();
			int i = 0;
			serializer.writeObject("NbrBateau:", this.size());
			serializer.writeObject("TailleGrille:", this.TailleGrille);
			for (Navire e : this) {
				serializer.writeObject("Bateau:" + String.valueOf(i), e.getClass().getSimpleName());
				serializer.writeObject("X:" + String.valueOf(i), e.getParts().get(0).getX());
				serializer.writeObject("Y:" + String.valueOf(i), e.getParts().get(0).getY());
				serializer.writeObject("Orientation:" + String.valueOf(i), e.getOrientation());
				i += 1;
			}
			i = 0;
			for (Navire e : this) {
				int j = 0;
				for (CaseNavire c : e.getParts()) {
					serializer.writeObject("VALUE:" + String.valueOf(i) + String.valueOf(j), c.getValue());
					j++;
				}
			}
			serializer.writeObject("NbrBateau:", this.size());
			long execTime = serializer.compile(new File(path));
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean FilesToNavires(String url, String Username, int IdPartie) {
		Navire navire = null;
		String path = url + "/" + Username + "/" + String.valueOf(IdPartie) + ".json";
		try {
			JsonFileDeserializer deserializer = (JsonFileDeserializer) new FileDeserializerBuilder()
					.type(CompilationType.JSON).file(new File(path)).get();
			int NbrI = deserializer.readInt("NbrBateau:");
			this.TailleGrille = deserializer.readInt("TailleGrille:");
			for (int i = 0; i < NbrI; i++) {
				boolean Orientation = deserializer.readBoolean("X:" + String.valueOf(i));
				int X = deserializer.readInt("X:" + String.valueOf(i));
				int Y = deserializer.readInt("Y:" + String.valueOf(i));
				String Bateau = deserializer.readUTF("Bateau:" + i);
				switch (Bateau) {
				case "Porte_Avion":
					navire = new Porte_Avion(X, Y, Orientation, TailleGrille);
					break;
				case "Croiseur":
					navire = new Croiseur(X, Y, Orientation, TailleGrille);
					break;
				case "Torpilleur":
					navire = new Torpilleur(X, Y, Orientation, TailleGrille);
					break;
				case "Sous_Marin":
					navire = new Sous_Marin(X, Y, Orientation, TailleGrille);
					break;
				case "Contre_Torpilleur":
					navire = new Contre_Torpilleur(X, Y, Orientation, TailleGrille);
					break;
				default:
					System.out.println("Erreur de chargement des bateaux");
				}
				int j = 0;
				for (CaseNavire Cn : navire.getParts()) {
					Cn.estDetruit(deserializer.readBoolean("VALUE:" + String.valueOf(i) + String.valueOf(j)));
					j++;
				}
				this.add(navire);
			}
			deserializer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String toucher(int x, int y) {
		String message = "Raté";
		CaseNavire cases = new CaseNavire(x, y, 0);
		for (Navire D : this) {
			if (D.navireTouche(cases)) {
				message = "Case Touché";
			}
		}
		for (Navire D : this) {
			if (D.estCoulé()) {
				message = D.getClass().getSimpleName() + " coulé !";
			}
		}
		return message;
	}

	public void GenererAleatoirement() {
		Navire navire = null;
		for (int i = 0; i < this.TailleNavire.size(); i++) {
			int taille = TailleNavire.get(i);
			int index = 0;
			if (taille == 1) {
				index = 1;
				taille = 4;
			}
			if (taille == 2) {
				index = 0;
				taille = 5;
			}
			if (taille == 3) {
				index = 2;
				taille = 3;
			}
			if (taille == 4) {
				index = 3;
				taille = 3;
			}
			if (taille == 5) {
				index = 4;
				taille = 2;
			}

			boolean direction = false;
			if (Math.random() > 0.5) {
				direction = true;
			} else {
				direction = false;
			}

			int x = (int) (Math.random() * ((TailleGrille + 1) - taille));
			int y = (int) (Math.random() * ((TailleGrille + 1) - taille));

			switch (index) {
			case 0:
				navire = (new Porte_Avion(x, y, direction, TailleGrille));
				break;
			case 1:
				navire = (new Croiseur(x, y, direction, TailleGrille));
				break;
			case 2:
				navire = (new Sous_Marin(x, y, direction, TailleGrille));
				break;
			case 3:
				navire = (new Torpilleur(x, y, direction, TailleGrille));
				break;
			case 4:
				navire = (new Contre_Torpilleur(x, y, direction, TailleGrille));
				break;
			default:
				System.out.println("Erreur dans le chargement des navires");
			}

			if (!checkNavire(navire)) {
				this.add(navire);
			} else {
				i--;
			}

		}
	}

	boolean checkNavire(Navire e) {

		for (CaseNavire Cs : e.getParts()) {
			if (this.contains(Cs)) {
				return true;
			}
		}
		for (CaseNavire C : e.getAroundParts()) {
			if (this.contains(C)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(CaseNavire c) {
		for (Navire D : this) {
			if (D.contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	
	public Vector<Navire> NavireDetruit() {
		Vector<Navire> navireDetruis = new Vector<Navire>();
		for(Navire navire : this) {
			if(navire.estCoulé()) {
				navireDetruis.add(navire);
			}
		}
		return navireDetruis;
	}


	@Override
	public void JsonToNavire(String message) throws ParseException {
		JSONParser parser = new JSONParser();
		Navire navire = null;
		Object obj;

		obj = parser.parse(message);
		JSONObject array = (JSONObject) obj;
		this.TailleGrille = Integer.parseInt(array.get("TailleGrille:").toString());
		int NbrI = Integer.parseInt(array.get("NbrBateau:").toString());
		for (int i = 0; i < NbrI; i++) {
			boolean Orientation = Boolean.valueOf(String.valueOf(array.get("X:" + String.valueOf(i))));
			int X = Integer.parseInt(array.get("X:" + String.valueOf(i)).toString());
			int Y = Integer.parseInt(array.get("Y:" + String.valueOf(i)).toString());
			String Bateau = String.valueOf(array.get("Bateau:" + i).toString());
			switch (Bateau) {
			case "Porte_Avion":
				navire = new Porte_Avion(X, Y, Orientation, TailleGrille);
				break;
			case "Croiseur":
				navire = new Croiseur(X, Y, Orientation, TailleGrille);
				break;
			case "Torpilleur":
				navire = new Torpilleur(X, Y, Orientation, TailleGrille);
				break;
			case "Sous_Marin":
				navire = new Sous_Marin(X, Y, Orientation, TailleGrille);
				break;
			case "Contre_Torpilleur":
				navire = new Contre_Torpilleur(X, Y, Orientation, TailleGrille);
				break;
			default:
				System.out.println("Erreur de chargement des bateaux");
			}
			int j = 0;
			for (CaseNavire Cn : navire.getParts()) {
				Cn.estDetruit(Boolean.valueOf(
						String.valueOf(array.get("VALUE:" + String.valueOf(i) + String.valueOf(j).toString()))));
				j++;
			}
			this.add(navire);
		}
	}

	@Override
	public boolean addBateau(String name, int X, int Y, boolean Orientation) {
		Navire navire = null;
		switch (name) {
		case "Porte_Avion":
			navire = new Porte_Avion(X, Y, Orientation, TailleGrille);
			if (this.checkNavire(navire)) {
				this.add(navire);
			} else {
				return false;
			}
			break;
		case "Croiseur":
			navire = new Croiseur(X, Y, Orientation, TailleGrille);
			if (this.checkNavire(navire)) {
				this.add(navire);
			} else {
				return false;
			}
			break;
		case "Torpilleur":
			navire = new Torpilleur(X, Y, Orientation, TailleGrille);
			if (this.checkNavire(navire)) {
				this.add(navire);
			} else {
				return false;
			}
			break;
		case "Sous_Marin":
			navire = new Sous_Marin(X, Y, Orientation, TailleGrille);
			if (this.checkNavire(navire)) {
				this.add(navire);
			} else {
				return false;
			}
			break;
		case "Contre_Torpilleur":
			navire = new Contre_Torpilleur(X, Y, Orientation, TailleGrille);
			if (this.checkNavire(navire)) {
				this.add(navire);
			} else {
				return false;
			}
			break;
		default:
			Exception e = new ExceptionNavire("Nom de navire incorrect");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean FinPartie() {
		for (Navire navire : this) {
			for (CaseNavire Case : navire.getParts()) {
				if (!Case.estDetruit()) {
					return false;
				}
			}
		}
		return true;
	}

}
