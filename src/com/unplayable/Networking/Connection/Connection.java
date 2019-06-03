package com.unplayable.Networking.Connection;

import com.unplayable.Gui.Score;
import com.unplayable.Networking.ScoreNetworkCallback;
import com.unplayable.Networking.StringNetworkCallback;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements ConnectionBackEnd {
	private ConnectionBackEnd streamBackEnd;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public Connection(Socket socket) throws IOException {
		//this.streamBackEnd = new ObjectStreamConnection(socket);
		this.streamBackEnd = new DataStreamConnection(socket);
	}

	public String getAdress(){
		String adress = this.streamBackEnd.getSocket().getRemoteSocketAddress().toString();
		return adress.replace("/", "");
	}

	public String getIp(){
		String adress = this.getAdress();
		adress = adress.substring(0, adress.indexOf(":"));
		return adress;
	}

	@Override
	public void writeScore(Score score) {
		this.streamBackEnd.writeScore(score);
	}

	@Override
	public void writeString(String string) {
		this.streamBackEnd.writeString(string);
	}

	@Override
	public String readString() {
		return this.streamBackEnd.readString();
	}

	@Override
	public Score readScore() {
		return this.streamBackEnd.readScore();
	}

	@Override
	public void readStringAsync(StringNetworkCallback callback) {
		this.streamBackEnd.readStringAsync(callback);
	}

	@Override
	public void readScoreAsync(ScoreNetworkCallback callback) {
		this.streamBackEnd.readScoreAsync(callback);
	}

	@Override
	public Socket getSocket() {
		return streamBackEnd.getSocket();
	}
}