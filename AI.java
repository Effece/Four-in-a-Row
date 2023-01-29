import java.util.ArrayList;
import java.util.Random;

public class AI {
	
	final Analyzer analyzer;
	final int nbPlayer, num;
	
	ArrayList<int[]> paths;
	final Random random;

	public AI(int nb, int nbPlayer, int num) {
		/*
		 * nb : longueur nécessaire pour gagner
		 * nbPlayer : nombre de joueurs
		 * num : numéro de l'ordinateur
		*/
		
		this.analyzer = new Analyzer(nb);
		this.nbPlayer = nbPlayer;
		this.num = num;
		
		this.paths = new ArrayList<int[]>();
		this.random = new Random();
		
	}
	
	public int findPlay(int[][] gate, int player) {
		
		return this.random.nextInt(gate.length);
		
	}

}