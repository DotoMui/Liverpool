package img;

import  java.awt.Color;
import  java.awt.image.BufferedImage;
import  java.io.File;
import  java.io.IOException;

import  javax.imageio.ImageIO;

/**
  *  图像Robert边缘算子，阈值判断边缘点
  *  两对角线方向相邻像素差近似梯度幅值监测边缘
  *  */
public  class  ch8_4  {

        public  static  void  main(String[]  args)  throws  Exception  {
                long  t1=System.currentTimeMillis();                //获取程序运行前的时间，单位毫秒
                BufferedImage  bi=ImageIO.read(new  File("input/2.jpg"));                //读取图像
                BufferedImage  nbi=robert(bi,50);                                                //处理图像
                ImageIO.write(nbi,  "jpg",  new  File("output/8_4b.jpg"));                //输出图像
                long  t2=System.currentTimeMillis();                                //获取程序运行后的时间
                System.out.println("程序运行"+(t2-t1)+"毫秒");        //程序结束后进行提示

        }

        private  static  BufferedImage  robert(BufferedImage  bi,int  yz)  {
                int  w=bi.getWidth();                //得到图像的宽度
                int  h=bi.getHeight();                //得到图像的高度
                BufferedImage  nbi=new  BufferedImage(w,  h,  BufferedImage.TYPE_3BYTE_BGR);        
                //创建新图像（临时图像变量）宽度和高度跟原图相同
                //循环遍历每一个像素点
                for(int  y=1;y<(h-1);y++)  {
                        for(int  x=1;x<(w-1);x++)  {
                                int  rgb=robert(getSz(bi,x,y,0),yz);
                                nbi.setRGB(x,  y,  rgb);                        //设置输出图像坐标为（x,y）的像素值
                        }
                }                
                return  nbi;
        }
        
        /**
          *  根据数组求robert值
          *  @param  int[]  sz                          3*3像素值数组
          *  @param  int  yz                        阈值，判断边缘点
          *  @return  int                                像素值（均值）
          *  */
        private  static  int  robert(int[]  sz,int  yz)  {
    		int r1 = Math.abs(sz[0]-sz[8]);
    		int r2 = Math.abs(sz[2]-sz[6]);
    		int rs = Math.max(r1, r2);
    		if(rs >=yz)
    			rs = 255*256*256+255*256+255;
    		else
    			rs = 0;
    		return rs;

        }

        /**
          *  得到原图(x,y)为中心3*3范围的数组
          *  @param  BufferedImage  bi          原始图像
          *  @param  int  x                                原图坐标x
          *  @param  int  y                                原图坐标y
          *  @param  int  key                        通道。0灰度，1红色，2绿色，3蓝色
          *  @return  int[]                        3*3数组
          *  */
        private  static  int[]  getSz(BufferedImage  bi,int  x,int  y,int  key)  {
                int[]  sz=new  int[9];
                int  n=0;
                if(x>0  &&  x<(bi.getWidth()-1)  &&  y>0  &&  y<(bi.getHeight()-1))  {
                        for(int  j=(y-1);j<=(y+1);j++){
                                for(int  i=(x-1);i<=(x+1);i++){
                                        Color  c=new  Color(bi.getRGB(i,  j));
                                        switch  (key)  {
                                        case  0:
                                                sz[n]=(int)  (0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
                                                break;
                                        case  1:
                                                sz[n]=c.getRed();
                                                break;
                                        case  2:
                                                sz[n]=c.getGreen();
                                                break;
                                        case  3:
                                                sz[n]=c.getBlue();
                                                break;
                                        default:
                                                break;
                                        }
                                        n++;
                                }
                        }                        
                }
                return  sz;
        }

}