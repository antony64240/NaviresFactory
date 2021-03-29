

import java.io.IOException;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mezza.navire.EnsembleNavire;
import com.mezza.navire.Porte_Avion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

public class TestNavire {
	private static Logger logger = LogManager.getLogger(EnsembleNavire.class);


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
	
	//@Test
	public void CreateNavire() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire(100,25);
		Ensemblenavire.GenererAleatoirement();
		

			try {
				String message = Ensemblenavire.ToJson();
				EnsembleNavire Ensemblenavire1 = new EnsembleNavire();
				Ensemblenavire1.JsonToNavire(message);
				System.out.println(Ensemblenavire1.toString());
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

//	@Test
	public void GenerateJson() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire();
		try {
			Ensemblenavire.GenererAleatoirement();
			System.out.println(Ensemblenavire.ToJson());
		} catch (IOException e) {
			System.out.println("Problem de création du json");
		}
	}
//	@Test
	public void WriteFiles() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire(100,25);
		Ensemblenavire.GenererAleatoirement();
		Ensemblenavire.SaveFiles("C:\\Users\\anton\\Desktop\\gitHub\\Bataille-Navale\\src", "utilisateur1", 2);
		EnsembleNavire Ensemblenavire2 = new EnsembleNavire();
		Ensemblenavire2.FilesToNavires("C:\\Users\\anton\\Desktop\\gitHub\\Bataille-Navale\\src", "utilisateur1", 2);
		System.out.println(Ensemblenavire2.toString());
	}
//	@Test
	public void ReadFiles() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire();
		Ensemblenavire.FilesToNavires("C:\\Users\\anton\\Desktop\\gitHub\\Bataille-Navale\\src", "utilisateur1", 2);
	}
	
	
//	@Test
	public void toucher() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire();
		Ensemblenavire.add(new Porte_Avion(0,0,false));
		System.out.println(Ensemblenavire.toucher(0, 0));
		System.out.println(Ensemblenavire.toucher(1, 0));
		System.out.println(Ensemblenavire.toucher(2, 0));
		System.out.println(Ensemblenavire.toucher(3, 0));
		System.out.println(Ensemblenavire.toucher(4, 0));
	}
	
	@Test
	public void creation() {
		EnsembleNavire Ensemblenavire = new EnsembleNavire(20,false);
		System.out.println(Ensemblenavire.toString());
		
	}
	
	
	
	
//	@Test
//	public void lireJson() {
//		EnsembleNavire Ensemblenavire = new EnsembleNavire(50,31);
//	}
	
	
}
