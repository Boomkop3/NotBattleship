package com.unplayable.Networking.Connection;

import com.unplayable.Gui.Score;
import com.unplayable.Networking.ScoreNetworkCallback;
import com.unplayable.Networking.StringNetworkCallback;

import java.net.Socket;

public class DataStreamConnection implements ConnectionBackEnd {

	private Socket socket;

	public DataStreamConnection(Socket socket){
		this.socket = socket;
	}

	@Override
	public void writeScore(Score score) {

	}

	@Override
	public void writeString(String string) {

	}

	@Override
	public String readString() {
		return null;
	}

	@Override
	public Score readScore() {
		return null;
	}

	@Override
	public void readStringAsync(StringNetworkCallback callback) {

	}

	@Override
	public void readScoreAsync(ScoreNetworkCallback callback) {

	}

	@Override
	public Socket getSocket() {
		return this.socket;
	}
}
