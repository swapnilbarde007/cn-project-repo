package com.tictac.trial;

import java.util.Scanner;

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
	
//	public Player getPlayerInfo(int playerCount) {
//		System.out.println("Enter Player "+playerCount+" Name:");
//		Scanner sc=new Scanner(System.in);
//		String name=sc.next();
//		Player player=new Player();
//		player.setPlayerName(name);
//		
//		System.out.println("Enter Player "+playerCount+" Symbol:");
//		char syb=sc.next().charAt(0);
//		player.setPlayerSymbol(syb);
//		
//		return player;
//	}
}
