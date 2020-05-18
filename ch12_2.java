package img;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * 根据种子点和阈值分割图像
 * 给定种子点和阈值，分割图像
 * */
public class ch12_2 {
	public static ArrayList<zzBean> zlist=new ArrayList<zzBean>();	//种子列表
	public static int[][] f;		//标记是否为目标点

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long t1=System.currentTimeMillis();		//获取程序运行前的时间，单位毫秒
		BufferedImage bi=ImageIO.read(new File("input/2.jpg"));		//读取图像
		zzBean zz=new zzBean(88, 70);
		BufferedImage nbi=quyu(bi,zz,8);						//处理图像
		ImageIO.write(nbi, "jpg", new File("output/ch12_1.jpg"));		//输出图像
		long t2=System.currentTimeMillis();				//获取程序运行后的时间
		System.out.println("程序运行"+(t2-t1)+"毫秒");	//程序结束后进行提示
	}
	
    public static BufferedImage quyu(BufferedImage bi,zzBean zz,int yz){
        int h = bi.getHeight();
        int w = bi.getWidth();
        BufferedImage nbi = new BufferedImage(w,h, BufferedImage.TYPE_3BYTE_BGR);
        f = new int[h][w];
        f[zz.getY()][zz.getX()] = 1;
        diedai(zz,bi,yz);
        for(int i=0;i<h;i++) {
            for (int j = 0; j < w; j++) {
                if(f[i][j]==1){
                    nbi.setRGB(j,i,255*256*256+255*256+255);
                }else{
                    nbi.setRGB(j,i,0);
                }
            }
        }
        return nbi;
    }

    public static void diedai(zzBean zz,BufferedImage bi,int yz){
        if(zz.getX()<1 || zz.getX()>bi.getWidth()-2 || zz.getY()<1 || zz.getY()>bi.getHeight()-2){
            return ;
        }else{
            ArrayList<zzBean> f1 = getEight(zz);
            for(zzBean x : f1){
                if(f[x.getY()][x.getX()]==0 && panduan(zz,x,bi,yz)){
                    f[x.getY()][x.getX()] = 1;
                    diedai(x,bi,yz);
                }
            }
        }

    }

    public static Boolean panduan(zzBean zz,zzBean x,BufferedImage bi,int yz){
        Boolean flag = false;
        Color color1 = new Color(bi.getRGB(zz.getX(),zz.getY()));
        Color color2 = new Color(bi.getRGB(x.getX(),x.getY()));
        if(Math.abs(color1.getGreen()-color2.getGreen())<=yz && Math.abs(color1.getRed()-color2.getRed())<=yz && Math.abs(color1.getBlue()-color2.getBlue())<=yz ){
            flag = true;
        }
        return flag;
    }

    public static ArrayList<zzBean> getEight(zzBean zz){
        ArrayList<zzBean> f1 = new ArrayList<zzBean>();
        f1.add(new zzBean(zz.getX()-1,zz.getY()-1));
        f1.add(new zzBean(zz.getX()-1,zz.getY()));
        f1.add(new zzBean(zz.getX()-1,zz.getY()+1));
        f1.add(new zzBean(zz.getX(),zz.getY()-1));
        f1.add(new zzBean(zz.getX(),zz.getY()+1));
        f1.add(new zzBean(zz.getX()+1,zz.getY()-1));
        f1.add(new zzBean(zz.getX()+1,zz.getY()));
        f1.add(new zzBean(zz.getX()+1,zz.getY()+1));
        return f1;
    }

	

	/**
	 * 种子点，保存坐标
	 * */
	public static class zzBean{
		int x;
		int y;
		
		public zzBean(int x,int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}	
		
	}

}


