package fr.tp.inf112.robotsim.model;
import java.io.Serializable;

import java.util.ArrayList;


import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;



public final class Area extends Component implements Serializable {
	private static final long serialVersionUID = 6573713249249750440L;
	private ArrayList<ProductionUnit> listProductionUnit = new ArrayList<ProductionUnit>();
	private Factory factory;
	
	public Area(String name, Position position, Factory factory) {
		super(name,position);
		this.factory = factory;
	}

	
	public ProductionUnit addProductionUnit(String name, int positionX, int positionY,float productionRate) {
		ProductionUnit New = new ProductionUnit(name,new Position(positionX+this.position.getxCoord(),positionY+this.position.getyCoord()), productionRate);
		factory.addComponent(New);
		listProductionUnit.add(New);
		return New;
	}
	public ArrayList<ProductionUnit> getlistProductionUnit(){
		return listProductionUnit;
	}
	
	
	@Override
	public Shape getShape() {
		Shape shape = new RectangleShapeImplement(5,5);
		return shape;
	}
	@Override
	public Style getStyle() {
		Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash));
		return style;
	}

	@Override
	public String toString( ) {
		return "Je m'appelle "+name+". Les unités de production présentes sont "+listProductionUnit;
				
		
	}


}
