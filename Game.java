import java.util.ArrayList;

public class Game {
	
	int[][] gate;
	ArrayList<int[][]> history;
	
	final int rows, cols, nb, nbPlayers, maxHistory, nbAis;
	int historyUsed;
	
	int lastPlayRow, lastPlayCol;
	int play, player;
	
	final boolean memory, memoryColors;
	
	boolean end, pause;
	
	Canvas can; // le canvas est ajouté plus tard donc il n'est pas déclaré constant
	final Analyzer analyzer;
	final int[] aiNums;
	final AI[] ais;
	//final AI ai;

	public Game() {
		
		this.rows = 7;       // nombre de colonnes
		this.cols = 6;       // nombre de lignes
		this.nb = 4;         // taille minimale pour gagner
		this.nbPlayers = 3;  // nombre de joueurs
		this.maxHistory = 5; // longueur maximale d'historique
		this.nbAis = 2;      // nombre d'IAs
		
		this.lastPlayRow = -1;
		this.lastPlayCol = -1;
		
		this.gate = new int[rows][cols];
		this.history = new ArrayList<int[][]>();
		this.player = 1;
		this.historyUsed = 0;
		
		this.end = false;
		this.pause = false;
		this.memory = false;
		this.memoryColors = false;
		this.play = 0;
		
		this.analyzer = new Analyzer(nb);
		
		this.aiNums = new int[] {2, 3, 4, 5};
		this.ais = new AI[this.nbAis];
		
		for (int i = 0; i < this.ais.length; i++) this.ais[i] = new AI(nb, nbPlayers, this.aiNums[i]);
		// this.ai = new AI(nb, nbPlayers, 3);
		
	}
	
	public void addCanvas(Canvas can) {
		
		this.can = can;
		
	}
	
	public void verify(int play) {
		
		this.play = play;
		
		// emplacement correct
		if (this.play < this.gate.length) {
			
			// colonne non remplie
			if (token.possible(this.gate, this.play)) {
				
				actualizeHistoric(gate);
				
				this.gate = token.add(this.gate, this.play, this.player);
				if (this.memoryColors) gates.printNoColor(gate, this.nbPlayers);
				else if (!this.memory) gates.print(this.gate);
				
				this.lastPlayRow = play;
				this.lastPlayCol = token.posCol(this.gate, play);
				
				this.can.updateGate(this.gate);
				this.can.repaint();
				
				this.end = this.analyzer.use(this.gate, this.player);
				if (this.end) System.out.println(String.format("A player won! Congrats to player %o!", this.player));
				
				// mise à jour du joueur
				this.player++;
				if (this.player > this.nbPlayers) this.player = 1;
			
			} else System.out.println("This column is already full.");
		}
		
	}
	
	public void historic() {
		
		if (this.historyUsed < this.maxHistory && this.history.size() != 0) {
			
			this.historyUsed++;
		
			this.end = false;
			
			this.gate = this.history.remove(this.history.size() - 1);
			
			if (this.memoryColors) gates.printNoColor(this.gate, this.nbPlayers);
			else if (!this.memory) gates.print(this.gate);
			
			this.player--;
			if (this.player < 1) this.player = this.nbPlayers;
			
			this.lastPlayRow = -1;
			this.lastPlayCol = -1;
			
			this.can.updateGate(this.gate);
			this.can.repaint();
			
		} else System.out.println("Undo error. Nothing to undo or undo limit reached.");
	
	}
	
	private void actualizeHistoric(int[][] lastMove) {
		
		this.history.add(lastMove);
		if (this.historyUsed > 0) this.historyUsed--;
		
	}
	
	public void playAI() {
		
		if (!this.end) {
		
			this.pause = true;
			
			AI ai = this.ais[0];
			for (int i = 0; i < this.ais.length; i++) if (this.ais[i].num == this.player) {
				ai = this.ais[i];
				break;
			};
			
			int play = ai.findPlay(this.gate, this.player);
			System.out.println(play);
			this.verify(play);
			
			for (int i = 0; i < this.ais.length; i++) if (this.ais[i].num == this.player) {
				this.playAI();
				break;
			};
			
			this.pause = false;
		
		}
		
	}
	
}
