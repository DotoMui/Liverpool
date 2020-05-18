package img;

import  java.awt.Color;
import  java.awt.image.BufferedImage;
import  java.io.File;
import  java.io.IOException;

import  javax.imageio.ImageIO;

/**
  *  图像分割，ostu
  *  */
public  class  ch11_2  {

        public  static  void  main(String[]  args)  throws  Exception  {
                long  t1=System.currentTimeMillis();                //获取程序运行前的时间，单位毫秒
                BufferedImage  bi=ImageIO.read(new  File("input/2.jpg"));                //读取图像
                double[]  zft=getZft(bi);
                int  nyz=ostu(zft);
                BufferedImage  nbi=ptile(bi,nyz);                                                //处理图像
                ImageIO.write(nbi,  "jpg",  new  File("output/ch11_2.jpg"));                //输出图像
                long  t2=System.currentTimeMillis();                                //获取程序运行后的时间
                System.out.println("程序运行"+(t2-t1)+"毫秒");        //程序结束后进行提示

        }

        /**
          *  根据图像直方图，计算最大ostu值
          *  @param  double[]  zft                  图像直方图
          *  @return  int                                出现最大ostu值时的灰度值
          *  */
        private  static  int  ostu(double[]  zft)  {
                double[]  os  =  new  double[256];
                for(int  i=0;i<256;i++)  {
                        os[i]=ostu(zft,i);
                        System.out.println(i+":"+os[i]);
                }
                int  n=0;
                double  max=os[0];
                for(int  i=1;i<256;i++)  {
                        if(max<os[i])  {
                                max=os[i];
                                n=i;
                        }
                }
                System.out.println("ostu最大值出现在"+n+"，值="+max);
                return  n;
        }

        /**
          *  根据图像直方图和阈值，计算ostu值
          *  @param  double[]  zft                  图像直方图
          *  @param  int  yz                        分割阈值
          *  @return  double                        当前阈值分割下的ostu值
          *  */
        private  static  double  ostu(double[]  zft,  int  yz)  {
        	double w1=0,w0=0;
        	double u0=0,u1=0;
        	double sum=0;
        	double []p = new double[256];
        	for(int i=0;i<zft.length;i++){
        		p[i]=zft[i];
        		sum+=p[i];
        	}
        	for(int i=0;i<yz;i++){
        		w0+=p[i];
        	}
        	for(int i=yz;i<zft.length;i++){
        		w1+=p[i];
        	}
        	for(int i=0;i<yz;i++){
        		u0+=(i*p[i])/w0;
        	}
        	for(int i=yz;i<zft.length;i++){
        		u1+=(i*p[i])/w1;
        	}
        	return (w0*w1*(u0-u1)*(u0-u1));
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
                                int  gray=getGray(bi.getRGB(x,  y));                                
                                int  rgb=0;
                                if(gray>=yz)  {
                                        rgb=Color.WHITE.getRGB();
                                }
                                nbi.setRGB(x,  y,  rgb);                        //设置输出图像坐标为（x,y）的像素值
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