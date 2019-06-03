package com.unplayable.Networking.Connection;

import com.unplayable.Gui.Score;
import com.unplayable.Networking.ScoreNetworkCallback;
import com.unplayable.Networking.StringNetworkCallback;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DataStreamConnection implements ConnectionBackEnd {
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	private DataOutputStream getDataOutputStream() throws IOException {
		if (this.dataOutputStream == null){
			this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
		}
		return this.dataOutputStream;
	}

	private DataInputStream getDataInputStream() throws IOException {
		if (this.dataInputStream == null){
			this.dataInputStream = new DataInputStream(this.socket.getInputStream());
		}
		return this.dataInputStream;
	}

	public DataStreamConnection(Socket socket){
		this.socket = socket;
	}

	@Override
	public void writeScore(Score score) {

	}

	@Override
	public void writeString(String string) throws IOException {
		DataOutputStream outputStream = this.getDataOutputStream();
		outputStream.writeUTF(string);
		outputStream.flush();
	}

	private String lastReadString = "";

	@Override
	public String readString() {
		this.lastReadString = null;
		try {
			this.lastReadString = getDataInputStream().readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastReadString;
	}

	@Override
	public Score readScore() {
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
