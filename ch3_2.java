package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
//数字图像的开窗变换
public class ch3_2 {
	public static void main(String[] args)throws Exception{
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = kaichuang(bi,127,100);
		ImageIO.write(nbi, "jpg", new File("output/ch3_2.jpg"));
		System.err.println("程序结束！！！");
	}
	public static BufferedImage kaichuang(BufferedImage bi,int cw,int ww){
		int w = bi.getWidth();
		int h = bi.getHeight();
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				int pixel = bi.getRGB(x, y);
				Color c = new Color(pixel);
				int r = kaichuang(c.getRed(),cw,ww);
				int g = kaichuang(c.getGreen(),cw,ww);
				int b = kaichuang(c.getBlue(),cw,ww);
				Color nc = new Color(r,g,b);
				int rgb = nc.getRGB();
				nbi.setRGB(x, y, rgb);
			}
		}
		return nbi;
	}
	private static int kaichuang(int pix, int cw, int ww) {
		// TODO Auto-generated method stub
		int y =(int)(255*(pix-cw+ww/2)/ww);
		if(pix<=cw-ww/2) y=0;
		if(pix>=cw+ww/2) y=255;
		return y;
	}
}
