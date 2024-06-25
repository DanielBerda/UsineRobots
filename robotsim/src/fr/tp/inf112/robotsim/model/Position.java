package fr.tp.inf112.robotsim.model;

import java.io.Serializable;

public final class Position implements Serializable{
	private static final long serialVersionUID = -8917701403651829768L;
	private int xCoord;
	private int yCoord;
	public Position(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public int getxCoord() {
		return xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setxCoord(int x) {
		xCoord = x;
	}
	public void setyCoord(int y) {
		yCoord = y;
	}
}
