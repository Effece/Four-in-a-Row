import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Frame extends JFrame {
	
	final JPanel canPanel, infosPanel;
	final Undo undo;
	final Game game;
	final int width, height, rap;
	Color background, oppositeBackground;
	
	private static final long serialVersionUID = 4L;

	public Frame(Game game, int rap) {
		
		this.game = game;
		
		this.rap = rap;
		this.background = Color.black;
		this.oppositeBackground = Color.white; //Color.getHSBColor(255, 204, 51); //Color.cyan; // 255 215 0 (gold)
	    
	    this.setBackground(this.background);
	    this.setFocusable(true);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setVisible(true);
	    
	    this.canPanel = new JPanel();
	    this.add(this.canPanel, BorderLayout.CENTER);
	    this.canPanel.setBackground(this.background);
	    this.canPanel.setVisible(true);
	    
	    this.infosPanel = new JPanel();
	    this.add(this.infosPanel, BorderLayout.NORTH);
	    this.infosPanel.setBackground(this.background);
	    this.infosPanel.setVisible(true);
	    
	    this.undo = new Undo(this, "Cancel");
	    
	    this.width = game.gate.length * this.rap + this.rap;
		this.height = game.gate[0].length * this.rap + this.undo.height + 90;
		
	    this.setSize(this.width, this.height);
	    this.setPreferredSize(new Dimension(this.width, this.height));
	    this.setMinimumSize(new Dimension(this.width, this.height));
	    this.setMaximumSize(new Dimension(this.width, this.height));
	    
	    this.pack();
	    
	}
	
}
