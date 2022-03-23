package ProjetMeta;

public class Puzzle {
	
	// Number 9 represents the empty tile.
	public static int GOAL = 123456789 ;
	private int state ;
	
	public Puzzle() {
	}
	
	public Puzzle(int state) {
		this.state = state ;
	}
	
	public int getState() {
		return state;
	}
	

	public void setState(int state) {
		this.state = state;
	}
	
	//returns the position of the blank tile.
	public int getPosBlank() { 
		return Integer.toString(state).indexOf('9');
	}
	
	//returns a new instance of Puzzle with the blank tile moved up.
	public Puzzle moveUp() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx >= 3 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx - 3] ;
            newstate[posx - 3] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved down.
	public Puzzle moveDown() {
		int posx = Integer.toString(state).indexOf('9') ;
		if(posx < 6 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx + 3] ;
            newstate[posx + 3] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved left.
	public Puzzle moveLeft() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx % 3 != 0 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx - 1] ;
            newstate[posx -1 ] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved right.
	public Puzzle moveRight() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx % 3 != 2 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx + 1] ;
            newstate[posx +1 ] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	
	//returns the row of a tile. helper function for manhattan()
	private int row(int tilepos) {
		return ((tilepos - 1) / 3) + 1  ;
	}
	
	//returns the column of a tile. helper function for manhattan()
	private int col(int tilepos) {
		return (tilepos % 3 != 0) ? tilepos % 3 : 3 ;
	}
	
	//returns the sum of Manhattan distances between a tile and its approriate position.
	public int manhattan() {
		int manhattan = 0;
		char[] statestr = Integer.toString(state).toCharArray(); 
		String goalstr = Integer.toString(GOAL) ;
		for(int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			int rowdiff = Math.abs(row(statestr[i] - 48) - row(1 + goalstr.indexOf(i + 49)));
            int coldiff = Math.abs(col(statestr[i] - 48) - col(1 + goalstr.indexOf(i + 49)));
            manhattan = manhattan + rowdiff + coldiff;
		}
		return manhattan ;
	}
	
	//returns the number of misplaced tiles (also known as haming distance).
	public int misplaced() {
		int misplaced  = 0 ;
		char[] statestr = Integer.toString(state).toCharArray() ;
		char[] goalstr = Integer.toString(GOAL).toCharArray() ;
		for (int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			if (goalstr[i] != statestr[i]) misplaced ++ ;
		}
		return misplaced ; 
	}
	
	public boolean isSolution() {
		return state == GOAL ;
	}
	
	public void performMoves(String path){
		Puzzle trace = new Puzzle(state) ;
	    for(int i = 0 ; i<path.length() ; i++){
	        switch (path.charAt(i)){
	        case 'U':
	            trace = trace.moveUp();
	            break;
	        case 'D':
	        	trace = trace.moveDown();
	            break;
	        case 'L':
	            trace = trace.moveLeft() ;
	            break;
	        case 'R':
	            trace = trace.moveRight() ;
	            break;
	        }
	        System.out.println(trace);
	        System.out.println("--------------------\n");
	    }
    }
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
		
		if (!(o instanceof Puzzle) || o == null) {
            return false;
        }
		
		Puzzle p = (Puzzle) o ;
		return state == p.state ;
	}
	
	
	@Override 
	//Hashing function H4.
	public int hashCode() {
		int s = 0 ;
		char[] statestr = Integer.toString(state).toCharArray() ;
		for (int  i = 1 ; i < 9 ; i++ ) {
			s = s + (statestr[i] - 48)*i ;
		}
		return (state + s) % 362880 ;
	}
	
	@Override
	public String toString() {
		char[] statestr = Integer.toString(state).toCharArray() ;
		StringBuilder tostring = new StringBuilder() ;
		for (int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') tostring.append(" " + '\t') ;
			else tostring.append("" + statestr[i] + '\t') ;
			
			if(i % 3 == 2) tostring.append('\n');
		}
		return tostring.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solver s = new Solver() ;
		Puzzle p = new Puzzle(123456978) ;
		Node n = new Node(p ,"") ;
		
		//p.performMoves(s.aStarIterative(n, new Solver.SortByManhattan()));
		//	System.out.println( s.DFS(n, 22));
			p.performMoves(s.DFS(n, 22));
	//System.out.println(s.DFS(n, 25));	
	//System.out.println(s.aStarRecursive(n, new Solver.SortByManhattan()));
		//System.out.println(s.aStarIterative(n, new Solver.SortByMisplaced()));
		

	}

}
