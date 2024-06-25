package fr.tp.inf112.projects.canvas.wpaint;

import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.RectangleShape;

public class RectangleShapeImplement implements RectangleShape, Serializable {
	private static final long serialVersionUID = 3140608490342845386L;
	private int width;
	private int height;
	
	
	public RectangleShapeImplement(int width, int height) {
		this.width = width;
		this.height = height;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
