package fr.tp.inf112.robotsim.model;

import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;

public final class Door extends Component implements Serializable {
	

	private static final long serialVersionUID = -1211788126415759837L;
	private boolean open;

	public Door(String name, Position position) {
		super(name,position);
	}
	
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getOpen() {
		return open;
	}
	
	@Override
	public Style getStyle() {
		float dash2[] = {1};
		Style style;
		if (open)
			style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.WHITE,4,dash2));
		else
			style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash2));
		return style;
	}
	
	@Override
	public Shape getShape() {
		Shape shape = new RectangleShapeImplement(0,4);
		return shape;
	}

	@Override
	public String toString() {
		return "Je m'appelle "+name+". Je suis située aux coordonnées (" +position.getxCoord()+ ";" +position.getyCoord() +").";
	
	}
}


