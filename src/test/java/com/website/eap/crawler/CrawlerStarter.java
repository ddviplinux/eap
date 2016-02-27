package com.website.eap.crawler;




import com.website.eap.crawler.model.CrawlerParams;
import com.website.eap.crawler.queue.UrlQueue;
import com.website.eap.crawler.storage.HBaseCreateTable;
import com.website.eap.crawler.worker.CrawlerWorker;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CrawlerStarter {

	public static void main(String[] args){

        CrawlerStarter crawlerStarter=new CrawlerStarter();
		// 初始化配置参数
        crawlerStarter.initializeParams();

		// 初始化爬取队列
        crawlerStarter.initializeQueue();

        // 初始化hbase 爬虫表
        crawlerStarter.initializeHBaseTable();
		
		// 创建worker线程并启动
		for(int i = 1; i <= CrawlerParams.WORKER_NUM; i++){
			new Thread(new CrawlerWorker(i)).start();
		}
	}
	
	/**
	 * 初始化配置文件参数
	 */
	private  void initializeParams(){
		InputStream in=null;
		try {
			in = new BufferedInputStream(this.getClass().getResourceAsStream("/conf/crawler.properties"));
			Properties properties = new Properties();
			properties.load(in);
			
			// 从配置文件中读取参数
			CrawlerParams.WORKER_NUM = Integer.parseInt(properties.getProperty("crawler.threadNum"));
			CrawlerParams.DEYLAY_TIME = Integer.parseInt(properties.getProperty("crawler.fetchDelay"));

			in.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	}
	
	/**
	 * 准备初始的爬取链接
	 */
	private  void initializeQueue(){
		for(int i =0;i<100;i++){
			UrlQueue.addElement("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=" + i * 20 + 1);
		}
	}

    /**
     * 初始化hbase爬虫表
     */
    private void initializeHBaseTable(){
        try {
            HBaseCreateTable.createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
