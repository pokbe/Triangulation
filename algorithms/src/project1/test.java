package project1;
import java.net.URL;
import java.io.*;
import java.util.*;

import javax.swing.*;
public class test extends JFrame {
	private MyPanel mypanle;
	public static ArrayList<Node> Triangle=new ArrayList<Node>();
	public static double[][] arcs;
	public static String resultweight;
	public static int vexNum;
	
	public test(){
		mypanle=new MyPanel(vexNum,Triangle,arcs,resultweight);
		this.add(mypanle);
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws Exception{
		
		Scanner in=new Scanner(System.in);
		System.out.println("«Î—°‘Ò≤‚ ‘ºØ£∫1°¢10test 2°¢30test :");
		int choice=in.nextInt();
		if(choice==1){
			vexNum=10;
			MGraph m=new MGraph(vexNum,"/source/test10.txt");
			m.createGraph();
			Triangle=m.getTriangle();
			arcs=m.getArcs();
			resultweight=m.getResultweight();
		}
		else{
			vexNum=30;
			MGraph m=new MGraph(vexNum,"/source/test30.txt");
			m.createGraph();
			Triangle=m.getTriangle();
			arcs=m.getArcs();
			resultweight=m.getResultweight();
		}
		
		test test=new test();
		
	}
}
