package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ch1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedImage bi=ImageIO.read(new File("input/1.jpg"));		//读取图像
		BufferedImage nbi=fanxiang(bi);								//处理图像
		ImageIO.write(nbi, "jpg", new File("output/1b.jpg"));		//输出图像
	}

	private static BufferedImage fanxiang(BufferedImage bi) {
		int w=bi.getWidth();		//得到图像的宽度
		int h=bi.getHeight();		//得到图像的高度
		BufferedImage nbi=new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);	//创建新图像（临时图像变量）
		//循环遍历每一个像素点
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				//本次实验的重点内容
				int pixel=bi.getRGB(x, y);		//得到坐标为（x,y）处的像素值
				Color c=new Color(pixel);		//转换为Color类，便于处理
				int r=255 - c.getRed();			//红色通道取反
				int g=255 - c.getGreen();		//绿色通道取反
				int b=255 - c.getBlue();		//蓝色通道取反
				Color nc=new Color(r,g,b);		//根据红绿蓝三个通道，合成新颜色
				int rgb=nc.getRGB();			//转换为int类型
				nbi.setRGB(x, y, rgb);			//设置输出图像坐标为（x,y）的像素值
			}
		}		
		return nbi;
	}
}
