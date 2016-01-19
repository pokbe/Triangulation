package project1;

public class Node {
	private int start;
	private int mid;
	private int end;
	
	public Node(int start,int mid,int end){
		this.start=start;
		this.mid=mid;
		this.end=end;
	}
	public int getStart(){
		return start;
	}
	public int getMid(){
		return mid;
	}
	public int getEnd(){
		return end;
	}
}
