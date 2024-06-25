package fr.tp.inf112.robotsim.model;

import java.io.Serializable;


import fr.tp.inf112.projects.canvas.model.Shape;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;

public final class ChargingStation extends Component implements Serializable{
	private static final long serialVersionUID = -3539483679943471508L;
	float chargingSpeed;
	public ChargingStation(String name, Position position,float chargingSpeed) {
		super(name,position);
		this.chargingSpeed = chargingSpeed;
	}
	

	public Style getStyle() {
		Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash));
		return style;
	}
	
	
	public Shape getShape() {
		Shape shape = new RectangleShapeImplement(2,2);
		return shape;
	}
	
}
