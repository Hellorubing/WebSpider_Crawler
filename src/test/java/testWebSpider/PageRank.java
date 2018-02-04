package testWebSpider;

import java.util.Hashtable;

public class PageRank {
	private double[] rank;
	Hashtable<String, Integer> hashedPages;
	String[] sortedRank;
	public PageRank() {
	}
	private void rankFilter(){
		
	}
}
//矩阵类
class BigMatrix {
	public int nCols,nRows;
	EntryList[] theRows;
	//构造函数采用String的数组作为输入，例如{"(1,1);(4,3);(5,8)","(2,5);(3,4)","(3,8),()"}
	//每个字符串能够初始化一行数据。例如，(2,5)表示在第二行的第二列值为5
	public BigMatrix(String[] x) {
		nRows = x.length;
		nCols = 0;
		theRows = new EntryList[nRows];
		for (int i=0;i<nRows;i++) {
			theRows[i] = new EntryList();
			if (x[i] != null) {
				String[] tempArr = x[i].split(";");
				
			}
		}
	}
}
//矩阵的元素
class Entry{
	int col;//元素所在列
	double value;//元素值
	public Entry(String x) {
		String[] temp = x.split(",");
		if (temp[0].compareTo("") != 0) {
			col = Integer.parseInt(temp[0].trim().substring(1));
			value = Double.parseDouble(temp[1].trim().substring(0, temp[1].trim().length()-1));
		}	
	}
}
//元素列表，对行进行建模
class EntryList {
	Entry data;
	EntryList next,tail;
	public EntryList() {
		next = null;
		tail = null;
		data = null;
	}
	//添加数据
	void add(Entry x) {
		if (null == tail) {
			data = x;
			tail = this;
		} else {
			tail.next = new EntryList();
			tail.next.data = x;
			tail = tail.next;
		}
	}
}





















