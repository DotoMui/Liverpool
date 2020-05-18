package img;

import  java.awt.Color;
import  java.awt.image.BufferedImage;
import  java.io.File;
import  java.io.IOException;

import  javax.imageio.ImageIO;

/**
 * p分位数法分割图像
  *  图像分割，根据先验概率
  *  */
public  class  ch10_2  {

        public  static  void  main(String[]  args)  throws  Exception  {
                long  t1=System.currentTimeMillis();                //获取程序运行前的时间，单位毫秒
                BufferedImage  bi=ImageIO.read(new  File("input/2.jpg"));                //读取图像1
                BufferedImage  nbi=ptile(bi,127);                                                //处理图像
                ImageIO.write(nbi,  "jpg",  new  File("output/ch10_2.jpg"));                //输出图像
                long  t2=System.currentTimeMillis();                                //获取程序运行后的时间
                System.out.println("程序运行"+(t2-t1)+"毫秒");        //程序结束后进行提示

        }

        /**
          *  根据阈值分割图像
          *  @param  BufferedImage  bi          原图像
          *  @param  int  yz                        分割阈值，根据灰度值进行判断
          *  @return  BufferedImage        分割后的图像
          *  */
        public  static  BufferedImage  ptile(BufferedImage  bi,int  yz)  {
                int  w=bi.getWidth();                //得到图像的宽度
                int  h=bi.getHeight();                //得到图像的高度
                BufferedImage  nbi=new  BufferedImage(w,  h,  BufferedImage.TYPE_3BYTE_BGR);        
                //创建新图像（临时图像变量）宽度和高度跟原图相同
                //循环遍历每一个像素点
                for(int  y=0;y<h;y++)  {
                        for(int  x=0;x<w;x++)  {
                        	int pixel = bi.getRGB(x, y);
                        	int rgb = getGray(pixel);
                        	if(rgb>=yz){
                        		rgb = 255*256*256+255*256+255;
                        	}else{
                        		rgb = 0;
                        	}
                        	nbi.setRGB(x,  y,  rgb);
                        }
                }                
                return  nbi;
        }

        /**
          *  计算像素值对应的灰度值
          *  @param  int  pixel                  RGB像素值
          *  @return  int                                灰度值0-255
          *  */
        private  static  int  getGray(int  pixel)  {
                Color  c=new  Color(pixel);
                int  gray=(int)  (0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
                return  gray;
        }

}
