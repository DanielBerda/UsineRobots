package fr.tp.inf112.robotsim.test;

import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;
import fr.tp.inf112.robotsim.model.Factory;
import fr.tp.inf112.robotsim.persistenceManager.CanvasPersistenceManagerImplement;

public class SimulatorController implements CanvasViewerController {

	private Factory factoryModel;
	
	private CanvasPersistenceManager persistenceManager;
	
	
	public SimulatorController(Factory factoryModel) {
		
		this.factoryModel = factoryModel;
		FileCanvasChooser canvasChooser = new FileCanvasChooser("factory","Usine");
		this.persistenceManager = new CanvasPersistenceManagerImplement(canvasChooser);
	}
	
	@Override
	public boolean addObserver(Observer observer) {
		factoryModel.addObserver(observer);
		return false;
	}

	@Override
	public boolean removeObserver(Observer observer) {
		factoryModel.removeObserver(observer);
		return false;
	}

	@Override
	public Canvas getCanvas() {
		return (Canvas) factoryModel;
	}

	@Override
	public void setCanvas(Canvas canvasModel) {
		

	}

	@Override
	public CanvasPersistenceManager getPersistenceManager() {
		
		return persistenceManager;
	}

	@Override
	public void startAnimation() {
		
		
		factoryModel.startSimulation();
		while (factoryModel.isSimulationStarted()) {
			factoryModel.behave();
			
			try {
				Thread.sleep( 100 );
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	

	}

	@Override
	public void stopAnimation() {
		factoryModel.stopSimulation();

	}

	@Override
	public boolean isAnimationRunning() {
		return factoryModel.isSimulationStarted();
	}

}
