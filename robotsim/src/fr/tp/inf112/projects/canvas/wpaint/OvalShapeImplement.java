package fr.tp.inf112.projects.canvas.wpaint;

import java.io.Serializable;


import fr.tp.inf112.projects.canvas.model.OvalShape;

public class OvalShapeImplement implements OvalShape, Serializable {
	private static final long serialVersionUID = -7481247708424032150L;
	int width;
	int height;
	
	public OvalShapeImplement(int width, int height) {
		this.width = width;
		this.height = height;
	}
	

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
