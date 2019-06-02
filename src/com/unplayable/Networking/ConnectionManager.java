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
	private int port;

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
		this.port = 10000;
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
