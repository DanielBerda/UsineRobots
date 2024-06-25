package fr.tp.inf112.robotsim.pathFinder;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import fr.tp.inf112.projects.graph.DijkstraAlgorithm;
import fr.tp.inf112.projects.graph.impl.GridEdge;
import fr.tp.inf112.projects.graph.impl.GridGraph;
import fr.tp.inf112.projects.graph.impl.GridVertex;
import fr.tp.inf112.robotsim.model.Component;
import fr.tp.inf112.robotsim.model.Factory;
import fr.tp.inf112.robotsim.model.Position;
import fr.tp.inf112.robotsim.model.ProductionUnit;
import fr.tp.inf112.robotsim.model.Room;
import fr.tp.inf112.projects.graph.Vertex;



public class FactoryPathFinderImplement implements FactoryPathFinder, Serializable {

	private static final long serialVersionUID = 7346664782949167912L;
	private ArrayList<GridVertex> ListVertex = new ArrayList<GridVertex>();
	private static HashMap<Position, GridVertex> vertex = new HashMap<Position, GridVertex>();
	private final Factory myFactory;
	
	
	public FactoryPathFinderImplement(Factory myFactory) {
		this.myFactory = myFactory;
	}
	
	public static GridVertex addVertex(String label,Position position) {
		GridVertex newVertex = new GridVertex(label,position.getxCoord(),position.getyCoord());
		vertex.put(position,newVertex);
		return newVertex;
	}
	
	
	public static GridEdge addEdge(GridGraph graphe, GridVertex vertex1, GridVertex vertex2) {
		GridEdge edge = new GridEdge(graphe,vertex1,vertex2,1);
		return edge;
	}
	
	
	public GridVertex vertex(int xCoord, int yCoord) {
		for (GridVertex x : ListVertex) {
			if ((x.getxCoordinate() == xCoord) & (x.getyCoordinate() == yCoord)){
				return x;
			}
		
		}
		return null;
	}

	
	
	public GridGraph constructGraph() {
		GridGraph graphe = new GridGraph();
		ArrayList<ArrayList<GridVertex>> tabVertex = new ArrayList<ArrayList<GridVertex>>();
		for (int i=0;i<100;i++) {
			ArrayList<GridVertex> rowVertex = new ArrayList<GridVertex>();
			for (int j=0;j<100;j++) {
				rowVertex.add( FactoryPathFinderImplement.addVertex("("+i+";"+j+")",new Position(i,j)) );
			}
			tabVertex.add(rowVertex);
		}	
		
		
		
		for (int i=0;i<99;i++) {
			for (int j=0;j<99;j++) {
				 GridEdge edge1 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(j),tabVertex.get(i).get(j+1));
				
				graphe.addVertex(tabVertex.get(i).get(j));
				
				graphe.addEdge(edge1);
				tabVertex.get(i).get(j).addEdge(edge1);
				tabVertex.get(i).get(j+1).addEdge(edge1);
				
				GridEdge edge2 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(j),tabVertex.get(i+1).get(j));
				graphe.addEdge(edge2);
				tabVertex.get(i).get(j).addEdge(edge2);
				tabVertex.get(i+1).get(j).addEdge(edge2);
			}
			
