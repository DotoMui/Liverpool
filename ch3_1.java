package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
//数字图像的线性变换
public class ch3_1 {
	public static void main(String[] args) throws Exception{
		BufferedImage bi = ImageIO.read(new File("C:/Users/meika/Desktop/1.jpg"));
		BufferedImage nbi = xianxing(bi,1.5,-50);
		ImageIO.write(nbi, "jpg", new File("output/ch3_1.jpg"));
		System.err.println("程序结束！");
	}
	public static BufferedImage xianxing(BufferedImage bi,double aa,int bb){
		int w = bi.getWidth();
		int h = bi.getHeight();
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				int pixel = bi.getRGB(x, y);
				Color c = new Color(pixel);
				int r = xianxing(c.getRed(),aa,bb);
				int g = xianxing(c.getGreen(),aa,bb);
				int b = xianxing(c.getBlue(),aa,bb);
				Color nc = new Color(r,g,b);
				int rgb = nc.getRGB();
				nbi.setRGB(x, y, rgb);
			}
		}
		return nbi;
	}
	private static int xianxing(int pix,double aa,int bb){
		return Math.max(0,Math.min(255, (int)(aa*pix+bb)));
	}
}
