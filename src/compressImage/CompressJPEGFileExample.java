package compressImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CompressJPEGFileExample {

	
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(193, 167, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 193, 167, null);
		g.dispose();
			
		return resizedImage;
	    }

}
