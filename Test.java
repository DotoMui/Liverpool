package img;
import java.lang.reflect.Array;
import java.util.Arrays; 

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//xianxing(184,0.5,30);
		//bianhuan(1130,950,510);
		//zoomJbjz();
		zoomSxx(88,138,229,210,3/5,4/5);
		//smoothJz();
		//smoothJqjz();
		//smoothZz();
		//robert();
		//sobel();
		//kirsch();
		//ostu();
		//maxshang();
		//smoothZz();

	}
	//����ͼ������Ա任
	public static void xianxing(int pix,double aa,int bb){//pix:���� aa:�任ϵ�� bb:����ϵ��
		int rs = Math.max(0,Math.min(255, (int)(aa*pix+bb)));
		System.out.println(rs);
	}
	//����ͼ��Ŀ����任
	public static void bianhuan(int pix,int cw,int ww){
		int rs =(int)(255*(pix-cw+ww/2)/ww);
		if(pix<=cw-ww/2) rs=0;
		if(pix>=cw+ww/2) rs=255;
		System.out.println(rs);
	}
	public static void zoomJbjz(){
		int x=1,y=3;
		double k=0.5;	//放大系数
		int xx0;
		int yy0;
		xx0 = (int) (x/k);
		yy0 = (int) (y/k);
		System.err.print("("+xx0+","+yy0+")"+"("+(int)(xx0+1)+","+yy0+")\n"+"("+xx0+","+(int)(yy0+1)+")"+"("+(int)(xx0+1)+","+(int)(yy0+1)+")");
		
	}
	public static void zoomSxx(int a,int b ,int c,int d,double xx,double yy){
		double y = a*(1-xx)*(1-yy) + b*xx*(1-yy) + c*(1-xx)*yy + d*xx*yy;
		System.err.println((int)y);
		}
	
	public static void smoothJz(){
		int[] sz = new int[] {56,253,201,243,31,110,104,122,229};
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
		System.err.println(pix);
	}
	//��Ȩ��ֵ�˲� 3*3���ģ��{1,2,1,2,4,2,1,2,1}
	public static void smoothJqjz(){
		int count = 0;
		int sum = 0;
		int[] qz=new int[] {1,2,1,2,4,2,1,2,1};
		//int[] pix = new int[] {181,130,139,188,178,222,140,196,25};
		//int[] pix = new int[] {201,105,175,219,235,130,90,128,181};
		int[] pix = new int[] {229,32,232,27,13,124,213,37,242};
		for(int i=0;i<qz.length;i++){
			count+=qz[i];
			sum+=pix[i]*qz[i];
		}
		System.err.println(sum/count);
	}
	
	public static void smoothZz(){
		int []sz=new int[] {243,31,110,104,122,229,229,247,27};
		for(int i=0;i<sz.length;i++){
			for(int j=0;j<sz.length-i-1;j++){
				int temp;
				if(sz[j]>sz[j+1]){
					temp=sz[j];
					sz[j]=sz[j+1];
					sz[j+1]=temp;
				}
			}
		}
/*		for(int i:sz){
			System.err.println(i);
		}*/
		int pix = 0;
		pix = sz[sz.length/2];
		System.err.println(pix);
	}
	//Robert
	public static void robert(){
		//int []sz=new int[] {201,105,175,219,235,130,90,128,181};
		int []sz=new int[] {146,178,36,118,155,128,119,124,191};
		int r1 = Math.abs(sz[0]-sz[8]);
		int r2 = Math.abs(sz[2]-sz[6]);
		System.err.println(Math.max(r1, r2));
	}
	//sobel
	public static void sobel(){
		//int []sz=new int[] {201,105,175,219,235,130,90,128,181};
		//int []sz = new int[] {120,37,8,137,1,29,138,182,8};
		//int []sz=new int[] {181,130,139,188,178,222,140,196,25};
		int []sz = new int[] {137,1,29,138,182,8,166,187,29};
		int s1 = Math.abs(sz[0]+2*sz[1]+sz[2]-(sz[6]+2*sz[7]+sz[8]));
		int s2 = Math.abs(sz[0]+2*sz[3]+sz[6]-(sz[2]+2*sz[5]+sz[8]));
		int rs = Math.abs(s1+s2);
		System.err.println(rs>255?255:rs);
	}
	
    public  static  void  kirsch()  {
    	//int []sz = new int[]{181,130,139,188,178,222,140,196,25};
    	//int []sz = new int[]{167,120,37,62,137,1,229,138,182};
    	int []sz = new int[]{37,8,104,1,29,72,182,8,248};
		int []rs = new int[] {0,0,0,0,0,0,0,0};
		int max = 1;
    	int model[][]={
    			{5,5,5,-3,0,-3,-3,-3,-3},
    			{-3,5,5,-3,0,5,-3,-3,-3},
    			{-3,-3,5,-3,0,5,-3,-3,5},
    			{-3,-3,-3,-3,0,5,-3,5,5},
    			{-3,-3,-3,-3,0,-3,5,5,5},
    			{-3,-3,-3,5,0,-3,5,5,-3},
    			{5,-3,-3,5,0,-3,5,-3,-3},
    			{5,5,-3,5,0,-3,-3,-3,-3}};
    	for(int i=0;i<8;i++){
    		for(int j=0;j<9;j++){
    			rs[i]+=(int)(model[i][j]*sz[j]);
    		}
    		rs[i]=Math.abs(rs[i]);
    		if(Math.abs(rs[i])>max)
    			max = Math.abs(rs[i]);
    	}
        if(max>255)
            max=255;
        System.err.println(max);
    }
    //图像分割 ostu
    public static void ostu(){
    	//double []zft = new double[] {10,7,7,8,1,14,5,12};
    	double []zft = new double[] {10,12,7,8,1,16,5,5};
    	double sum=0;
    	double []p = new double[8];
    	double w1=0,w0=0;
    	double u0=0,u1=0;
    	for(int i=0;i<zft.length;i++){
    		sum+=zft[i];
    	}
    	for(int i=0;i<zft.length;i++){
    		p[i]=zft[i]/sum;
    		System.err.println(p[i]);
    	}
    	for(int i=0;i<4;i++){
    		w0+=p[i];
    	}
    	for(int i=4;i<zft.length;i++){
    		w1+=p[i];
    	}
    	//System.err.println(w1);
    	for(int i=0;i<4;i++){
    		u0+=(i*p[i])/w0;
    	}
    	for(int i=4;i<zft.length;i++){
    		u1+=(i*p[i])/w1;
    	}
    	//System.err.println(u1);
    	System.err.println(w0*w1*(u0-u1)*(u0-u1));
    }
    
    //图像分割 最大熵
    public static void maxshang(){
    	//double []zft = new double[] {5,12,16,8,1,7,10,5};
    	double []zft = new double[] {5,12,16,8,1,7,10,5};
    	double sum=0;
    	double []p = new double[8];
    	double []s = new double[8];
    	for(int i=0;i<zft.length;i++){
    		sum+=zft[i];
    	}
    	//灰度值的概率
    	for(int i=0;i<zft.length;i++){
    		p[i]=zft[i]/sum;
    		//System.err.println(p[i]);
    	}
    	//灰度值的熵
    	for(int i=0;i<zft.length;i++){
    		s[i]=-p[i]*Math.log(p[i]);
    	}
    	System.err.println(s[4]);
    }
}
