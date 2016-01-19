package project1;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class MyPanel extends JPanel{
	private int arcNum;
	private int[] x;
	private int[] y;
	private Polygon pol;
	public double[][] arcs;
	public ArrayList<Node> Triangle=new ArrayList<Node>();
	public String resultweight;
	
	public MyPanel(int arcNum,ArrayList<Node> Triangle,double[][] arcs,String resultweight){
		this.arcNum=arcNum;
		this.Triangle=Triangle;
		this.arcs=arcs;
		this.resultweight=resultweight;
		x=new int[arcNum];
		y=new int[arcNum];
		pol=new Polygon(x,y);
	}
	
	public void paint(Graphics g){
		
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		pol.PosPoint(arcNum);
		g.drawPolygon(x, y, arcNum);
		for(int j=0;j<arcNum;j++){
			String Mark="v"+String.valueOf(j);
			g.drawString(Mark, x[j], y[j]);
		}
		for(int i=0;i<Triangle.size();i++){
			int start=Triangle.get(i).getStart();
			int mid=Triangle.get(i).getMid();
			int end=Triangle.get(i).getEnd();
			g.drawLine(x[start], y[start], x[mid], y[mid]);
			g.drawString(String.valueOf(arcs[start][mid]), (x[start]+x[mid])/2, (y[start]+y[mid])/2);
			g.drawLine(x[mid], y[mid], x[end], y[end]);
			g.drawString(String.valueOf(arcs[mid][end]), (x[mid]+x[end])/2, (y[mid]+y[end])/2);
			g.drawLine(x[start], y[start], x[end], y[end]);
			g.drawString(String.valueOf(arcs[start][end]), (x[start]+x[end])/2, (y[start]+y[end])/2);
		}
		String resultST="Total weight is "+resultweight;
		g.drawString(resultST, 450, 900);
	}
	
}