			GridEdge edge3 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(99),tabVertex.get(i+1).get(99));
			
			
			graphe.addVertex(tabVertex.get(i).get(99));
			
			graphe.addEdge(edge3);
			tabVertex.get(i).get(99).addEdge(edge3);
			tabVertex.get(i+1).get(99).addEdge(edge3);
		}
		for (int j=0;j<99;j++) {
			GridEdge edge4 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(99).get(j),tabVertex.get(99).get(j+1));
			
			
			graphe.addVertex(tabVertex.get(99).get(j));
			
			
			graphe.addEdge(edge4);
			tabVertex.get(99).get(j).addEdge(edge4);
			tabVertex.get(99).get(j+1).addEdge(edge4);
		}
		
		graphe.addVertex(tabVertex.get(99).get(99));
		
		
		
		
		
		return graphe;
	}
	
	public List<GridVertex> contours(Room room){
		if (room == null) {
			return null;
		}
		else {
			ArrayList<ArrayList<GridVertex>> tabVertex = new ArrayList<ArrayList<GridVertex>>();
			for (int i=0;i<100;i++) {
				ArrayList<GridVertex> rowVertex = new ArrayList<GridVertex>();
				for (int j=0;j<100;j++) {
					rowVertex.add( FactoryPathFinderImplement.addVertex("("+i+";"+j+")",new Position(i,j)) );
				}
				tabVertex.add(rowVertex);
			}	
		
			List<GridVertex> contoursRoom = new ArrayList<GridVertex>();
			for (int i = room.getxCoordinate();i<room.getxCoordinate()+10;i++)
				contoursRoom.add(tabVertex.get(i).get(room.getyCoordinate()));
			for (int i = room.getxCoordinate();i<room.getxCoordinate()+10;i++)
				contoursRoom.add(tabVertex.get(i).get(10+room.getyCoordinate()));
			for (int j = room.getyCoordinate();j<room.getyCoordinate()+10;j++)
				contoursRoom.add(tabVertex.get(room.getxCoordinate()+10).get(j));
			for (int j = room.getyCoordinate();j<room.getyCoordinate()+10;j++)
				contoursRoom.add(tabVertex.get(room.getxCoordinate()).get(j));	
			contoursRoom.add(tabVertex.get(room.getxCoordinate()+10).get(room.getyCoordinate()+10));
			return contoursRoom;
			
		}
	}
	
	public GridGraph constructGraph(Component init, Component end) {
		GridGraph graphe = new GridGraph();
		ArrayList<ArrayList<GridVertex>> tabVertex = new ArrayList<ArrayList<GridVertex>>();
		for (int i=0;i<100;i++) {
			ArrayList<GridVertex> rowVertex = new ArrayList<GridVertex>();
			for (int j=0;j<100;j++) {
				rowVertex.add( FactoryPathFinderImplement.addVertex("("+i+";"+j+")",new Position(i,j)) );
			}
			tabVertex.add(rowVertex);
		}	
		
		
		
		for (int i=0;i<99;i++) {
			for (int j=0;j<99;j++) {
				GridEdge edge1 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(j),tabVertex.get(i).get(j+1));
				
				graphe.addVertex(tabVertex.get(i).get(j));
				
				if (myFactory.overlays(tabVertex.get(i).get(j+1)) == null  ) {
					graphe.addEdge(edge1);
					tabVertex.get(i).get(j).addEdge(edge1);
					tabVertex.get(i).get(j+1).addEdge(edge1);
				}
				else if (     (myFactory.overlays(tabVertex.get(i).get(j+1)).getListProductionUnit().contains(init)) || (myFactory.overlays(tabVertex.get(i).get(j+1)).getListProductionUnit().contains(end))      ) {
					//if ( (myFactory.overlays(tabVertex.get(i).get(j+1)).getListProductionUnit().contains(init))) {
						//if (tabVertex.get(i).get(j+1).getLabel())
					if (!(myFactory.overlaysWall(tabVertex.get(i).get(j+1)))){
						graphe.addEdge(edge1);
						tabVertex.get(i).get(j).addEdge(edge1);
						tabVertex.get(i).get(j+1).addEdge(edge1);
					}
					
				}
				
				GridEdge edge2 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(j),tabVertex.get(i+1).get(j));
				if (myFactory.overlays(tabVertex.get(i+1).get(j)) == null ) {
					graphe.addEdge(edge2);
					tabVertex.get(i).get(j).addEdge(edge2);
					tabVertex.get(i+1).get(j).addEdge(edge2);
				}
				else if (   (myFactory.overlays(tabVertex.get(i+1).get(j)).getListProductionUnit().contains(init)) || (myFactory.overlays(tabVertex.get(i+1).get(j)).getListProductionUnit().contains(end))  ) {
					if (!(myFactory.overlaysWall(tabVertex.get(i+1).get(j)))){
						graphe.addEdge(edge2);
						tabVertex.get(i).get(j).addEdge(edge2);
						tabVertex.get(i+1).get(j).addEdge(edge2);
					}
				}
			}
			
			GridEdge edge3 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(i).get(99),tabVertex.get(i+1).get(99));
			
			
			graphe.addVertex(tabVertex.get(i).get(99));
			
			if (myFactory.overlays(tabVertex.get(i+1).get(99)) == null  ) {
				graphe.addEdge(edge3);
				tabVertex.get(i).get(99).addEdge(edge3);
				tabVertex.get(i+1).get(99).addEdge(edge3);
			}
			else if (     (myFactory.overlays(tabVertex.get(i+1).get(99)).getListProductionUnit().contains(init)) || (myFactory.overlays(tabVertex.get(i+1).get(99)).getListProductionUnit().contains(end))   )  {
				if (!(myFactory.overlaysWall(tabVertex.get(i+1).get(99)))){
					graphe.addEdge(edge3);
					tabVertex.get(i).get(99).addEdge(edge3);
					tabVertex.get(i+1).get(99).addEdge(edge3);
				}
				
			}
		}
		for (int j=0;j<99;j++) {
			GridEdge edge4 = FactoryPathFinderImplement.addEdge(graphe,tabVertex.get(99).get(j),tabVertex.get(99).get(j+1));
			

			graphe.addVertex(tabVertex.get(99).get(j));
			
			if (myFactory.overlays(tabVertex.get(99).get(j+1)) == null || (myFactory.overlays(tabVertex.get(99).get(j+1)).getListProductionUnit().contains(init)) || (myFactory.overlays(tabVertex.get(99).get(j+1)).getListProductionUnit().contains(end))   ) {
				if (!(myFactory.overlaysWall(tabVertex.get(99).get(j+1)))){
					graphe.addEdge(edge4);
					tabVertex.get(99).get(j).addEdge(edge4);
					tabVertex.get(99).get(j+1).addEdge(edge4);
				}
			}
		}
		
		graphe.addVertex(tabVertex.get(99).get(99));
		
		
		
		
		
		return graphe;
	}
	
	
	
	
	
	
	
	@Override
	public List<Position> findPath(Position init, Position end) {
		List<Vertex> path;
		
		ArrayList<ArrayList<GridVertex>> tabVertex = new ArrayList<ArrayList<GridVertex>>();
		for (int i=0;i<100;i++) {
			ArrayList<GridVertex> rowVertex = new ArrayList<GridVertex>();
			for (int j=0;j<100;j++) {
				rowVertex.add( FactoryPathFinderImplement.addVertex("("+i+";"+j+")",new Position(i,j)) );
			}
			tabVertex.add(rowVertex);
		}	
		GridGraph graphe = constructGraph();
		
		path = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+init.getxCoord()+";"+init.getyCoord()+")"), graphe.getVertex("("+end.getxCoord()+";"+end.getyCoord()+")"));
		System.out.println("Le chemin à suivre :"+path);
		
		List<Position> pathPosition = new ArrayList<Position>();
		for (Vertex x : path) {
			GridVertex y = (GridVertex) x;
			pathPosition.add(new Position(y.getxCoordinate(),y.getyCoordinate()));
		}
		System.out.println("Les positions à emprunter :"+pathPosition);
		return pathPosition;
		
		
		
		
		
	}

	@Override
	public List<Position> findPath(Component init, 	Component end) {
		List<Vertex> path = new ArrayList<Vertex>();
		
		ArrayList<ArrayList<GridVertex>> tabVertex = new ArrayList<ArrayList<GridVertex>>();
			for (int i=0;i<100;i++) {
				ArrayList<GridVertex> rowVertex = new ArrayList<GridVertex>();
				for (int j=0;j<100;j++) {
					rowVertex.add( FactoryPathFinderImplement.addVertex("("+i+";"+j+")",new Position(i,j)) );
				}
				tabVertex.add(rowVertex);
			}	
			GridGraph graphe = constructGraph(init,end);
			List<Position> pathPosition = new ArrayList<Position>();
		if (!(init instanceof ProductionUnit) & !(end instanceof ProductionUnit)) {
			path = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+init.getxCoordinate()+";"+init.getyCoordinate()+")"), graphe.getVertex("("+end.getxCoordinate()+";"+end.getyCoordinate()+")"));
			System.out.println("Le chemin à suivre :"+path);
			
			
			
		}
		
		
		else if (!(init instanceof ProductionUnit) & (end instanceof ProductionUnit) ) {
			if (myFactory.searchDoor((ProductionUnit) end) != null) {
				if (myFactory.searchDoor((ProductionUnit) end).getOpen()) {
					List<Vertex> pathBetween1 = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+init.getxCoordinate()+";"+init.getyCoordinate()+")"), graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) end).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) end).getyCoordinate()+")"));
					List<Vertex> pathBetween2 = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) end).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) end).getyCoordinate()+")"), graphe.getVertex("("+end.getxCoordinate()+";"+end.getyCoordinate()+")"));
					
					path.addAll(pathBetween1);
					path.addAll(pathBetween2);
					System.out.println(path);
				}
			}
			
		}
			
		else if ( (init instanceof ProductionUnit) & (end instanceof ProductionUnit) ) {
			if ((myFactory.searchDoor((ProductionUnit) init) != null) & (myFactory.searchDoor((ProductionUnit) end) != null) ) {
				if ((myFactory.searchDoor((ProductionUnit) init).getOpen()) & (myFactory.searchDoor((ProductionUnit) end).getOpen()) ) {
					List<Vertex> pathBetween1 = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+init.getxCoordinate()+";"+init.getyCoordinate()+")"), graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) init).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) init).getyCoordinate()+")"));
					List<Vertex> pathBetween2 = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) init).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) init).getyCoordinate()+")"), graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) end).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) end).getyCoordinate()+")"));
					List<Vertex> pathBetween3 = DijkstraAlgorithm.findShortestPath(graphe, graphe.getVertex("("+myFactory.searchDoor((ProductionUnit) end).getxCoordinate()+";"+myFactory.searchDoor((ProductionUnit) end).getyCoordinate()+")"), graphe.getVertex("("+end.getxCoordinate()+";"+end.getyCoordinate()+")"));
					
					path.addAll(pathBetween1);
					path.addAll(pathBetween2);
					path.addAll(pathBetween3);
								
				}
				else {
					path = null;
				}
			}
		}
		
			
		if ( path!=null	) {
			for (Vertex x : path) {
				GridVertex y = (GridVertex) x;
				pathPosition.add(new Position(y.getxCoordinate(),y.getyCoordinate()));
			}
			System.out.println("Les positions à emprunter :"+pathPosition);
			return pathPosition;
		}
		else {
			return null;
		}
			
			
	}
	

		
		
}

