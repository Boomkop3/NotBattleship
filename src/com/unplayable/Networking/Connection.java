package com.unplayable.Networking;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	private Socket socket;
	private ObjectOutputStream objectOutputStream;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
	}

	private ObjectOutputStream getOutputStream() throws IOException {
		if (this.objectOutputStream == null){
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		}
		return this.objectOutputStream;
	}

	private ObjectInputStream getInputStream() throws IOException {
		return new ObjectInputStream(socket.getInputStream());
	}

	public void sendObject(Object object) throws IOException {
			ObjectOutputStream outputStream = this.getOutputStream();
			outputStream.writeObject(object);
			outputStream.flush();
	}

	public Object readObject() throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = this.getInputStream();
		return inputStream.readObject();
	}

	public void readObjectAsync(NetworkCallback callback){
		new Thread(()->{
			try {
				ObjectInputStream inputStream = this.getInputStream();
				while (true){
					Thread.yield();
					callback.callback(
						inputStream.readObject(),
						this.getAdress()
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private DataOutputStream dataOutputStream;

	private DataOutputStream getDataOutputStream() throws IOException {
		if (this.dataOutputStream == null){
			this.dataOutputStream = new DataOutputStream(
				this.socket.getOutputStream()
			);
		}
		return this.dataOutputStream;
	}

	public void writeString(String text) throws IOException {
		this.getDataOutputStream().writeUTF(
			text
		);
	}

	public String getAdress(){
		String adress = this.socket.getRemoteSocketAddress().toString();
		return adress.replace("/", "");
	}

	public String getIp(){
		String adress = this.getAdress();
		adress = adress.substring(0, adress.indexOf(":"));
		return adress;
	}

	@Override
	public int hashCode() {
		return this.getIp().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this.hashCode() == o.hashCode();
	}
}
