package com.website.eap.crawler.storage;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/22
 * Time: 18:11
 * Desc:
 */
public class HBaseCreateTable {

    public static void createTable() throws Exception{
        Configuration conf = HBaseConfiguration.create(); // co GetExample-1-CreateConf Create the configuration.

        conf.set("hbase.zookeeper.quorum", "192.168.3.114,192.168.3.115,192.168.3.182");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.rootdir", "hdfs://192.168.3.114:49000/hbase");
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("wac-crawler-douban");
        if (!helper.existsTable("wac-crawler-douban")) {
            //title->title,url
            //rate->rate
            //cover->cover,cover_x,cover_y
            //subject->playable,is_new,is_beetle_subject
            //rowkey:movie-id
            helper.createTable("wac-crawler-douban", "title","rate","cover","subject");
            System.out.println("=====初始化hbase wac-crawler-douban 成功=====");
        }
    }

    public static void main(String[] args) throws  Exception{
        HBaseCreateTable.createTable();
    }
}
