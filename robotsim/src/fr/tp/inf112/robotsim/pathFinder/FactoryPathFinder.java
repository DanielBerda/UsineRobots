package fr.tp.inf112.robotsim.pathFinder;
import java.util.List;
import fr.tp.inf112.robotsim.model.Component;
import fr.tp.inf112.robotsim.model.Position;

public interface FactoryPathFinder {
	/*
	 *  Cette méthode définira deux paramètres, le premier
	étant un composant source et le deuxième un composant cible, ces deux paramètres servant
	de sommet de départ et d’arrivée du chemin le plus court à calculer.
	 */
	
	List<Position> findPath(Position init, Position next);
	List<Position> findPath(Component init, Component next);
	
	
	
}
