package com.mezza.navire;

import java.util.Vector;

public abstract class Navire {

	private static EnsembleNavire ensembleNavire;

	
	
	public Navire() {
		super();
		
	}

	public static EnsembleNavire getEnsembleNavire() {
		return ensembleNavire;
	}

	public static void setEnsembleNavire(EnsembleNavire ensembleNavire) {
		Navire.ensembleNavire = ensembleNavire;
	}
	
	
	public abstract Vector<CaseNavire> getParts();
	public abstract int getTaille();
	public Vector<Navire> getNavire() {return Navire.ensembleNavire;		}
	public abstract Vector<CaseNavire> getAroundParts();
	public abstract void addPart(int x,int y);
	public abstract boolean contains(CaseNavire a);
	public abstract boolean getOrientation();
	public boolean estCoulé;
	public abstract boolean navireTouche(CaseNavire a);



	public abstract boolean estCoulé();


}
