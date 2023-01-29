
public class IA {
	
	final Analyzer analyzer;
	final int nbPlayer, playerNb;
	
	public IA(int nb, int nbPlayer, int playerNb) {
		
		this.analyzer = new Analyzer(nb);
		this.nbPlayer = nbPlayer; // nombre de joueurs
		this.playerNb = playerNb; // numéro de joueur de l'ordinateur
		
	}
	
	public int findPlay(int[][] gateArg, int player) {
		
		StatsIA[] stats = new StatsIA[gateArg.length];
		
		int[][] gate = new int[gateArg.length][gateArg[0].length];
		for (int i = 0; i < gateArg.length; i++) {
			System.out.println(i);
			gate = token.add(gateArg, i, player);
			stats[i] = analyse(gate, player, 0);
		}
		
		int play;
		
		// un élément FALSE signifie que la colonne ne répond pas au critère
		boolean[] isWorthWin0, isWorthLoose, isWorthLoose0, isWorthDrawW, isWorthDrawL, isWorthShortWin, isWorthShortWin0, possibilities;
		isWorthWin0 = new boolean[stats.length];
		isWorthLoose = new boolean[stats.length];
		isWorthLoose0 = new boolean[stats.length];
		isWorthDrawW = new boolean[stats.length];
		isWorthDrawL = new boolean[stats.length];
		isWorthShortWin = new boolean[stats.length];
		isWorthShortWin0 = new boolean[stats.length];
		
		possibilities = findCols(gateArg);
		
		for (int k = 0; k < stats.length; k++) {
			isWorthWin0[k] = stats[k].winning != 0; // il n'y a aucune chance de gagner
			isWorthLoose[k] = stats[k].loosing < stats[k].winning; // il y a plus de chances de gagner que de perdre
			isWorthLoose0[k] = stats[k].loosing == 0; // il n'y a aucune chance de perdre
			isWorthDrawW[k] = stats[k].draw < stats[k].winning; // il y a plus de chances de gagner que de finir en nulle
			isWorthDrawL[k] = stats[k].draw > stats[k].loosing; // il y a plus de chances de finir en nulle que de perdre
			isWorthShortWin[k] = stats[k].shortestWin < stats[k].shortestLoose; // l'ordinateur gagne plus vite que le joueur
			isWorthShortWin0[k] = stats[k].shortestWin < 3; // l'ordinateur peut gagner en 3 coups maximum
		}
		
		int[] isWorth;
		int cond;
		isWorth = new int[stats.length];
		
		for (int i = 0; i < stats.length; i++) {
			
			cond = possibilities[i]? 200:-200;
			cond += isWorthWin0[i]? 10:0;
			cond += isWorthLoose[i]? 10:0;
			cond += isWorthLoose0[i]? 40:0;
			cond += isWorthDrawW[i]? 5:0;
			cond += isWorthDrawL[i]? 5:0;
			cond += isWorthShortWin[i]? 80:0;
			cond += isWorthShortWin0[i]? 60:0;
			
			isWorth[i] = cond;
			
		}
		
		play = Main.maxIndex(isWorth);
		
		return play;
		
	}
	
	private StatsIA analyse(int[][] gateArg, int player, int size) {
		
		StatsIA[] stats = new StatsIA[gateArg.length];
		for (int k = 0; k < stats.length; k++) {
			stats[k] = new StatsIA();
			stats[k].size = size + 1;
		}
		
		int[][] gate = new int[gateArg.length][gateArg[0].length];
		int p;
		
		// navigation dans chaque colonne
		for (int i = 0; i < gateArg.length; i++) {
			
			// génération de la nouvelle grille
			gate = token.add(gateArg, i, player);
			
			if (token.possible(gateArg, i)) {
				
				// si une victoire est possible
				if (this.analyzer.use(gate, player)) {
					
					if (player == this.playerNb) {
						// cas où l'ordinateur gagnerait
						stats[i].winning++;
						stats[i].shortestWin = size + 1;
					} else {
						// cas où un autre joueur gagnerait
						stats[i].loosing++;
						stats[i].shortestLoose = size + 1;
					}
				
				// si la partie est nulle
				} else if (gates.isFull(gate)) {
					stats[i].draw++;
				
				// si le jeu continue (sinon)
				} else {
					
					// joueur suivant
					p = player + 1;
					if (p > this.nbPlayer) {
						p = 1;
					}
					
					// analyse récursive
					stats[i] = analyse(gate, p, size + 1);
					
				}
				
			} else if (gates.isFull(gate)) {
				stats[i].draw++;
				break;
			}
		}
		
		StatsIA ultimateStats = new StatsIA();
		
		for (StatsIA k: stats) {
			ultimateStats.winning += k.winning;
			ultimateStats.loosing += k.loosing;
			ultimateStats.draw += k.draw;
			ultimateStats.size = size + 1;
			if (ultimateStats.shortestWin == -1 || k.shortestWin < ultimateStats.shortestWin) ultimateStats.shortestWin = k.shortestWin;
			if (ultimateStats.shortestLoose == -1 || k.shortestLoose < ultimateStats.shortestLoose) ultimateStats.shortestLoose = k.shortestLoose;
		}
		
		return ultimateStats;
		
	}

	private boolean[] findCols(int[][] gateArg) {
		// renvoie quelles colonnes peuvent être jouées
		
		boolean[] pos = new boolean[gateArg.length];
		
		for (int i = 0; i < gateArg.length; i++) {
			if (token.possible(gateArg, i)) {
				pos[i] = true;
			}
		}
		
		return pos;
		
	}

}
