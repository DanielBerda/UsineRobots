package fr.tp.inf112.projects.canvas.wpaint;

import fr.tp.inf112.projects.canvas.model.Vertex;

public class VertexImplement implements Vertex {

	private int xCoordinate;
	private int yCoordinate;
	
	public VertexImplement(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	@Override
	public int getxCoordinate() {
		// TODO Auto-generated method stub
		return xCoordinate;
	}

	@Override
	public int getyCoordinate() {
		// TODO Auto-generated method stub
		return yCoordinate;
	}

}
