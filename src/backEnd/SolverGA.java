package backEnd;

import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;   
public class SolverGA {
	
	public  SortedSet<Chromosome> population = new TreeSet<Chromosome>() ;
	public int pop_size ;
	public double cross_rate, mut_rate ;
	public static String[] Moves = {"U", "D", "L", "R"}  ;
	public float pop_fitness ;
	public static int  MIN_LENGTH=6;
	
	
	//Selection function. (Roulette selection)
	public Chromosome[] selectPair() {
		double proba ;
		Chromosome [] parents = new Chromosome[2] ;
		float proba_sum ;
		int i ;
		do {
			i = 0 ;
			while(i < 2) {
				proba_sum = 0 ;
				proba = ThreadLocalRandom.current().nextDouble(1) ;
				for(Chromosome c : population ) {
					if (c.getFitness() == 1.0) break ;
					proba_sum = proba_sum + (c.getFitness() / pop_fitness);
					if (proba <= proba_sum  && c.getFitness()!=1.0) {
						parents[i] = c;
						i++ ;
						break;
					}
				}
			}
		}while(parents[0].equals(parents[1])) ;
		return parents ;
	}
	
	public Chromosome[] crossOver(Chromosome p1 , Chromosome p2) {
		double proba = ThreadLocalRandom.current().nextDouble(1) ;
		
		if(proba < cross_rate)
			return p1.crossOver(p2);
		else
			return new Chromosome[] {new Chromosome(p1), new Chromosome(p2)} ;
	}
	
	//generate valid chromosome.	
	private Chromosome generateChromosome() {
		int idx = 0;
		Puzzle p = new Puzzle(Chromosome.INITIAL_STATE.getState()) ;
		String path = "";
		LinkedList<Character> possible_moves ;
		for (int i = 0 ; i < MIN_LENGTH ; i++ ) {
			possible_moves = p.getPossibleMoves() ;
			idx = ThreadLocalRandom.current().nextInt(possible_moves.size())  ;
			path = path + possible_moves.get(idx) ;
			p = p.goToState("" + possible_moves.get(idx)) ;
		}
		return new Chromosome(path, false);
	}
	
	public void initPopulation() {
		Chromosome c ;
		while(population.size() < pop_size ) {
			c = generateChromosome() ;
			population.add(c) ;
		}
		pop_fitness = populationFitness() ;
	}
	
	public void runEvolution(int nb_generations) {
		Chromosome[] parents , children;
		SortedSet<Chromosome> next_gen ;
		int j ;
		for (int i = 0 ; i < nb_generations ; i++) {
			next_gen = new TreeSet<Chromosome>() ;
			
			if(population.first().getFitness() == 3) {
				System.out.println( population.first().getFitness() + " " +  population.first() + " " + i);
				return ;
			}
			
			j = 0 ;
			
			//keep 20% of current generation and pass them to the next generation as elites.
			for(Chromosome c : population) {
				Chromosome c2 = new Chromosome(c) ;
				c2.grow(mut_rate); 
				if(c2.LENGTH >= 32) c2.truncate(); 
				next_gen.add(c2);
				j++ ;
				if(j>=population.size() * 0.2) break ;
			}

			while(j < pop_size ) { 
				parents = selectPair();
				children = crossOver(parents[0], parents[1]) ;
				children[0].grow(mut_rate);
				children[1].grow(mut_rate);
				if(children[0].LENGTH >= 32) children[0].truncate();
				if(children[1].LENGTH >= 32) children[1].truncate();
				next_gen.add(children[0]); 
				next_gen.add(children[1]);
				
				j = j + 2 ;
			}
			population = next_gen ;
			pop_fitness = populationFitness() ;
			System.out.println("GEN "+ i + "of size " + population.size() + " " + population.first()  + " " + population.first().getFitness());
		}
	}
	
	public float populationFitness() {
		float sum = 0;
		for(Chromosome c : population) {
			sum = sum + c.getFitness() ;
		}
		return sum ;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chromosome.INITIAL_STATE = new Puzzle(915327468) ;
		SolverGA s = new SolverGA() ;
		s.pop_size = 200 ;
		s.cross_rate = 0.6 ;
		s.mut_rate = 0.8 ;
		s.initPopulation() ;
		s.runEvolution(15000);
	} 
		
}
