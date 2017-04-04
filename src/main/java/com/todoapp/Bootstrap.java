package com.todoapp;

import com.mongodb.*;

public class Bootstrap {
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_IP") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_IP")) : 8080;
    public static final int TOP_K = 10;
    public static void main(String[] args) throws Exception {
//        setIpAddress(IP_ADDRESS);
//        setPort(PORT);
//        staticFileLocation("/public");
//        new TodoResource(new TodoService(mongo()));
        new RankerController(new RankerService(mongo()));
        String username = "realDonaldTrump";
//        Collector col = new Collector(username);
//        Ranker1 ranker = new Ranker1(username);
//        ranker.findTop10(col.getTweets());
    }

    private static DB mongo() throws Exception {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("todoapp");
        }
        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        System.out.println(username);
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
//        if (db.authenticate(username, password.toCharArray())) {
//            return db;
//        } else {
//            throw new RuntimeException("Not able to authenticate with MongoDB");
//        }
        return db;
    }
}
