package com.website.eap.crawler.storage;

import com.website.eap.crawler.model.MovieEntity;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/22
 * Time: 18:12
 * Desc:
 */
public class HBaseDataApi {



    private  Configuration conf;

    private Connection connection=null;

    public HBaseDataApi() throws  Exception{
        this.conf= HBaseConfiguration.create(); // co GetExample-1-CreateConf Create the configuration.
        this.conf.set("hbase.zookeeper.quorum", "192.168.3.114,192.168.3.115,192.168.3.182");
        this.conf.set("hbase.zookeeper.property.clientPort", "2181");
        this.conf.set("hbase.rootdir", "hdfs://192.168.3.114:49000/hbase");
        this.connection = ConnectionFactory.createConnection(this.conf);
    }

    //title->title,url
    //rate->rate
    //cover->cover,cover_x,cover_y
    //subject->playable,is_new,is_beetle_subject
    //rowkey:movie-id
    public void insertData(MovieEntity movieEntity)throws Exception{

        Table table=this.connection.getTable(TableName.valueOf("wac-crawler-douban"));

        Put put=new Put(Bytes.toBytes("movie-"+movieEntity.getId()));

        //title族
        put.addColumn(Bytes.toBytes("title"),Bytes.toBytes("title"),Bytes.toBytes(movieEntity.getTitle()));
        put.addColumn(Bytes.toBytes("title"),Bytes.toBytes("url"),Bytes.toBytes(movieEntity.getUrl()));
        //rate族
        put.addColumn(Bytes.toBytes("rate"),Bytes.toBytes("rate"),Bytes.toBytes(String.valueOf(movieEntity.getRate())));
        //cover族
        put.addColumn(Bytes.toBytes("cover"),Bytes.toBytes("cover"),Bytes.toBytes(movieEntity.getCover()));
        put.addColumn(Bytes.toBytes("cover"),Bytes.toBytes("cover_x"),Bytes.toBytes(String.valueOf(movieEntity.getCoverX())));
        put.addColumn(Bytes.toBytes("cover"),Bytes.toBytes("cover_y"),Bytes.toBytes(String.valueOf(movieEntity.getCoverY())));
        //subject族
        put.addColumn(Bytes.toBytes("subject"),Bytes.toBytes("playable"),Bytes.toBytes(String.valueOf(movieEntity.isPlayable())));
        put.addColumn(Bytes.toBytes("subject"),Bytes.toBytes("is_new"),Bytes.toBytes(String.valueOf(movieEntity.isNew())));
        put.addColumn(Bytes.toBytes("subject"),Bytes.toBytes("is_beetle_subject"),Bytes.toBytes(String.valueOf(movieEntity.isBeetleSubject())));

        table.put(put);
        table.close();
    }

    public List<MovieEntity> queryData(String tableName){

        ResultScanner rs=null;
        List<MovieEntity> movieEntityList=new ArrayList<>();
        try {
            Scan scan=new Scan();
            Table table=this.connection.getTable(TableName.valueOf(tableName));
            rs=table.getScanner(scan);
            for(Result row:rs){
                System.out.println(new String(row.getRow()));
                byte[] title= row.getValue(Bytes.toBytes("title"),Bytes.toBytes("title"));
                byte[] url=row.getValue(Bytes.toBytes("title"),Bytes.toBytes("url"));

                byte[] rate=row.getValue(Bytes.toBytes("rate"),Bytes.toBytes("rate"));

                byte[] cover=row.getValue(Bytes.toBytes("cover"),Bytes.toBytes("cover"));
                byte[] cover_x=row.getValue(Bytes.toBytes("cover"),Bytes.toBytes("cover_x"));
                byte[] cover_y=row.getValue(Bytes.toBytes("cover"),Bytes.toBytes("cover_y"));

                byte[] playable=row.getValue(Bytes.toBytes("subject"),Bytes.toBytes("playable"));
                byte[] is_new=row.getValue(Bytes.toBytes("subject"),Bytes.toBytes("is_new"));
                byte[] is_beetle_subject=row.getValue(Bytes.toBytes("subject"),Bytes.toBytes("is_beetle_subject"));
                MovieEntity movieEntity=new MovieEntity();
                movieEntity.setTitle(new String(title,"UTF-8"));
                movieEntity.setUrl(new String(url, "UTF-8"));
                movieEntity.setRate(Float.valueOf(new String(rate, "UTF-8")));
                movieEntity.setCover(new String(cover, "UTF-8"));
                movieEntity.setNew(Boolean.valueOf(new String(is_new, "UTF-8")));
                movieEntity.setPlayable(Boolean.valueOf(new String(playable, "UTF-8")));
                movieEntity.setBeetleSubject(Boolean.valueOf(new String(is_beetle_subject, "UTF-8")));
                movieEntity.setCoverX(Integer.valueOf(new String(cover_x, "UTF-8")));
                movieEntity.setCoverY(Integer.valueOf(new String(cover_x,"UTF-8")));
                movieEntityList.add(movieEntity);
                System.out.println((new String(title,"UTF-8")+"\t"+new String(url,"UTF-8")+"\t"+new String(rate,"UTF-8")+"\t"+new String(cover,"UTF-8")+"\t"+new String(cover_x,"UTF-8")+"\t"+new String(cover_y,"UTF-8")+"\t"+new String(playable,"UTF-8")+"\t"+new String(is_new,"UTF-8")+"\t"+new String(is_beetle_subject,"UTF-8")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                rs.close();
            }
            return movieEntityList;
        }
    }


    public static void main(String[] args) throws  Exception{
        HBaseDataApi hBaseDataApi=new HBaseDataApi();
        hBaseDataApi.queryData("wac-crawler-douban");
    }
}
