package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;
import java.util.List;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.projects.canvas.wpaint.OvalShapeImplement;
import fr.tp.inf112.projects.canvas.wpaint.StrokeComponent;
import fr.tp.inf112.projects.canvas.wpaint.StyleComponent;
import fr.tp.inf112.robotsim.pathFinder.FactoryPathFinderImplement;
import fr.tp.inf112.robotsim.pathFinder.FactoryPathFinder;
import java.io.Serializable;


public final class Robot extends Component implements Figure, Serializable {
	
	
	private static final long serialVersionUID = -619031071174594858L;
	private Factory myFactory;
	private double speed;
	private ArrayList<Component> toVisit;
	private boolean init = false;
	private List<Position> path = new ArrayList<Position>();
	
	//List<Position> path = finder.findPath(toVisit.get(0),toVisit.get(1));
	float dash2[] = {1};
	private Style style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash2));
	
	public Robot(String name, Position position, ArrayList<Component> toVisit, Factory myFactory) {
		super(name,position);
		this.myFactory = myFactory;
		this.toVisit = toVisit;
		
	}	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean addVisit(Component component) {
		toVisit.add(component);
		return true;
		
		
	}
	public ArrayList<Component> getVisit(){
		return toVisit;
	}
	
	Component next;
	Position nextpos;
	
	public void move() {
		position = nextpos;
	}
	
	
	
	@Override
	public void behave(){
		
		
		if ((path == null) || (init == false )){
			style = new StyleComponent(RGBColor.RED, new StrokeComponent(RGBColor.BLACK,4,dash2));
				
			
			if (path != null){
				if ( (path.size() == 0) & init == false) {
					init = true;
					FactoryPathFinder finder = new FactoryPathFinderImplement(myFactory);
					for (int i=0;i<toVisit.size()-1;i++) {
						List<Position> pathBetween = finder.findPath(toVisit.get(i), toVisit.get(i+1));	
						if (pathBetween != null & path != null)
							path.addAll(pathBetween);
						if (pathBetween == null)
							path = null;
					}
					List<Position> lastPath = finder.findPath(toVisit.get(toVisit.size()-1), toVisit.get(0));
					if (lastPath != null & path != null)
						path.addAll(lastPath);
					if (lastPath == null)
						path = null;
					
				}
			
			}
		}
		
		else {
			
			style = new StyleComponent(RGBColor.WHITE, new StrokeComponent(RGBColor.BLACK,4,dash2));
			nextpos = path.get(0);
			position = nextpos;
			path.add(nextpos);
			path.remove(0);
			
		}

	}
	
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	

	public String toString() {
		return "Je m'appelle "+name+" et j'avance à "+speed+" km/h.";
	}

	public Shape getShape() {
		Shape shape = new OvalShapeImplement(10,10);
		return shape;
	}
	
	public Style getStyle() {
		return style;
	}

	
	public String getName() {
		return name;
	}



	
}


