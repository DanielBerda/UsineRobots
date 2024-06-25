package fr.tp.inf112.projects.canvas.wpaint;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;


public class StyleComponent implements Style, Serializable {
	private static final long serialVersionUID = -7493858511928352695L;
	Color color;
	Stroke stroke;
	
	public StyleComponent(Color color, Stroke stroke) {
		this.color = color;
		this.stroke = stroke;
	}

	
	
	public Color getBackgroundColor() {
		return color;		
	}
	public Stroke getStroke() {
		return stroke;
		
	}
}
