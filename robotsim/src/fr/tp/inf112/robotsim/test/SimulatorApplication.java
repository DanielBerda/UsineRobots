package fr.tp.inf112.robotsim.test;




import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;
import fr.tp.inf112.robotsim.model.Area;
import fr.tp.inf112.robotsim.model.Component;
import fr.tp.inf112.robotsim.model.Door;
import fr.tp.inf112.robotsim.model.Factory;
import fr.tp.inf112.robotsim.model.ProductionUnit;
import fr.tp.inf112.robotsim.model.Robot;
import fr.tp.inf112.robotsim.model.Room;





public class SimulatorApplication {

	

	public static void main(String[] args) {

		final Logger LOGGER = Logger.getLogger(SimulatorApplication.class.getName());
		LOGGER.info("Starting the robot simulator...");
		LOGGER.config("With parameters " + Arrays.toString(args) + ".");
		
		
		Collection<Figure> components = new ArrayList<Figure>();

		
		
		
		FileCanvasChooser fileCanvasChooser = new FileCanvasChooser("factory","Usine");
		Factory myFactory = new Factory("Usine",components);
		
		try {
			myFactory.setId(fileCanvasChooser.newCanvasId());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		ArrayList<Component> toVisit = new ArrayList<Component>();
		
		Room room1 = myFactory.addRoom("Salle 1", 80, 80, myFactory);
		Door porte1 = room1.addDoor("", 0, 5);
		porte1.setOpen(true);
		Area aire1 = room1.addArea("Aire", 2,2, myFactory);
		ProductionUnit machine1 = aire1.addProductionUnit("Machine 1",3,3,1);
		room1.addWall("", 0, 0, 10, 0, myFactory);
		room1.addWall("", 0, 0, 0, 1, myFactory);
		room1.addWall("", 10, 0, 0, 10, myFactory);
		room1.addWall("", 0, 10, 10, 0, myFactory);
		
		
		Room room2 = myFactory.addRoom("Salle 2", 50, 80, myFactory);
		Door porte2 = room2.addDoor("", 0, 5);
		porte2.setOpen(true);
		Area aire2 = room2.addArea("Aire", 2,2, myFactory);
		ProductionUnit machine2 = aire2.addProductionUnit("Machine 2", 3, 3, 1);
		room2.addWall("", 0, 10, 10, 0, myFactory);
		room2.addWall("", 0, 0, 10, 0, myFactory);
		room2.addWall("", 0, 0, 0, 1, myFactory);
		room2.addWall("", 10, 0, 0, 10, myFactory);
		
		
		Room room0 = myFactory.addRoom("Salle 0", 40, 10, myFactory);
		Area aire0 = room0.addArea("Aire 0", 3, 3, myFactory);
		ProductionUnit machine0 = aire0.addProductionUnit("Machine 0", 2, 2, 1);
		Door door0 = room0.addDoor("", 0, 5);
		door0.setOpen(true);
		room0.addWall("", 0, 10, 10, 0, myFactory);
		room0.addWall("", 10, 0, 0, 10, myFactory);
		room0.addWall("", 0, 0, 10, 0, myFactory);
		room1.addWall("", 0, 0, 0, 1, myFactory);
		
		toVisit.add(machine0);
		toVisit.add(machine1);
		toVisit.add(machine2);
	
		Robot New = myFactory.addRobot("r1",toVisit,myFactory);
		New.setSpeed(1);
		
		
		new CanvasViewer(new SimulatorController(myFactory));
		
	}
	
}
