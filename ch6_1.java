package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像相加 
public class ch6_1 {
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage b1 = ImageIO.read(new File("input/1.jpg"));
		BufferedImage b2 = ImageIO.read(new File("input/2.jpg"));
		BufferedImage nbi = imgAdd(b1,b2);
		ImageIO.write(nbi, "jpg", new File("output/ch6_1.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage imgAdd(BufferedImage b1,BufferedImage b2){
		int w = Math.min(b1.getWidth(),b2.getWidth());
		int h = Math.min(b1.getHeight(), b2.getHeight());
		
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				Color c1 = new Color(b1.getRGB(x, y));
				Color c2 = new Color(b2.getRGB(x, y));
				int r = imgAdd(c1.getRed(),c2.getRed());
				int g = imgAdd(c1.getGreen(),c2.getGreen());
				int b = imgAdd(c1.getBlue(),c2.getBlue());
				Color nc =new Color(r,g,b);
				nbi.setRGB(x, y, nc.getRGB());
			}
		}
		return nbi;
	}
	
	private static int imgAdd(int pix1,int pix2){
		return (pix1+pix2)/2;
	}
}
