package fr.tp.inf112.projects.canvas.wpaint;



import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

public class StrokeComponent implements Stroke, Serializable {
	
	private static final long serialVersionUID = 8363025047107361026L;
	Color color;
	float thickness;
	float[] dash;
	
	public StrokeComponent(Color color, float thickness, float[] dash) {
		this.color = color;
		this.thickness = thickness;
		this.dash = dash;
	}
	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public float getThickness() {
		// TODO Auto-generated method stub
		return thickness;
	}

	@Override
	public float[] getDashPattern() {
		// TODO Auto-generated method stub
		return dash;
	}

}
