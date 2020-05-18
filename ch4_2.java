package img;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

////图像旋转
public class ch4_2 {
	public static int BGCOLOR=0;
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/2.jpg"));
		BufferedImage nbi = xuanzhuan(bi,60);
		ImageIO.write(nbi, "jpg", new File("output/ch4_2.2.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	public static BufferedImage xuanzhuan(BufferedImage bi,double angle){
		int w = bi.getWidth();
		int h = bi.getHeight();
		
		angle = Math.PI*angle/180;
		double sina = Math.sin(angle);
		double cosa = Math.cos(angle);
		
		int cx = w/2;
		int cy = h/2;
		 
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h;y1++){
			for(int x1=0;x1<w;x1++){
				
				int x0=(int)((x1-cx)*cosa-(y1-cy)*sina+cx);
				int y0=(int)((y1-cy)*cosa+(x1-cx)*sina+cy); 
				
				int rgb=BGCOLOR;
				if(x0>=0&&x0<w&&y0>=0&&y0<h){
					rgb=bi.getRGB(x0, y0);
				}
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
}
