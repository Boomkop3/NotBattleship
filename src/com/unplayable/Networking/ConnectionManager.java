package com.unplayable.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
	private List<Connection> incomingConnections;
	private List<Connection> createdConnections;
	private static ConnectionManager connectionManager;
	private List<Runnable> onConnection;
	private int port;
	private boolean serverRunning;

	public void setOnConnection(Runnable callback){
		this.onConnection.add(callback);
	}

	public static ConnectionManager getInstance() {
		if (connectionManager == null){
			try {
				connectionManager = new ConnectionManager();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return connectionManager;
	}

	public Connection createConnection(String ip) throws IOException {
		for (Connection connection : this.createdConnections){
			if (connection.getIp().equals(ip)){
				return connection;
			}
		}
		Connection connection = new Connection(
			new Socket(
				ip, 10000
			)
		);
		this.createdConnections.add(connection);
		return connection;
	}

	public void receiveObject(NetworkCallback callback){
		incomingConnections.stream().parallel().forEach((connection -> {
			connection.readObjectAsync((result, adress)->{
				callback.callback(result, adress);
			});
		}));
	}

	private ConnectionManager() throws IOException {
		this.incomingConnections = new ArrayList<>();
		this.createdConnections = new ArrayList<>();
		this.onConnection = new ArrayList<>();
		this.port = 10000;
		this.serverRunning = false;
	}

	public void startServer() throws IOException {
		if (this.serverRunning) return;
		this.serverRunning = true;
		ServerSocket serverSocket = new ServerSocket(10000);
		new Thread(()->{
			while (true) {
				try {
					Socket newSocket = serverSocket.accept();
					this.incomingConnections.add(
						new Connection(
							newSocket
						)
					);
					for (Runnable callback : this.onConnection) {
						new Thread(callback).start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public List<Connection> getIncomingConnections(){
		return this.incomingConnections;
	}
}
