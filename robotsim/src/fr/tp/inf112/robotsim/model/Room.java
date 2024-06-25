package fr.tp.inf112.robotsim.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.RectangleShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;



public final class Room extends Component implements Serializable {
	private static final long serialVersionUID = -1395562945878195893L;

	private ArrayList<Area> listArea = new ArrayList<Area>();
	private final Factory factory;
	private Door door;
	private List<Wall> listWall = new ArrayList<Wall>();
	
	public Room(String name, Position position, Factory factory) {
		super(name,position);
		this.factory = factory;
	}
	public List<Wall> getListWall(){
		return listWall;
	}
	public Door getDoor() {
		return door;
	}
	public ArrayList<Area> getListArea(){
		return listArea;
	}
	public ArrayList<ProductionUnit> getListProductionUnit(){
		ArrayList<ProductionUnit> listProductionUnit = new ArrayList<ProductionUnit>();
		for (Area x : listArea) {
			
			ArrayList<ProductionUnit> listProductionUnitx = x.getlistProductionUnit();
			listProductionUnit.addAll(listProductionUnitx);
		}
		return listProductionUnit;
		
	}
	
	public Door addDoor(String name, int positionX, int positionY) {
		door = new Door(name,new Position(positionX+position.getxCoord(),positionY+position.getyCoord()));
		factory.addComponent(door);
		return door;
	}
	
	public Area addArea(String name, int positionX, int positionY, Factory factory) {
		Area New = new Area(name,new Position(positionX+this.position.getxCoord(),positionY+this.position.getyCoord()),factory);
		factory.addComponent(New);
		listArea.add(New);
		return New;
	}
	
	public Wall addWall(String name, int positionX, int positionY, int longueur, int hauteur, Factory factory) {
		Wall New = new Wall(name,new Position(positionX+this.position.getxCoord(),positionY+this.position.getyCoord()),longueur, hauteur);
		factory.addComponent(New);
		listWall.add(New);
		
		return New;
	}
	
	
	
	@Override
	public Shape getShape() {
		Shape shape = new RectangleShapeImplement(10,10);
		return shape;
	}
	
	@Override
	public Style getStyle() {
		float dash2[] = {1};
		Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,5,dash2));
		return style;
	}

	@Override
	public String toString( ) {
		return "Je m'appelle "+name+". Les aires présentes sont "+listArea;
				
		
	}
}
