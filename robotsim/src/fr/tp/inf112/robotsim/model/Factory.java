package fr.tp.inf112.robotsim.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import fr.tp.inf112.projects.canvas.controller.Observable;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.RectangleShape;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;
import fr.tp.inf112.projects.graph.impl.GridVertex;

public final class Factory extends Component implements Canvas, Observable, Serializable  {

	

	private static final long serialVersionUID = 202405251841L;
	private String id;
	private boolean isRunning = false;
	
	
	
	
	
	private Collection<Figure> components = new ArrayList<Figure>();

	
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private ArrayList<Room> listRoom = new ArrayList<Room>();
	
	private transient Set<Observer> observers = new HashSet<>();

	private final Style style = new StyleComponent(RGBColor.GREEN, new StrokeComponent(RGBColor.GREEN,4,dash));
	public final Style getStyle() {
		return style;
	}
	

	
	public Factory(String name, Collection<Figure> components) {

		super(name,new Position(0,0));
		this.components = components;	
	}

	public ArrayList<Room> getListRoom(){
		return listRoom;
	}
	
	
	
	public void startSimulation() {
		isRunning = true;
		notifyObservers();
	}
	
	public void stopSimulation() {
		isRunning = false;
		notifyObservers();
	}
	
	public boolean isSimulationStarted() {
		return isRunning;
	}
	
	
	
	public int getWidth() {
		return 100;
	}
		
	public int getHeight() {
		return 100;
	}
	
	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	

	public void behave() {
		
		notifyObservers();
		robots.get(0).behave();
		
	}
	
	
	
	

	public Robot addRobot(String name, ArrayList<Component> toVisit,Factory myFactory) {
		
		if (checkRobotName(name)) {
			//pos init : 45,15
			Robot New = new Robot(name,new Position(45,15),toVisit,myFactory);
			New.setSpeed(1);
			this.robots.add(New);
			this.components.add((Figure) New);
			return New;
		}
		else
			return null;
		
	}
	
	private boolean checkRobotName(String name) {
		for(Robot x : robots) {
			
			if (x.getName().equals(name)) {
				return false;
			}
		}
		return true;
		
	}
	
	public ChargingStation addChargingStation(String name, int positionX, int positionY, float productionRate) {
		ChargingStation New = new ChargingStation(name,new Position(positionX,positionY),productionRate);
		
		addComponent(New);
		return New;
	}
	
	public Room addRoom(String name, int positionX, int positionY, Factory factory) {
		Room New = new Room(name,new Position(positionX,positionY),factory);
		this.components.add((Figure) New);
		listRoom.add(New);
		return New;
	}

	
	public boolean addComponent(Figure component) {
		components.add(component);
		return true;
	}
	
	public void printToConsole() {
		System.out.println("Nom : "+name+" Robots : "+robots);
		
	}
	
	public String toString() {
		return "Je m'appelle "+name+" et je contiens "+components;
	}
	
	public Collection<Figure> getFigures(){
		return components;
	}
	
	protected Set<Observer> getObservers() {
		if (observers == null) {
			observers = new HashSet<>();
		}
		return observers;	
		}
	
	
	@Override
	public boolean addObserver(Observer observer) {
	return observers.add(observer);
	}
	
	@Override
	public boolean removeObserver(Observer observer) {
	return observers.remove(observer);
	}

	public void notifyObservers() { 
		for (Observer observer : observers) {
			observer.modelChanged();
		}
	}
	
	//Teste si un sommet est dans une salle de l'usine :
	//Pour éviter les collisions, on augmente la taille de la fenêtre de la moitié du diamètre du robot
	public Room overlays(GridVertex vertex) {
		for (Room x : listRoom) {
			Component y = (Component) x;
			final Shape figShape = y.getShape();
			
			
			if (figShape instanceof RectangleShape) {
				RectangleShape z = (RectangleShape) figShape;
				if ((vertex.getxCoordinate()>y.getxCoordinate()-4)&(vertex.getxCoordinate()<y.getxCoordinate()+4+z.getWidth())&(vertex.getyCoordinate()>y.getyCoordinate()-4)&(vertex.getyCoordinate()<y.getyCoordinate()+z.getHeight()+4)) {
						return x;
				}
			}
			
		}
		return null;
	}
	//Teste si un sommet est dans un mur :
	public boolean overlaysWall(GridVertex vertex) {
		List<Wall> listWall = new ArrayList<Wall>();
		for (Room x : listRoom) {
			listWall.addAll(x.getListWall());
		}
		for (Wall x : listWall) {
			Component y = (Component) x;
			final Shape figShape = y.getShape();
			
			
			if (figShape instanceof RectangleShape) {
				RectangleShape z = (RectangleShape) figShape;
				if ((vertex.getxCoordinate()>y.getxCoordinate()-4)&(vertex.getxCoordinate()<y.getxCoordinate()+4+z.getWidth())&(vertex.getyCoordinate()>y.getyCoordinate()-4)&(vertex.getyCoordinate()<y.getyCoordinate()+z.getHeight()+4)) {
						return true;
				}
			}
			
		}
		return false;
		
		
	}
	
	//Recherche de la porte d'une salle à partir d'une unité de production : 
	public Door searchDoor(ProductionUnit productionUnit) {
		for (Room x : listRoom) {
			for (Area y : x.getListArea()) {
				for (ProductionUnit z : y.getlistProductionUnit())
					if (z.getName() == productionUnit.getName()){
						return x.getDoor();
					}
			}
		}
		return null;
	}

	
}
