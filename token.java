
public class token {

	public static int[][] add(int[][] gateArg, int row, int player) {
		// renvoie la grille après l'ajout d'un jeton
		
		if (!possible(gateArg, row)) {
			int[][] gate = new int[gateArg.length][gateArg[0].length];
			for (int[] k: gate) {
				for (int i = 0; i < k.length; i++) {
			        k[i] = 1;
			    }
			}
			return gate;
		}
		
		int[][] gate = new int[gateArg.length][gateArg[0].length];
		for (int i = 0; i < gateArg.length; i++) {
			for (int j = 0; j < gateArg[i].length; j++) {
				gate[i][j] = gateArg[i][j];
			}
		}
		
		final int col = posCol(gate, row);
		gate[row][col] = player;
		
		return gate;
		
	}
	
	public static boolean possible(int[][] gate, int row) {
		// renvoie si un jeton peut être ajouté à une colonne
		
		return gate[row][gate[row].length - 1] == 0;
		
	}
	
	public static int posCol(int[][] gate, int row) {
		// renvoie, pour une colonne donnée, la ligne à laquelle serait joué un jeton
		
		for (int k = 0; k < gate[row].length; k++) {
			if (gate[row][k] == 0) {
				return k;
			}
		}
		
		return 0;
		
	}
	
}
