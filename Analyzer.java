
public class Analyzer {
	
	int size, nb, givenNb;
	int[][] winningTokens;
	public boolean useCol, useRow, useDiag, useSquare;

	public Analyzer(int nb) {
		
		this.size = 0;
		this.nb = nb;
		this.givenNb = nb;
		
		this.winningTokens = new int[nb][2]; // 2 est le nombre de dimensions
		
		this.useCol = true;
		this.useRow = true;
		this.useDiag = true;
		this.useSquare = false;
		
	}
	
	public boolean use(int[][] gateArg, int player) {
		// renvoie l'intégralité des tests (colonne, ligne, diagonales)
		
		if (col(gateArg, player) && useCol) return true;
		if (row(gateArg, player) && useRow) return true;
		if (diag(gateArg, player) && useDiag) return true;
		if (square(gateArg, player) && useSquare) return true;
		
		return false;
		
		// return changé pour éviter d'éxecuter inutilement des tests lorsque d'autres sont déjà validés
		// return (col(gateArg, player) && useCol) || (row(gateArg, player) && useRow) || (diag(gateArg, player) && useDiag) || (square(gateArg, player) && useSquare);
		
	}
	
	protected boolean col(int[][] gateArg, int player) {
		// renvoie si une colonne contient un allignement
		
		boolean cond = false;
		int v;
		
		for (int i = 0; i < gateArg.length; i++) {
			
			v = 0;
			
			// pour chaque colonne, on compte le nombre de jetons identiques consécutifs
			for (int j = 0; j < gateArg[i].length; j++) {
				
				if (gateArg[i][j] == player) {
					
					if (!cond) {
						this.winningTokens[v][0] = i;
						this.winningTokens[v][1] = j;
					}
					
					v++;
					
					if (v == this.nb) {
						
						cond = true;
						
						if (v > this.size) this.size = v;
						
					}
					
				} else v = 0;
				
			}
			
		}
		
		return cond;
		
	}
	
	protected boolean row(int[][] gateArg, int player) {
		// renvoie si une ligne contient un allignement
		
		// retournement de la grille pour effectuer, avec, une analyse de colonne
		int[][] gate = gates.inverse(gateArg);
		
		boolean cond = col(gate, player);
		
		int r, c;
		for (int i = 0; i < this.winningTokens.length; i++) {
			r = this.winningTokens[i][0];
			c = this.winningTokens[i][1];
			this.winningTokens[i][0] = c;
			this.winningTokens[i][1] = r;
		}
		
		return cond;
		
	}
	
	protected boolean diag(int[][] gateArg, int player) {
		// renvoie si les diagonales contiennent un allignement
		
		// décalle chaque colonne en fonction de son index, pour les diagonales deviennent des lignes

		// création de grilles surdimensionnées pour pouvoir aisément décaller
		int[][] gateUp, gateDo;
		gateUp = new int[gateArg.length][gateArg[0].length + gateArg.length];
		gateDo = new int[gateArg.length][gateArg[0].length + gateArg.length];
		
		for (int i = 0; i < gateArg.length; i++) {
			for (int j = 0; j < gateArg[i].length; j++) {
				
				// décallage
				gateUp[i][j + i] = gateArg[i][j];
				gateDo[i][gateDo[i].length - gateArg[i].length + j - i] = gateArg[i][j];
			
			}
		}
		
		// analyse en ligne des deux diagonales
		boolean cond1, cond2;
		cond1 = row(gateUp, player);
		cond2 = row(gateDo, player);
		
		// replacement des jetons de victoire
		
		//int c;
		
		if (cond1 || cond2) {
			for (int i = 0; i < this.winningTokens.length; i++) {
				
				//c = this.winningTokens[i][1];
				
				//if (cond1) System.out.println(c);
				//else if (cond2) System.out.println(c);
				
				//this.winningTokens[i][1] = c;
				
			}
		}
		
		return cond1 || cond2;
		
	}
	
	protected boolean square(int[][] gateArg, int player) {
		
		int[][] gate = new int[gateArg.length][gateArg[0].length * 5];
		
		for (int i = 0; i < gateArg.length - 1; i++) {
			for (int j = 0; j < gateArg[i].length - 1; j++) {
				
				gate[i][j * 5] = gateArg[i][j];
				gate[i][j * 5 + 1] = gateArg[i][j + 1];
				gate[i][j * 5 + 2] = gateArg[i + 1][j];
				gate[i][j * 5 + 3] = gateArg[i + 1][j + 1];
				
			}
		}
		
		return col(gate, player);
		
	}
	
	public boolean use(int[][] gateArg, int player, int nb) {
		
		this.nb = nb;
		boolean cond = use(gateArg, player);
		this.nb = this.givenNb;
		
		return cond;
		
	}
	
	protected boolean col(int[][] gateArg, int player, int nb) {
		
		this.nb = nb;
		boolean cond = col(gateArg, player);
		this.nb = this.givenNb;
		
		return cond;
		
	}
	
	protected boolean row(int[][] gateArg, int player, int nb) {
		
		this.nb = nb;
		boolean cond = row(gateArg, player);
		this.nb = this.givenNb;
		
		return cond;
		
	}
	
	protected boolean diag(int[][] gateArg, int player, int nb) {
		
		this.nb = nb;
		boolean cond = diag(gateArg, player);
		this.nb = this.givenNb;
		
		return cond;
		
	}
	
	protected boolean square(int[][] gateArg, int player, int nb) {
		
		this.nb = nb;
		boolean cond = square(gateArg, player);
		this.nb = this.givenNb;
		
		return cond;
		
	}
	
}
