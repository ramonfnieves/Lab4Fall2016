import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 3221057358581503074L;
	private BufferedImage img;

	public LeftPanel(BufferedImage img){
		this.img = img;
	}
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0 , null);		
	}
}
