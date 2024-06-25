package fr.tp.inf112.projects.canvas.wpaint;

import java.util.Set;


import fr.tp.inf112.projects.canvas.model.PolygonShape;
import fr.tp.inf112.projects.canvas.model.Vertex;

public class PolygonShapeImplement implements PolygonShape {

	
	private Set<Vertex> vertices;
	
	public PolygonShapeImplement(Set<Vertex> vertices){
		this.vertices = vertices;
	}
	
	@Override
	public Set<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return vertices;
	}
	

}
