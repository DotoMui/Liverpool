package img;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 颜色相似性分割图像
 * 根据给出的颜色值和阈值，将图像中相似的像素设置为白色，否则为黑色
 * 像素红绿蓝三个通道的颜色值分别减去给定颜色值对应通道的值，其绝对值均小于等于阈值为白色，否则为黑色
 * */
public class ch12_1 {

	public static void main(String[] args) throws Exception {
		long t1=System.currentTimeMillis();		//获取程序运行前的时间，单位毫秒
		BufferedImage bi=ImageIO.read(new File("input/2.jpg"));		//读取图像
                //给定颜色值RGB均为99，阈值为32
		BufferedImage nbi=quyu(bi,new Color(99,99,99),32);						//处理图像
		ImageIO.write(nbi, "jpg", new File("output/ch12_1.jpg"));		//输出图像
		long t2=System.currentTimeMillis();				//获取程序运行后的时间
		System.out.println("程序运行"+(t2-t1)+"毫秒");	//程序结束后进行提示
	}

	/**
	 * 图像根据给定的颜色值和阈值，分割图像
	 * 遍历图像中的每一个像素点，像素红绿蓝三个通道的颜色值分别减去给定颜色值对应通道的值，其绝对值均小于阈值为白色，否则为黑色
	 * @param BufferedImage bi 	原始图像
	 * @param Color cc 			给定的颜色值
	 * @param int yz 			阈值
	 * @return BufferedImage	变换后图像
	 * */
	private static BufferedImage quyu(BufferedImage bi, Color cc, int yz) {
		int w=bi.getWidth();		//得到图像的宽度
		int h=bi.getHeight();		//得到图像的高度
		BufferedImage nbi=new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);	
		//循环遍历每一个像素点
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				 int pixel=bi.getRGB(x, y);		//得到坐标为（x,y）处的像素值
				                Color c=new Color(pixel);		//转换为Color类，便于处理
				                int r=c.getRed() - 99;			
				                int g=c.getGreen() - 99;		
				                int b=c.getBlue() - 99;	
				                if(Math.abs(r) <= 32&&Math.abs(g) <= 32&&Math.abs(b) <= 32 ){
				                        r = 255;
				                        g = 255;
				                        b = 255;
				                }else{
				                       r = 0;
				                       g = 0;
				                       b = 0;
				                }	
				                Color nc=new Color(r,g,b);		
				                int rgb=nc.getRGB();			
				                nbi.setRGB(x, y, rgb);

			}
		}		
		return nbi;
	}

}


