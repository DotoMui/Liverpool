package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像相乘sqrt(b1*b1+b2*b2)
public class ch6_5 {
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage b1 = ImageIO.read(new File("input/3.jpg"));
		BufferedImage b2 = ImageIO.read(new File("input/4.jpg"));
		BufferedImage nbi = imgMul(b1,b2);
		ImageIO.write(nbi, "jpg", new File("output/ch6_5.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage imgMul(BufferedImage b1,BufferedImage b2){
		int w = Math.min(b1.getWidth(),b2.getWidth());
		int h = Math.min(b1.getHeight(), b2.getHeight());
		
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				Color c1 = new Color(b1.getRGB(x, y));
				Color c2 = new Color(b2.getRGB(x, y));
				int r = imgMul(c1.getRed(),c2.getRed());
				int g = imgMul(c1.getGreen(),c2.getGreen());
				int b = imgMul(c1.getBlue(),c2.getBlue());
				Color nc =new Color(r,g,b);
				nbi.setRGB(x, y, nc.getRGB());
			}
		}
		return nbi;
	}
	
	private static int imgMul(int pix1,int pix2){
		int rs = (int) Math.sqrt(pix1*pix1+pix2*pix2);
		if(rs>255){
			rs =255;
		}
		return rs;
	}
}
