package ProjetMeta;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;


public class Solver {
	
	HashSet<Integer> fermes ; //set containing the states that have already been visited.
	HashSet<Node> ouverts; //set containing the states that have yet to be visited.
	HashMap<Integer, Integer> fermes_dfs ; //set containing the states that have already been visited with their resepective minimal depth they can be found at.
	
	public Solver() {
		fermes = new HashSet<Integer> () ;
		ouverts = new HashSet<Node> () ;
		fermes_dfs = new HashMap<Integer, Integer>();
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
	
	public static class SortByManhattan_RevPenalty implements Comparator<Node>{	
		@Override
		public int compare(Node a , Node b) {
			int x = a.heurManhattan_RevPenalty() - b.heurManhattan_RevPenalty() ; 
			return (x!=0) ? x : (a.manhattan() + a.getReversalCount()) - (b.manhattan() + b.getReversalCount()) ; 
		}
	}
	
	//returns True if the node has already been visited, returns False otherwise.
	public boolean visited(Node n) {
		return fermes.contains(n.getState()) ;
	}
	
	//returns True if the node has already been visited at a lower depth, returns False otherwise.
	public boolean visitedDFS(Node n) {
		Integer depth = fermes_dfs.get(n.getState()) ;
		
		return (depth == null) ? false : depth <= n.nodeDepth() ;  
	}
	
	//Recursive implementation of the A* algorithm using the hamming distance heuristic function and the manhattan distance heuristic function.
	//returns the combination of moves that leads to the goal.
	public String aStarRecursive(Node root, Comparator<Node> comp) {
		
		if (root.isSolution()) {
			//fermes = new HashSet<Integer>() ;
			//ouverts = new HashSet<Node>() ;
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
		
		if(!root.isSolvable()) return "" ;
		if(root.isSolution()) return root.getPath() ;
		
		do {
			fermes.add(root.getState());
			for(Node n : root.getChildNodes()) {
				if(!visited(n)) {
					ouverts.add(n);
				}
			}

			do {
				root =Collections.min(ouverts, comp) ;
				ouverts.remove(root) ;
			}while(visited(root));
			
		}while(!root.isSolution() && !ouverts.isEmpty()) ;
		
		System.out.println(fermes.size());
		System.out.println(ouverts.size());
		
		if(root.isSolution()) return root.getPath() ;
		else return "" ;
	
	}
	
	//Depth first search.
	public String dFS(Node root, int seuil) {
		
		if(!root.isSolvable()) return "" ;
		
		Stack<Node> stack =  new Stack<Node>() ;
		Node parent ;

		stack.push(root) ;
		ouverts.add(root);
		while(!stack.isEmpty()) {
			parent = stack.pop();
			ouverts.remove(parent);
			if (parent.isSolution()) {
				System.out.println(fermes_dfs.size());
				return parent.getPath() ;
			}
			
			if(!visitedDFS(parent) && parent.nodeDepth()<seuil) {
				fermes_dfs.put(parent.getState(), parent.nodeDepth()) ;
				for(Node n : parent.getChildNodes()) {
					if(!visitedDFS(n) && !n.equals(parent)) {
						ouverts.add(n);
						stack.push(n);
					}
				}
			}
		}
		
		return ("Impossible a resoudre avec un seuil de "+ seuil) ;
	}

}
