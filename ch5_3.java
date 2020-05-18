package img;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//图像水平镜像
public class ch5_3 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = mirrorHor(bi);
		ImageIO.write(nbi, "jpg", new File("output/ch5_3.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage mirrorHor(BufferedImage bi){
		int w = bi.getWidth();
		int h = bi.getHeight();
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h;y1++){
			for(int x1=0;x1<w;x1++){
				int x0 = (w-1)-x1;
				int y0 = y1;
				int rgb = bi.getRGB(x0, y0);
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
}