package fr.tp.inf112.robotsim.persistenceManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;


public class CanvasPersistenceManagerImplement extends AbstractCanvasPersistenceManager implements CanvasPersistenceManager {


	
	public CanvasPersistenceManagerImplement(CanvasChooser canvasChooser) {
		super(canvasChooser);
	}
		
	
	@Override
	public Canvas read(String canvasId) throws IOException {
		InputStream fileInStream = new FileInputStream(canvasId);
		InputStream bufInStream = new BufferedInputStream(fileInStream);
		ObjectInputStream objInStream = new ObjectInputStream(bufInStream);
		try {
			Canvas canvas = (Canvas) objInStream.readObject();
			return canvas;
		} 
		catch (ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
			return null;
		}
		finally {
			objInStream.close();
		}
	}
			

	@Override
	public void persist(Canvas canvasModel) throws IOException {
		OutputStream fileOutStream = new FileOutputStream(canvasModel.getId());
		OutputStream bufOutStream = new BufferedOutputStream(fileOutStream);
		ObjectOutputStream objOutStream = new ObjectOutputStream(bufOutStream);
		try {
			objOutStream.writeObject(canvasModel);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			
			objOutStream.close();
		}
	}

	@Override
	public boolean delete(Canvas canvasModel) throws IOException {
		File file = new File(canvasModel.getId());
		return file.delete();
	}


}
