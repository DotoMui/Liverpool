package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//图像均值滤波
public class ch7_1 {
	public static void main(String[] args)throws Exception{
		long t1 = System.currentTimeMillis();
		BufferedImage bi = ImageIO.read(new File("input/2噪声点.jpg"));
		BufferedImage nbi = smoothJz(bi);
		ImageIO.write(nbi, "jpg", new File("output/ch7_1.jpg"));
		long t2 = System.currentTimeMillis();
		System.err.println("程序运行"+(t2-t1)+"毫秒");
	}
	
	public static BufferedImage smoothJz(BufferedImage bi){
		int w = bi.getWidth();
		int h = bi.getHeight();
		BufferedImage nbi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		for(int y=1;y<(h-1);y++){
			for(int x=1;x<(w-1);x++){
				int r = smoothJz(getSz(bi,x,y,1));
				int g = smoothJz(getSz(bi,x,y,2));
				int b = smoothJz(getSz(bi,x,y,3));
				int rgb = (new Color(r,g,b).getRGB());
				nbi.setRGB(x, y, rgb);
			}
		}
		return nbi;
	}
	
	private static int smoothJz(int[] sz){
		int count = 0;
		int sum = 0;
		int pix = 0;
		
		for(int i=0;i<sz.length;i++){
			count++;
			sum+=sz[i];
		}
		if(count>0){
			pix = sum/count;
		}
		return pix;
	}
	
	private static int[] getSz(BufferedImage bi,int x,int y,int key){
		int[] sz = new int[9];
		int n=0;
		if(x>0 && x<(bi.getWidth()-1) && y>0 && y<(bi.getHeight()-1)){
			for(int j=(y-1);j<=(y+1);j++){
				for(int i=(x-1);i<=(x+1);i++){
					Color c =new Color(bi.getRGB(i, j));
					switch(key){
					case 0:
						sz[n] = (int)(0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
						break;
					case 1:
						sz[n] = c.getRed();
						break;
					case 2:
						sz[n] = c.getGreen();
						break;
					case 3:
						sz[n] = c.getBlue();
						break;
					default:
						break;
							
					}
					n++;
				}
			}
		}
		return sz;
	}
}
