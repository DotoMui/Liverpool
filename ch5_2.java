package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//图像双线性插值放大 
public class ch5_2 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = zoomSxx(bi,2);
		ImageIO.write(nbi, "jpg", new File("output/ch5_2.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage zoomSxx(BufferedImage bi,double k){
		int w = (int)(k*bi.getWidth());
		int h = (int)(k*bi.getHeight());
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h;y1++){
			for(int x1=0;x1<w;x1++){
				int rgb = zoomSxx(bi,k,x1,y1);
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
	private static int zoomSxx(BufferedImage bi,double k ,int x1,int y1){
		double xx = x1/k;
		double yy = y1/k;
		int x0 = (int)xx;
		int y0 = (int)yy;
		int rgb = 0;
		if((x0+1)<bi.getWidth()&&(y0+1)<bi.getHeight()){
			Color a = new Color(bi.getRGB(x0, y0));
			Color b = new Color(bi.getRGB(x0+1,y0));
			Color c = new Color(bi.getRGB(x0, y0+1));
			Color d = new Color(bi.getRGB(x0+1,y0+1));
			int r0 = zoomSxx(a.getRed(),b.getRed(),c.getRed(),d.getRed(),xx-x0,yy-y0);
			int g0 = zoomSxx(a.getGreen(),b.getGreen(),c.getGreen(),d.getGreen(),xx-x0,yy-y0);
			int b0 = zoomSxx(a.getBlue(),b.getBlue(),c.getBlue(),d.getBlue(),xx-x0,yy-y0);
			
			Color nc = new Color(r0,g0,b0);
			rgb = nc.getRGB();
		}
		return rgb;
	}
	private static int zoomSxx(int a,int b ,int c,int d,double xx,double yy){
		double y = a*(1-xx)*(1-yy) + b*xx*(1-yy) + c*(1-xx)*yy + d*xx*yy;
		return (int)y;
		}
}