import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TransitionTester {
	public static void main(String[] args) {
		final int FrameWidth = 400;
		final int FrameHeight = 500;
		final int LeftFrameXLeft = 300;
		final int RightFrameXLeft = LeftFrameXLeft + FrameWidth + 10;
		final int FrameYTop = 200;

		BufferedImage img = null;
		BufferedImage rightImage = null;

		//Create left frame with source image
		JFrame leftFrame = new JFrame("Source Frame");
		leftFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		leftFrame.setLocation(LeftFrameXLeft, FrameYTop);
		leftFrame.setSize(FrameWidth, FrameHeight);

		File imageFile = null;

		boolean imageLoaded=false;
		while (!imageLoaded) {
			// Load an image file
			JFileChooser chooser = new JFileChooser("Hello");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(leftFrame.getContentPane());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			}
			if (returnVal == JFileChooser.CANCEL_OPTION) {
				leftFrame.dispose();
				return;
			}			
			
			imageFile = chooser.getSelectedFile();

			try {
				img = ImageIO.read(imageFile);
			} catch (IOException e) {
				System.out.println("Error attempting to load image File: " + e);
				return;
			}
			// Reject image if it does not fit in frame
			imageLoaded = ((img.getWidth() <= FrameWidth) && (img.getHeight() <= FrameHeight));
			if (!imageLoaded) { System.out.println("Image too large"); }
		}
		LeftPanel myLeftPanel = new LeftPanel(img);
		leftFrame.getContentPane().add(myLeftPanel);

		try {
			rightImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("Error attempting to load image File: " + e);
			return;
		}

		RightPanel myRightPanel = new RightPanel(rightImage);
		myRightPanel.clearImage(myRightPanel.getGraphics());

		// Create right Frame
		JFrame rightFrame = new JFrame("Destination Frame");
		rightFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rightFrame.setLocation(RightFrameXLeft, FrameYTop);
		rightFrame.setSize(FrameWidth, FrameHeight);
		rightFrame.getContentPane().add(myRightPanel);

		leftFrame.setVisible(true);
		rightFrame.setVisible(true);

		final int NumTransitions = 2;	// Lab Q2: Modify This
		int nextTransition = 0;
		while (leftFrame.isVisible() || rightFrame.isVisible()) {
			myRightPanel.clearImage(myRightPanel.getGraphics());
			switch(nextTransition) {
				case 0:
					myRightPanel.transitionLRTB(myRightPanel.getGraphics(), img);
					break;
				case 1:
					myRightPanel.transitionDiagonal45LR(myRightPanel.getGraphics(), img);
					break;
				// Lab Q2: Your Code Here
			}
			nextTransition = (nextTransition + 1) % NumTransitions;
		}
	}
}
