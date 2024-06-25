package fr.tp.inf112.robotsim.model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.OvalShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;



public final class Puck extends Component implements Serializable {

	private static final long serialVersionUID = -3461530796769519144L;

	public Puck(String name, Position position) {
		super(name,position);
	}
	
	public String toString() {
		return "Je m'appelle "+name+".";
		
		
	}
	public Shape getShape() {
		Shape shape = new OvalShapeImplement(2,2);
		return shape;
	}
	
	public Style getStyle() {
		Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash));
		return style;
	}

}
