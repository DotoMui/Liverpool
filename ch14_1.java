package img;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public  class  ch14_1  {

    public  static  void  main(String[]  args)  throws  Exception  {
            long  t1=System.currentTimeMillis();                //获取程序运行前的时间
            BufferedImage  bi=ImageIO.read(new  File("input/5.jpg"));                //读取图像
            BufferedImage  nbi=oldPic(bi);                                                //处理图像
            ImageIO.write(nbi,  "jpg",  new  File("output/ch14_1.jpg"));                //输出图像
            long  t2=System.currentTimeMillis();                                //获取程序运行后的时间
            System.out.println("程序运行"+(t2-t1)+"毫秒");        //程序结束后进行提示
    }
	private static BufferedImage oldPic(BufferedImage bi) {
		int w=bi.getWidth();		
		int h=bi.getHeight();		
		BufferedImage nbi=new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);	
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				int pixel=bi.getRGB(x, y);
				Color c=new Color(pixel);		
				double r=(double) (0.393*c.getRed()+0.769*c.getGreen()+0.189*c.getBlue());			
				double g=(double) (0.349*c.getRed()+0.686*c.getGreen()+0.168*c.getBlue());		
				double b=(double) (0.272*c.getRed()+0.543*c.getGreen()+0.131*c.getBlue());	
				if(r>255){
					r=255;
				}else if(r<0){
					r=0;
				}
				if(g>255){
					g=255;
				}else if(g<0){
					g=0;
				}
				if(b>255){
					b=255;
				}else if(b<0){
					b=0;
				}
				Color nc=new Color((int) r,(int)g,(int)b);		
				int rgb=nc.getRGB();
				nbi.setRGB(x, y, rgb);
			}
		}		
		return nbi;
	}
}
