package fr.tp.inf112.robotsim.model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;

public final class ProductionUnit extends Component implements Serializable {
	
	private static final long serialVersionUID = 7970016883584307253L;

	private float productionRate;
	
	public ProductionUnit(String name, Position position,float productionRate){
		super(name,position);
		this.productionRate = productionRate;
	}
		
	
	public Style getStyle() {
		float dash2[] = { 1};
		Style style = new StyleComponent(RGBColor.YELLOW, new StrokeComponent(RGBColor.BLACK,4,dash2));
		return style;
	}
	
	
	public Shape getShape() {
		
		Shape shape = new RectangleShapeImplement(2,2);
		return shape;
	}

	
	public String toString() {
		return "Je m'appelle "+name+". Ma capacité de production est "+productionRate;
	}
}
