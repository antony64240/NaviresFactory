package com.mezza.navire;



public class CaseNavire{
	private Boolean estDetruit;
	private int x;
	private int y;
	private int value;
	
	public CaseNavire(int x, int y, int i){
		estDetruit(false);
		this.x=x;
		this.y=y;
		this.value= i;
	}
	
	public Boolean estDetruit()         	{return estDetruit;			}
	public void estDetruit(Boolean b)   	{estDetruit = b;   			}
	public int getX()                   	{return x;         			}
	public void setX(int x)             	{this.x = x;       			}
	public int getY()                 		{return y;         			}
	public void setY(int y)           		{this.y = y;       			}
	public int getValue(int x, int y) 		{return this.getValue(x, y);}
	public void setValue(int value)      	{this.value = value;       	}
	public int getValue()                  	{return this.value;         }

	
	@Override
	public String toString() {
		return "CaseNavire [estDetruit=" + estDetruit + ", x=" + x + ", y=" + y + ", value=" + value + "]";
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		CaseNavire x = (CaseNavire) obj;
		
		if((this.x==x.getX()) && (this.y == x.getY())) {
			return true;
		}
		return false;
	}
	
	
	
}