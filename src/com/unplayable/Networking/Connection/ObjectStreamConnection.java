package com.unplayable.Networking.Connection;

import com.unplayable.Gui.Score;
import com.unplayable.Networking.ScoreNetworkCallback;
import com.unplayable.Networking.StringNetworkCallback;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectStreamConnection implements ConnectionBackEnd {
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public ObjectStreamConnection(Socket socket){
		this.socket = socket;
	}

	private ObjectInputStream getObjectInputStream() throws IOException {
		if (this.objectInputStream == null){
			this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
		}
		return this.objectInputStream;
	}

	private ObjectOutputStream getObjectOutputStream() throws IOException {
		if (this.objectOutputStream == null){
			this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
		}
		return this.objectOutputStream;
	}

	@Override
	public void writeScore(Score score) {
		try {
			this.getObjectOutputStream().writeObject(score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeString(String string) throws IOException {
		this.getObjectOutputStream().writeObject(string);
	}

	private String lastReadString = "";

	@Override
	public String readString() {
		this.lastReadString = null;
		try {
			this.lastReadString = ((String)this.getObjectInputStream().readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.lastReadString;
	}

	@Override
	public Score readScore() {
		try {
			return ((Score)this.getObjectInputStream().readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void readStringAsync(StringNetworkCallback callback) {
		new Thread(()->{
			callback.callback(
				this.readString()
			);
		}).start();
	}

	@Override
	public void readScoreAsync(ScoreNetworkCallback callback) {
		new Thread(()->{
			callback.callback(
				this.readScore()
			);
		}).start();
	}

	@Override
	public Socket getSocket() {
		return this.socket;
	}
}
