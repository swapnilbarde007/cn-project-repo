package com.tictac.trial;

public class Player {
	
	String playerName;
	char playerSymbol;
	
	public Player() {
		
	}
	public Player(String playerName,char playerSymbol) {
		this.playerName=playerName;
		this.playerSymbol=playerSymbol;
	}

	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		if(!playerName.isEmpty()) {
			this.playerName = playerName;	
		}
	}
	public char getPlayerSymbol() {
		return playerSymbol;
	}
	public void setPlayerSymbol(char playerSymbol) {
		if(playerSymbol!='\0') {
			this.playerSymbol = playerSymbol;
		}
	}
}
