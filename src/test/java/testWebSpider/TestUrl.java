package testWebSpider;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestUrl {
	public static void main(String[] args) throws IOException {
		String path = "https://www.baidu.com/";
		//URL pageUrl = new URL(path);
		//创建一个客户端，类似于打开一个浏览器
		HttpClient httpclient = new HttpClient();
		//创建一个get方法，类似于在浏览器地址栏中输入一个地址
		GetMethod getMethod = new GetMethod(path);
		//回车，获得响应状态码
		int statusCode = httpclient.executeMethod(getMethod);
		
		System.out.println(statusCode);
		//查看命中情况，可以获得的东西还有很多，比如head、cookies等
		System.out.println("response=" + getMethod.getResponseBodyAsString());
		//等到post方法
		PostMethod postMethod = new PostMethod(path);
		//使用数组来传递参数
		NameValuePair[] postData = new NameValuePair[2];
		//设置参数
		postData[0] = new NameValuePair("武器", "枪");
		postData[1] = new NameValuePair("什么枪", "神枪");
		
		postMethod.addParameters(postData);
		
		int s = httpclient.executeMethod(postMethod);
		
		System.out.println(s + " response = = "+postMethod.getResponseBodyAsString());
		//释放
		getMethod.releaseConnection();
	}
}