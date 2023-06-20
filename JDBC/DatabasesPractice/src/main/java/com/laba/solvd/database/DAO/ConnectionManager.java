package com.laba.solvd.database.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConnectionManager {

	public static final ConnectionPool Pool = new ConnectionPool(5);
	
	public static Future getSQLConnection() {
		Callable c = () ->{
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
			System.out.println("Connected to Database");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connection failed");
		return null;};
		return Pool.getSQLConnection(c);
	}
	
	static class ConnectionPool {

		private ArrayBlockingQueue<MyConnection> Pool;
		private int size;
		
		public ConnectionPool(int size) {
			this.Pool = new ArrayBlockingQueue<MyConnection>(size);
			this.size = size;
		}	
		
		private MyConnection fetchOrStartConnection() {
			boolean found = false;
			if (this.Pool.size() == this.size) {
				while (!found) {
					for (MyConnection c: this.Pool) {
						if (c.status == ConnectionStatus.IDLE) {
							return c;
						}
					}
				}
			} 
			MyConnection connection = new MyConnection();
			this.Pool.add(connection);
			return connection;

		}
		
		public Future getSQLConnection(Callable con) {
			MyConnection connection = this.fetchOrStartConnection(); 
			connection.status = ConnectionStatus.ACTIVE;
			Future f = connection.SubmitReturnableThread(con);
			this.returnConnection(connection, f);
			return f;
		}

		private void returnConnection(MyConnection c, Future f) {
			Thread t = new Thread(() -> {
				while (!f.isDone()) {
				}
				c.status = ConnectionStatus.IDLE;
			});
			t.start();
		}
		
		private static class MyConnection {
			ExecutorService s = Executors.newSingleThreadExecutor();
			ConnectionStatus status;
			
			MyConnection() {
				this.status = ConnectionStatus.IDLE;
			}
			
			Future SubmitReturnableThread(Callable callable) {
				Future f = s.submit(callable);
				return f;
			}
		}

		private static enum ConnectionStatus {
			IDLE,
			ACTIVE;
		}
	}
}