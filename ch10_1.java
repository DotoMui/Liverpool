package img;
import  java.awt.Color;
import  java.awt.image.BufferedImage;
import  java.io.File;
import  java.io.IOException;

import  javax.imageio.ImageIO;

/**
  *  图像分割，迭代法
  *  */
public  class  ch10_1  {

        public  static  void  main(String[]  args)  throws  Exception  {
                long  t1=System.currentTimeMillis();                //获取程序运行前的时间，单位毫秒
                BufferedImage  bi=ImageIO.read(new  File("input/2.jpg"));                //读取图像
                double[]  zft=getZft(bi);
                int  nyz=diedai(zft);
                BufferedImage  nbi=ptile(bi,nyz);                                                //处理图像
                ImageIO.write(nbi,  "jpg",  new  File("output/cha10_1.jpg"));                //输出图像
                long  t2=System.currentTimeMillis();                                //获取程序运行后的时间
                System.out.println("程序运行"+(t2-t1)+"毫秒");        //程序结束后进行提示

        }

        /**
          *  根据图像灰度直方图，按照迭代法计算最终阈值
          *  @param  double[]  zft                  图像灰度直方图
          *  @return  int                                分割阈值
          *  */
        public  static  int  diedai(double[]  zft)  {
                int  setp=1;
                int  yz=127;
                int  nyz=0;
                while(setp<15)  {
                        nyz=getNewYz(zft,yz);
                        if(nyz!=yz)  {
                                yz=nyz;
                        }else  {
                                break;
                        }
                        System.out.println(setp+":"+nyz);
                        setp++;
                }
                
                return  nyz;
        }
        
        /**
          *  根据图像灰度直方图和初始阈值，按照迭代法计算新阈值
          *  @param  double[]  zft                  图像灰度直方图
          *  @param  int  yz                        初始阈值
          *  @return  int                                新阈值
          *  */
        public  static  int  getNewYz(double[]  zft,int  yz)  {
        	double sum1 =0,sum2 =0,w1=0,w2=0;
        	int avg1,avg2;
        	for(int i=0;i<yz;i++){
        		sum1+= zft[i]*i;
        		w1+=zft[i];
        	}
        	avg1 = (int)(sum1/w1);
        	for(int i=yz;i<256;i++){
        		sum2+= zft[i]*i;
        		w2+=zft[i];
        	}
        	avg2 = (int)(sum2/w2);
        	return (avg1+avg2)/2;
        }
        
        
        /**
          *  根据图像计算各灰阶的概率值
          *  @param  BufferedImage  bi          原图像
          *  @return  double[]                        0-255灰度值出现的概率
          *  */
        public  static  double[]  getZft(BufferedImage  bi)  {
                int  w=bi.getWidth();                //得到图像的宽度
                int  h=bi.getHeight();                //得到图像的高度
                int[]  zft=new  int[256];
                for(int  y=0;y<h;y++)  {
                        for(int  x=0;x<w;x++)  {
                                int  gray=getGray(bi.getRGB(x,  y));                                
                                zft[gray]++;                //灰度值为gray的像素个数加1
                        }
                }                
                double[]  zftd=new  double[256];
                for(int  i=0;i<256;i++)  {
                        zftd[i]=(double)zft[i]/(w*h);        //计算灰阶为i的概率，注意转换为double类型！！！
//                        System.out.println(i+":"+zftd[i]);        //检测计算结果是否正确
                }
                
                return  zftd;
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