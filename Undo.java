import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

public class Undo extends JButton {

	final Frame frame;
	final String text;
	final int width, height;
	
	private static final long serialVersionUID = 4L;
	
	public Undo(Frame frame, String text) {
		
		this.frame = frame;
		this.text = text;
		this.width = this.frame.width;
		this.height = 35;
		
		this.setText(this.text);
		this.setForeground(this.frame.oppositeBackground);
		this.setBackground(this.frame.background);
		this.setBorderPainted(false);
		
		this.setVisible(true);
		
		this.frame.add(this, BorderLayout.SOUTH);
	    
	    this.setSize(this.width, this.height);
	    this.setPreferredSize(new Dimension(this.width, this.height));
	    this.setMinimumSize(new Dimension(this.width, this.height));
	    this.setMaximumSize(new Dimension(this.width, this.height));
		
	}

}
