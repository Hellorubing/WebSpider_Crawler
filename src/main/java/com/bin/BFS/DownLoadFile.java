package com.bin.BFS;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DownLoadFile {
	/*
	 * 通过url获取文件名
	 */
	public String getFileNameByUrl(String url,String contentType){
		//移除http或者https协议
		if (url.startsWith("https://")) {
			url = url.substring(8);
		} else if (url.startsWith("http://")) {
			url = url.substring(7);
		}
		//text/html类型
		if (contentType.indexOf("html")!=-1) {
			return url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
		} else {
			//如application/pdf类型
			return url.replaceAll("[\\?/:*|<>\"]", "_")+"."+
					contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	/**
	 * 保存网页字节数组到本地文件，FilePath为要保存的文件的相对地址
	 * @throws IOException 
	 */
	private void saveToLocal(byte[] data,String filePath) throws IOException{
		DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(new File(filePath)));
		for (int i = 0; i < data.length;i++) {
			dataOut.write(data);
			dataOut.flush();
			dataOut.close();
		}
	}
	//下载URL指向的网页
	public String downloadFile(String url){
		String filePath = null;
		//生成HttpClient对象并设置参数
		HttpClient httpClient = new HttpClient();
		//设置HTTP连接超时5s
		httpClient.getHttpConnectionManager().getParams()
		.setConnectionTimeout(5000);
		//生成GetMethod对象并设置参数
		GetMethod getMethod = new GetMethod(url);
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//执行HTTP GET 请求
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+getMethod.getStatusLine());
				filePath = null;
			}
			//处理HTTP响应内容
			byte[] responseBody = getMethod.getResponseBody();//读取为字节数组
			filePath = "temp\\" 
					+ getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
			saveToLocal(responseBody, filePath);
		} catch (HttpException e) {
			System.out.println("Please check your provided http address！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放资源，释放连接
			getMethod.releaseConnection();
		}
		return filePath;
	}
}