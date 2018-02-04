package testWebSpider;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

public class ProxyHttpClient {
	/**
	 * 设置代理的网络代理的抓包方式
	 * @param args
	 */
	public static void main(String[] args) {
		//创建httpClient相当于打开一个代理
		HttpClient httpClient = new HttpClient();
		//设置代理服务器的IP地址和端口
		httpClient.getHostConfiguration().setProxy("192.168.0.1", 9527);
		//告诉httpClient，使用抢先认证，否则你会收到“你没有资格”的恶果
		httpClient.getParams().setAuthenticationPreemptive(true);
		
		//httpClient.getParams().setParameter(CredentialsProvider.PROVIDER, new MyProxyCredentialsProvider());
		httpClient.getState().setProxyCredentials(new AuthScope("192.168.0.1",AuthScope.ANY_PORT,AuthScope.ANY_REALM), 
				new UsernamePasswordCredentials("userName", "password"));
		
	}
}