package com.unplayable.Networking.Connection;

import com.unplayable.Gui.Score;
import com.unplayable.Networking.ScoreNetworkCallback;
import com.unplayable.Networking.StringNetworkCallback;

import java.io.IOException;
import java.net.Socket;

public interface ConnectionBackEnd {
	void writeScore(Score score);
	void writeString(String string) throws IOException;
	String readString();
	Score readScore();
	void readStringAsync(StringNetworkCallback callback);
	void readScoreAsync(ScoreNetworkCallback callback);
	Socket getSocket();
}
