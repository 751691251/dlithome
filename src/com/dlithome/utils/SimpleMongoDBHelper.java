package com.dlithome.utils;


import java.net.UnknownHostException;

import com.mongodb.*;

public class SimpleMongoDBHelper {
	
	private static String mongoURI = "mongodb://dlithome:dlithome@ds035740.mongolab.com:35740/dlithome";
	

	private static Mongo mongo;
	private static DB db;
	private SimpleMongoDBHelper() {
	
	}

	public static Mongo getMongoConnection () {
		if (mongo == null) {
			try {
				mongo = new Mongo(new MongoURI(mongoURI));
			} catch (UnknownHostException e) {
				System.out.println("Failed to connect to mongo db");
			}
		}
		return mongo;
	}

	public static void close () {
		
	}
	
	public static DB getDB (String sdb) {
		db = getMongoConnection().getDB(sdb);
		return db;
	}

	public static DB getDB () {
		return getDB("projects");
	}
	
	public static DBCollection getCollection (String n) {
		return getDB().getCollection(n);
	}
}
