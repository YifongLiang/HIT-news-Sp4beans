package com.Sp4beans.Flag;

public class Flag {
	private boolean FLAG;
	public Flag() {
		FLAG = true;
	}
	public boolean isFLAG() {
		return FLAG;
	}
	public void setFLAG(boolean fLAG) {
		FLAG = fLAG;
	}
	public void changeFLAG() {
		FLAG = !FLAG;
	}
}
