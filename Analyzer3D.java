
public class Analyzer3D extends Analyzer {

	public Analyzer3D(int nb) {
		
		super(nb);
		
	}
	
	protected boolean depth(int[][][] gateArg, int player) {
		
		int[][] gate = new int[gateArg.length * (gateArg[0][0].length + 1)][gateArg[0].length];
		
		for (int i = 0; i < gateArg.length; i++) {
			for (int j = 0; j < gateArg[i].length; j++) {
				for (int k = 0; k < gateArg[i][j].length; k++) {
					
					gate[i * gateArg[i][j].length + k][j] = gateArg[i][j][k];
					
				}
			}
		}
		
		return col(gate, player);
		
	}

}
