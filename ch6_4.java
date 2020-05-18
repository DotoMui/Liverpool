package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像融合
public class ch6_4 {
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage b1 = ImageIO.read(new File("input/1.jpg"));
		BufferedImage b2 = ImageIO.read(new File("input/2.jpg"));
		BufferedImage nbi = imgFuse(b1,b2,0.3);
		ImageIO.write(nbi, "jpg", new File("output/ch6_4.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage imgFuse(BufferedImage b1,BufferedImage b2,double k){
		int w = Math.min(b1.getWidth(),b2.getWidth());
		int h = Math.min(b1.getHeight(), b2.getHeight());
		
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				Color c1 = new Color(b1.getRGB(x, y));
				Color c2 = new Color(b2.getRGB(x, y));
				int r = imgFuse(c1.getRed(),c2.getRed(),k);
				int g = imgFuse(c1.getGreen(),c2.getGreen(),k);
				int b = imgFuse(c1.getBlue(),c2.getBlue(),k);
				Color nc =new Color(r,g,b);
				nbi.setRGB(x, y, nc.getRGB());
			}
		}
		return nbi;
	}
	
	private static int imgFuse(int pix1,int pix2,double k){
		return (int)(k*pix1+(1-k)*pix2);
	}
}
