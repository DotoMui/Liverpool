package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
// 图像局部均值缩小 
public class ch5_1 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/1.jpg"));
		BufferedImage nbi = zoomJbjz(bi,0.4);
		ImageIO.write(nbi, "jpg", new File("output/ch5_1.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage zoomJbjz(BufferedImage bi,double k){
		int w = (int)(k*bi.getWidth());
		int h = (int)(k*bi.getHeight());
		BufferedImage nbi = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		for(int y1=0;y1<h;y1++){
			for(int x1=0;x1<w;x1++){
				int rgb = zoomJbjz(bi,k,x1,y1);
				nbi.setRGB(x1, y1, rgb);
			}
		}
		return nbi;
	}
	
	private static int zoomJbjz(BufferedImage bi,double k,int x1,int y1){
		int xx0 = (int)(x1/k);
		int yy0 = (int)(y1/k);
		int xx1 = (int)((x1+1)/k-1);
		int yy1 = (int)((y1+1)/k-1);
		int rgb = 0;
		if(xx1<bi.getWidth()&&yy1<bi.getHeight()){
			int n=0;
			int rsum = 0;
			int gsum = 0;
			int bsum = 0;
			for(int j=yy0;j<=yy1;j++){
				for(int i=xx0;i<=xx1;i++){
					Color c = new Color(bi.getRGB(i, j));
					rsum += c.getRed();
					gsum += c.getGreen();
					bsum += c.getBlue();
					n++;
				}
			}
			if(n>0){
				Color nc = new Color((int)(rsum/n),(int)(gsum/n),(int)(bsum/n));
				rgb = nc.getRGB();
			}
		}
		return rgb;
	}
}
