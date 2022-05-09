package backEnd;

import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class SolverPSO {
	
	public  SortedSet<Particle> swarm = new TreeSet<Particle>() ;
	public int swarm_size ;
	public float c1, c2, w ;
	public int MAX_ITER ;
	public int nb_iteration;
	
	public Particle generateParticle() {
		int idx = 0;
		Puzzle p = new Puzzle(Particle.INITIAL_STATE.getState()) ;
		String path = "";
		LinkedList<Character> possible_moves ;
		for (int i = 0 ; i < 10 ; i++ ) {
			possible_moves = p.getPossibleMoves() ;
			idx = ThreadLocalRandom.current().nextInt(possible_moves.size())  ;
			path = path + possible_moves.get(idx) ;
			p = p.goToState("" + possible_moves.get(idx)) ;
		}
		return new Particle(p, path) ;
	}
	
	public void initSwarm() {
		while(swarm.size() < swarm_size ) {
			swarm.add(generateParticle()) ;
		}
	}
	
	public String runPSO() {
		SortedSet<Particle> new_swarm ;
		
		for(nb_iteration = 0 ; nb_iteration < MAX_ITER ; nb_iteration++) {
			Particle.GBEST = swarm.first().getPosition() ;
			Particle.GBEST_FITNESS = swarm.first().calcFitness() ;
			if(Particle.GBEST_FITNESS == 0) {
				//System.out.println("SOLUTION FOUND ON ITERATION " + nb_iteration + " " + swarm.first() );
				return swarm.first().getPath();
			}
			System.out.println(swarm.first());
			new_swarm = new TreeSet<Particle>() ;
			do {
				for(Particle p : swarm) {
					new_swarm.add(p.update(w, c1, c2)) ;
				}
			}while(new_swarm.size() <= swarm_size) ;
			swarm = new_swarm ;
		}
		return "X" ;
	}
	
	/*
	public static void main(String[] args) {
		SolverPSO s = new SolverPSO() ;
		Particle.INITIAL_STATE = new Puzzle(584261379) ;
		s.swarm_size = 500 ;
		s.w = 0.7f ;
		s.c1 = 0.3f;
		s.c2 =  0.6f ;
		s.MAX_ITER = 1000 ;
		s.initSwarm() ;
		System.out.println(s.swarm);
		String po = s.runPSO();	
		 
		System.out.println(po);
	}
	*/
}
