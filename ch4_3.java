package img;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像直接缩小（放大） 
public class ch4_3 {
	public static int BGCOLOR=0;
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = suofang(bi,1.5);
		ImageIO.write(nbi, "jpg", new File("output/ch4_3.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	// k    缩放系数 
	public static BufferedImage suofang(BufferedImage bi,double k){
		int w1 = (int)(k*bi.getWidth());
		int h1 = (int)(k*bi.getHeight());
		BufferedImage nbi = new BufferedImage(w1,h1,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h1;y1++){
			for(int x1=0;x1<w1;x1++){
				int x0 = (int)(x1/k);
				int y0 = (int)(y1/k);
				int rgb=BGCOLOR;
				if(x0>=0&&x0<w1&&y0>=0&&y0<h1){
					rgb=bi.getRGB(x0, y0);
				}
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
}
