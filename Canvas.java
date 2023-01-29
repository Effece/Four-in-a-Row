import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseListener {
	
	final int rap, dis, width, height, outline;
	Color background, oppositeBackground;
	Graphics2D graphics;
	final Frame frame;
	int[][] gate;
	final Game game;
	
	private static final long serialVersionUID = 4L;
	
	public Canvas(Frame frame, Game game, int rap) {
		
		this.frame = frame;
		this.frame.canPanel.add(this);
		
		this.game = game;
		this.gate = this.game.gate;
		
		this.rap = rap;
		this.dis = 4;
		this.width = game.gate.length * this.rap;
		this.height = game.gate[0].length * this.rap;
		this.outline = 6;
		this.background = this.frame.background;
		this.oppositeBackground = this.frame.oppositeBackground;
		
		this.setSize(new Dimension(this.width, this.height));
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setMinimumSize(new Dimension(this.width, this.height));
		this.setMaximumSize(new Dimension(this.width, this.height));
		
		this.setVisible(true);
		
		this.addMouseListener(this);
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		this.graphics = (Graphics2D) g;
		
		this.graphics.setBackground(this.background);
		
		// retirer tout du canvas
		this.graphics.clearRect(0, 0, this.width, this.height);
		
		// mise en valeur du dernier jeton
		
		int r, c;
		r = this.game.lastPlayRow;
		c = this.game.lastPlayCol - 1;
		
		this.graphics.setColor(this.oppositeBackground);
		if (!this.game.end) addToken(this.graphics, r, c, true);
		
		else {
			
			int[][] wt = this.game.analyzer.winningTokens;
			
			for (int i[]: wt) {
				
				r = i[0];
				c = i[1];
				addToken(this.graphics, r, c, true);
				
			}
			
		}
		
		// parcours de chaque jeton pour en générer l'image
		int e;
		Color color;
		for (int i = 0; i < this.gate.length; i++) {
			for (int j = 0; j < this.gate[0].length; j++) {
				
				// à remplacer si possible par un dictionnaire
				
				e = this.gate[i][j];
				
				if (this.game.memory && (!this.game.end) && (!gates.isFull(this.gate))) color = this.background;
				else if (this.game.memoryColors && e != 0 && (!this.game.end) && (!gates.isFull(this.gate))) color = this.oppositeBackground;
				else switch (e) {
					case 0:
						continue;
					case 1:
						color = Color.blue;
						break;
					case 2:
						color = Color.red;
						break;
					case 3:
						color = Color.green;
						break;
					case 4:
						color = Color.yellow;
						break;
					case 5:
						color = Color.pink;
						break;
					case 6:
						color = Color.black;
						break;
					case 7:
						color = Color.orange;
						break;
					case 8:
						color = Color.cyan;
						break;
					default:
						color = this.background;
						break;
				}
				
				this.graphics.setColor(color);
				if (e != 0) this.addToken(this.graphics, i, j, false);
				
			}
		}
		
	 }
	
	public void updateGate(int[][] gateArg) {
		// actualise la grille sauvegardée du Canvas
		
		this.gate = gateArg;
		
	}
	
	private void addToken(Graphics g, int r, int c, boolean isShadow) {
		
		int ol = 0;
		if (isShadow) ol = this.outline;
		
		g.fillOval(r * this.rap + this.dis / 2 - ol / 2, (this.gate[0].length - c - 1) * this.rap + this.dis / 2 - ol / 2, this.rap - this.dis + ol, this.rap - this.dis + ol);
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		// si le jeu est en cours
		if ((!this.game.end) && (!this.game.pause)) {
		
			// récupération du clique et calcul de la colonne
			int x = e.getX() / this.rap;
		
			this.game.verify(x);
			
			//if (this.game.ais.num == this.game.player) this.game.playAI();
			
			for (int i = 0; i < this.game.ais.length; i++) if (this.game.ais[i].num == this.game.player) {
				this.game.playAI();
				break;
			};
		
		} else {
			System.out.println("A player already won!");
		}
		
	}
	
	// fonctions supplémentaires nécessaires pour le MouseListener mais inutiles
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
}
