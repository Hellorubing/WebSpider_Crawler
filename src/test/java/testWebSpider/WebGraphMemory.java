package testWebSpider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebGraphMemory {
	//把每个URL映射为一个整数，存储在Web图中
	private Map<Integer,String> IdentifyerToURL;
	//存储Web图的Hash表
	private Map<String,Map<String,Integer>> URLToIdentifyer;
	//存储入度，其中整数的第一个参数是URL的ID,第二个参数存放指向这个URL链接的Map
	private Map<Integer,Map<Integer,Double>> InLinks;
	//存储出度,其中第一个参数是URL的ID,第二个参数存放网页中的超链接,Double表示权重
	private Map<Integer,Map<Integer,Double>> OutLinks;
	//图中节点的数目
	private int nodeCount;

	//构造函数，0个节点的构造函数
	public WebGraphMemory(){
		IdentifyerToURL = new HashMap<Integer,String>();
		URLToIdentifyer = new HashMap<String,Map<String,Integer>>();
		InLinks = new HashMap<Integer,Map<Integer, Double>>();
		OutLinks = new HashMap<Integer,Map<Integer,Double>>();
		nodeCount = 0;
	}
	
	/**
	 * 从一个文本文件中取得节点的构造函数，每行包含一个指向关系
	 * http://url1.com 包含一个超链接 http://url2.com,并且这个超链接的权重是1.0
	 */
	public WebGraphMemory (File file) throws IOException {
		this();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while (null != (line = reader.readLine())) {
			int index1 = line.indexOf("->");
			if (index1 == -1) {
				
			}
		}
	}
	
	public Integer URLToIdentifyer (String URL) {
		String host;
		String name;
		int index = 0,index2 = 0;
		if (URL.startsWith("http://"))
			index = 7;
		else if (URL.startsWith("ftp://"))
			index = 6;
		else if (URL.startsWith("https://")) 
			index = 8;
		index2 = URL.substring(index).indexOf("/");
		if (index2 != -1){
			name = URL.substring(index+index2+1);
			host = URL.substring(0,index+index2);
		} else {
			host = URL;
			name = "";
		}
		Map<String,Integer> map = URLToIdentifyer.get(host);
		if (null == map) {
			return null;
		}
		return map.get(name);
	}
	/**
	 * 根据ID获得URL
	 */
	public String IdentifyerToURL(Integer id) {
		return IdentifyerToURL.get(id);
	}
	/**
	 * 在图中增加一个节点
	 */
	public Integer addLink(String link) {
		Integer id = URLToIdentifyer(link);
		if (null == id) {
			id = new Integer(++nodeCount);
			String host;
			String name;
			int index = 0,index2 = 0;
			if (link.startsWith("http://"))
				index = 7;
			else if (link.startsWith("ftp://")) 
				index = 6;
			index2 = link.substring(index).indexOf("/");
			if (index2 != -1) {
				name = link.substring(index+index2+1);
				host = link.substring(0, index + index2);
			} else {
				host = link;
				name = "";
			}
		}
		return 1;
	}
	/**
	 * 删除内部导航链接
	 */
	public void removeNepotistic() {
		
	}
	//删除终止URL
	public void removeStopLinks(String stopURLs[]) {
		HashMap aux = new HashMap<>();
		for (int i =0; i<stopURLs.length;i++) 
			aux.put(stopURLs, null);
		removeStopLinks(aux);
	}
	/**
	 *	删除终止URL 
	 */
	public void removeStopLinks(Map stopURLs) {
		int index1;
		Iterator it = OutLinks.keySet().iterator();
		while (it.hasNext()) {
			Integer link1 = (Integer)it.next();
			String URL1 = (String) IdentifyerToURL.get(link1);
			index1 = URL1.indexOf("://");
			if (index1 != -1) 
				URL1 = URL1.substring(index1+3);
			index1 = URL1.indexOf("/");
			if (stopURLs.containsKey(URL1)) {
				OutLinks.put(link1, new HashMap());
				InLinks.put(link1, new HashMap());
			}
		}
	}
	
	public int numNodes() {
		return nodeCount;
	}	
}