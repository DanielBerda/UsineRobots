package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.*;




public abstract class Component implements Figure  {
	protected String name;
	protected Position position;

	protected float dash[] = { 4};
	protected Style style;
	protected Shape shape;
	

	public Component(String name, Position position) {
		this.name = name;
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	public int getxCoordinate() {
		return position.getxCoord();
	}
	public int getyCoordinate() {
		return position.getyCoord();
	}
	
	
	public Style getStyle() {
		return style;
	}
	public Shape getShape() {
		return shape;
	}

	
	public void behave() {
		
	}

}
