import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;

public class InfosCan extends JPanel {
	
	final int rap, dis, fontSize, width, height, colsNbHeight;
	Color background, oppositeBackground;
	Graphics2D graphics;
	final Frame frame;
	final Canvas can;
	final Game game;
	
	private static final long serialVersionUID = 4L;

	public InfosCan(Frame frame, Canvas can, Game game, int rap) {
		
		this.frame = frame;
		this.frame.infosPanel.add(this);
		this.can = can;
		
		this.rap = rap;
		this.dis = this.can.dis;
		this.fontSize = 30;
		
		this.game = game;
		
		this.width = this.can.width;
		this.height = 30;
		this.colsNbHeight = this.height;
		
		this.background = this.can.background;
		this.oppositeBackground = this.can.oppositeBackground;
		
		this.setSize(new Dimension(this.width, this.height));
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setMinimumSize(new Dimension(this.width, this.height));
		this.setMaximumSize(new Dimension(this.width, this.height));
		
		this.setVisible(true);
		
		repaint();
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		this.graphics = (Graphics2D) g;
		
		this.graphics.setBackground(this.background);
		
		this.graphics.setFont(new Font("TimesRoman", Font.PLAIN, this.fontSize));
		this.graphics.setColor(this.oppositeBackground);
		
		for (int i = 1; i < this.game.gate.length + 1; i++) {
			this.graphics.drawString(String.valueOf(i), i * this.rap - this.rap / 2 - this.fontSize / 4, colsNbHeight);
			
		}
		
		//this.graphics.drawString("Current player:", 10, this.height - 10);
		
	}

}
