package com.bin.BFS;

public class MyCrawler {
	/**
	 * 使用种子初始化URL队列
	 * @return
	 * @param seeds 种子URL
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i<seeds.length;i++) 
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}
	
	/**
	 * 抓取过程
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds) {
		
	}
	
	public static void main(String[] args) {
		
	}
}