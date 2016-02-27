package com.website.eap.crawler.storage;

import com.website.eap.crawler.model.MovieEntity;

public class DataStorage {
    private HBaseDataApi hBaseDataApi;

    public DataStorage() {
        try {
            this.hBaseDataApi = new HBaseDataApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store(Object data) {
        // store to DB
        // TODO
    }

    public void store(MovieEntity movieEntity) {
        try {
            this.hBaseDataApi.insertData(movieEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
