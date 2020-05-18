package img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ch1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedImage bi=ImageIO.read(new File("input/1.jpg"));		//��ȡͼ��
		BufferedImage nbi=fanxiang(bi);								//����ͼ��
		ImageIO.write(nbi, "jpg", new File("output/1b.jpg"));		//���ͼ��
	}

	private static BufferedImage fanxiang(BufferedImage bi) {
		int w=bi.getWidth();		//�õ�ͼ��Ŀ��
		int h=bi.getHeight();		//�õ�ͼ��ĸ߶�
		BufferedImage nbi=new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);	//������ͼ����ʱͼ�������
		//ѭ������ÿһ�����ص�
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				//����ʵ����ص�����
				int pixel=bi.getRGB(x, y);		//�õ�����Ϊ��x,y����������ֵ
				Color c=new Color(pixel);		//ת��ΪColor�࣬���ڴ���
				int r=255 - c.getRed();			//��ɫͨ��ȡ��
				int g=255 - c.getGreen();		//��ɫͨ��ȡ��
				int b=255 - c.getBlue();		//��ɫͨ��ȡ��
				Color nc=new Color(r,g,b);		//���ݺ���������ͨ�����ϳ�����ɫ
				int rgb=nc.getRGB();			//ת��Ϊint����
				nbi.setRGB(x, y, rgb);			//�������ͼ������Ϊ��x,y��������ֵ
			}
		}		
		return nbi;
	}
}
