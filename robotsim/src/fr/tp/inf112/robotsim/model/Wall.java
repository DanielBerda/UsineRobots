package fr.tp.inf112.robotsim.model;

import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;

public class Wall extends Component implements Serializable {
	private static final long serialVersionUID = -4365335285260440725L;
	private int length;
	private int height;
	
	public Wall (String name, Position position, int length, int height) {
		super(name,position);
		this.length = length;
		this.height = height;
	}
	@Override
	public Shape getShape() {
		Shape shape = new RectangleShapeImplement(length,height);
		return shape;
	}
	
	@Override
	public Style getStyle() {
		float dash2[] = {1};
		Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,6,dash2));
		return style;
	}

}
