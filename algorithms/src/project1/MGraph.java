package project1;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.net.URL;
public class MGraph {
	public final static int INFINITY = Integer.MAX_VALUE; //2^31-1
	public final static int MIN=0;
	private int vexNUM;
	private double[] X;
	private double[] Y;
	private double[][] arcs;
	private double[][] optimize;
	private int[][] key;
	private ArrayList<Node> Triangle=new ArrayList<Node>();
	private String resultweight;
	private String filename;
	DecimalFormat df= new DecimalFormat("######0.00");
	
	public MGraph(int vexNUM,String filename){
		this.vexNUM=vexNUM;
		this.filename=filename;
		X=new double[vexNUM];
		Y=new double[vexNUM];
		//"source/test.txt"
	}
	
	public void getSource() throws Exception{  
		InputStream is=this.getClass().getResourceAsStream(filename);
		BufferedReader in=new BufferedReader(new InputStreamReader(is)); 
		String temp=in.readLine();
		for(int i=0;temp!=null;i++){
			
			System.out.println(temp);
			String[] xandy=temp.split(",");
			X[i]=Double.parseDouble(xandy[0]);
			Y[i]=Double.parseDouble(xandy[1]);
			temp=in.readLine();
		}
		in.close();
	}
	
	public void createGraph() throws Exception{
		getSource();
		createUDG();
		Outoptimize(1,vexNUM-1);
		resultweight=df.format(optimize[1][vexNUM-1]);
	}
	
	private void createUDG(){
		Scanner in = new Scanner(System.in);	
		arcs=new double[vexNUM][vexNUM];
		for(int v=0;v<vexNUM;v++)
			for(int u=0;u<vexNUM;u++)
				arcs[v][u]=INFINITY;
		
		for(int i=0;i<vexNUM;i++){
			for(int j=0;j<vexNUM;j++){
				double temp=Math.sqrt((X[j]-X[i])*(X[j]-X[i])+(Y[j]-Y[i])*(Y[j]-Y[i]));
				arcs[i][j]=Double.parseDouble(df.format(temp));
				}
		}
		in.close();
		minTriangulation();
	}
	
	public void minTriangulation(){
		key=new int[vexNUM][vexNUM]; //��¼�ӵ�i��j֮�����Ż���k��ֵ
		optimize=new double[vexNUM][vexNUM];//��¼��i��j���Ż��ֵ�Ȩֵ
		double minest;
		for(int t=1;t<vexNUM;t++){//��ʼ���������д�vt-1��vt������ȨֵΪ0��
			optimize[t][t]=0;
		}
		for(int r=2;r<vexNUM;r++){//�趨����Ϊ2��ʼ���㣬�����N-1
			for(int i=1;i<vexNUM-r+1;i++){//�趨i��ʼ����Ϊ1��ÿһ�����iΪvexNUM-r
				int j=i+r-1;//�յ㶥����ÿ���������
				minest=INFINITY;
				for(int k=i;k<j;k++){//����ij������kֵ�����ų��ȣ����ȵ�ǰȨֵС�������Ȩֵ������key
					double temp=optimize[i][k]+optimize[k+1][j]+weight(i-1,k,j);
					if(temp<minest){
						minest=temp;
						key[i][j]=k;
					}
				}
				optimize[i][j]=minest;//����СȨֵ����optimize[i][j]
			}
		}
	}
	
	private double weight(int i,int k,int j){
		double result=arcs[i][k]+arcs[k][j]+arcs[j][i];
		return result;
	}
	
	public void OutputArcs(){
		for(double[] e:arcs)
		{
			for(int i=0;i<e.length;i++)
			{
				System.out.print(e[i]+" ");
			}
			System.out.println(" ");
		}
			
	}
	
	private void Outoptimize(int start,int end){
		if(start==end)
			return;
		Outoptimize(start,key[start][end]);
		Outoptimize(key[start][end]+1,end);
		System.out.println("��ѻ��֣� "+(start-1)+" "+key[start][end]+" "+end);
		Node temp=new Node((start-1),key[start][end],end);
		Triangle.add(temp);
	}
	
	public int GetvexNUM(){
		return vexNUM;
	}
	public double[][] getArcs(){
		return arcs;
	}
	public ArrayList<Node> getTriangle(){
		return Triangle;
	}
	public String getResultweight(){
		return resultweight;
	}
}
