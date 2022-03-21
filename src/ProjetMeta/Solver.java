package ProjetMeta;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class Solver {
	
	HashSet<Integer> fermes ; //set containing the states that have already been visited.
	HashSet<Node> ouverts; //set containing the states that have yet to be visited.
	
	public Solver() {
		fermes = new HashSet<Integer> () ;
		ouverts = new HashSet<Node> () ;
	}
	
	public static class SortByManhattan implements Comparator<Node>{	
		@Override
		public int compare(Node a , Node b) {
			int x = a.heurManhattan() - b.heurManhattan() ; 
			return (x!=0) ? x : a.manhattan() - b.manhattan() ; 
		}
	}
	
	public static class SortByMisplaced implements Comparator<Node>{	
		@Override
		public int compare(Node a , Node b) {
			int x = a.heurMisplaced() - b.heurMisplaced() ; 
			return (x!=0) ? x : a.misplaced() - b.misplaced() ; 
		}
	}
	
	//returns True if the node has already been visited, returns False otherwise.
	public boolean visited(Node n) {
		return fermes.contains(n.getState()) ;
	}
	
	//Recursive implementation of the A* algorithm using the hamming distance heuristic function and the manhattan distance heuristic function.
	//returns the combination of moves that leads to the goal.
	public String aStarRecursive(Node root, Comparator<Node> comp) {
		
		if (root.isSolution()) {
			fermes = new HashSet<Integer>() ;
			ouverts = new HashSet<Node>() ;
			return root.getPath() ;
		}
		
		for(Node n : root.getChildNodes()) {
			if(!visited(n)) {
				ouverts.add(n);
			}
		}
		fermes.add(root.getState());
		
		Node n ;
		do {
			n =Collections.min(ouverts, comp) ;
			ouverts.remove(n) ;
		}while(visited(n));
		
		return(aStarRecursive(n, comp)) ;
	}
	
	//Iterative implementation of the A* algorithm using the hamming distance heuristic function and the manhattan distance heuristic function.
	//returns the combination of moves that leads to the goal.
	public String aStarIterative(Node root, Comparator<Node> comp) {
		
		if (root.isSolution()) return root.getPath() ;
		
		do {
			for(Node n : root.getChildNodes()) {
				if(!visited(n)) {
					ouverts.add(n);
				}
			}
			fermes.add(root.getState());
			
			do {
				root =Collections.min(ouverts, comp) ;
				ouverts.remove(root) ;
			}while(visited(root));
			
		}while(!root.isSolution() && !ouverts.isEmpty()) ;
		
		fermes = new HashSet<Integer>() ;
		ouverts = new HashSet<Node>() ;
		
		if(root.isSolution()) return root.getPath() ;
		else return "" ;
	
	}

}
