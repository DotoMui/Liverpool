package img;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像平移
public class ch4_1 {
	public static int BGCOLOR=127;
	
	public static void main(String[] args) throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = pingyi(bi,50,50);
		ImageIO.write(nbi, "jpg", new File("output/ch4_1.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage pingyi(BufferedImage bi,int tx,int ty){
		int w = bi.getWidth();
		int h = bi.getHeight();
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h;y1++){
			for(int x1=0;x1<w;x1++){
				int x0=x1-tx;
				int y0=y1-ty;
				int rgb = BGCOLOR;
				if(x0>0&&x0<w&&y0>=0&&y0<h){
					rgb=bi.getRGB(x0, y0);
				}
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
}
