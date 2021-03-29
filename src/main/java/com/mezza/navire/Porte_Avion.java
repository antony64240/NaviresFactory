package com.mezza.navire;

import java.util.Vector;

public class Porte_Avion extends Navire {

private Vector<CaseNavire> parts;
private Vector<CaseNavire> AroundNavire;
private int taille;
private boolean Orientation;
private int tailleGrille;
	
	public Porte_Avion(int x, int y, boolean Vertical, int tailleGrille) {
		super();
		parts = new Vector<CaseNavire>();
		AroundNavire = new Vector<CaseNavire>();
		this.Orientation=Vertical;
		if(Vertical==true) {
			this.parts.add(new CaseNavire(x,y,5));
			this.parts.add(new CaseNavire(x,y+1,5));
			this.parts.add(new CaseNavire(x,y+2,5));
			this.parts.add(new CaseNavire(x,y+3,5));
			this.parts.add(new CaseNavire(x,y+4,5));			
		}else {
			this.parts.add(new CaseNavire(x,y,5));
			this.parts.add(new CaseNavire(x+1,y,5));
			this.parts.add(new CaseNavire(x+2,y,5));
			this.parts.add(new CaseNavire(x+3,y,5));
			this.parts.add(new CaseNavire(x+4,y,5));
		}
		this.taille = 5;
		this.tailleGrille=(tailleGrille-1);
		this.pushAroundCase();
	}
	public Porte_Avion(int x, int y, boolean orientation2) {
		super();
		parts = new Vector<CaseNavire>();
		this.taille = 5;
	}
	
	public Vector<CaseNavire> getAroundParts()  {return this.AroundNavire;   			}
	public Vector<CaseNavire> getParts()   		{return this.parts;                		}
	public int getTaille()  			   		{return this.taille;          			}
	public void addPart(int x, int y)      		{this.parts.add(new CaseNavire(x,y,5)); }
	public boolean getOrientation() { return this.Orientation;}

	public boolean estCoulé(){
		for(CaseNavire e : this.getParts()) {
			if(!e.estDetruit()) {
				return false;
			}
		}
		 return true;
	}
	
	@Override
	public String toString() {
		return "Porte_Avion [parts=" + this.parts.toString() + this.AroundNavire.toString()+"]";
	}
	
	
	
	public boolean contains(CaseNavire a) {
		if(this.parts.contains(a))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean navireTouche(CaseNavire a) {
		for(CaseNavire Case : this.getParts()) {
			if(Case.equals(a)) {
				Case.estDetruit(true);
				return true;
			}
		}
		return false;
	}
	
	
	public void pushAroundCase() {
		if(this.Orientation) {
			for(CaseNavire e :this.parts) {
				if(e.getX()>0) {this.AroundNavire.add(new CaseNavire(e.getX()-1,e.getY(),8));}	
				if(e.getX()<tailleGrille) {this.AroundNavire.add(new CaseNavire(e.getX()+1,e.getY(),8));}
			}
			if(this.getParts().get(0).getY()>0)this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX(),this.getParts().get(0).getY()-1,8));
			if(this.getParts().get(this.getTaille()-1).getY()<tailleGrille)this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX(),this.getParts().get(this.getTaille()-1).getY()+1,8));
			if((this.getParts().get(0).getY()>0)&&(this.getParts().get(0).getX()>0)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX()-1,this.getParts().get(0).getY()-1,8));}
			if((this.getParts().get(0).getY()>0)&&(this.getParts().get(0).getX()<tailleGrille)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX()+1,this.getParts().get(0).getY()-1,8));}
			if((this.getParts().get(this.getTaille()-1).getY()<tailleGrille)&&(this.getParts().get(0).getX()<tailleGrille)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(this.getTaille()-1).getX()+1,this.getParts().get(this.getTaille()-1).getY()+1,8));}
			if((this.getParts().get(this.getTaille()-1).getY()<tailleGrille)&&(this.getParts().get(0).getX()>0)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(this.getTaille()-1).getX()-1,this.getParts().get(this.getTaille()-1).getY()+1,8));}
		}
		if(!this.Orientation) {
			for(CaseNavire e :this.parts) {
				if(e.getY()>0) {this.AroundNavire.add(new CaseNavire(e.getX(),e.getY()-1,8));}
				if(e.getY()<tailleGrille) {this.AroundNavire.add(new CaseNavire(e.getX(),e.getY()+1,8));}
			}
			if(this.getParts().get(0).getX()>0)this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX()-1,this.getParts().get(0).getY(),8));
			if(this.getParts().get(this.getTaille()-1).getX()<tailleGrille)this.AroundNavire.add(new CaseNavire(this.getParts().get(this.getTaille()-1).getX()+1,this.getParts().get(0).getY(),8));
			if((this.getParts().get(0).getY()>0)&&(this.getParts().get(0).getX()>0)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX()-1,this.getParts().get(0).getY()-1,8));}
			if((this.getParts().get(0).getY()<tailleGrille)&&(this.getParts().get(0).getX()>0)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(0).getX()-1,this.getParts().get(0).getY()+1,8));}
			if((this.getParts().get(0).getY()>0)&&(this.getParts().get(this.getTaille()-1).getX()<tailleGrille)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(this.getTaille()-1).getX()+1,this.getParts().get(this.getTaille()-1).getY()-1,8));}
			if((this.getParts().get(0).getY()<tailleGrille)&&(this.getParts().get(this.getTaille()-1).getX()<tailleGrille)) {this.AroundNavire.add(new CaseNavire(this.getParts().get(this.getTaille()-1).getX()+1,this.getParts().get(this.getTaille()-1).getY()+1,8));}
		}
	
	}
}
