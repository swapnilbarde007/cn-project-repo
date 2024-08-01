package com.tictac.trial;

import java.util.Scanner;

public class TicTacToe {
	Player player1,player2;
	int rows,cols;

	public static void main(String[] args) {
		TicTacToe game=new TicTacToe();
		game.initGame();
		Board b1=new Board(3, 3);
		game.runGame(b1);
	}

	public Player getPlayerInfo(int playerCount) {
		System.out.println("Enter Player "+playerCount+" Name:");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		Player player=new Player();
		player.setPlayerName(name);
		
		System.out.println("Enter Player "+playerCount+" Symbol:");
		char syb=sc.next().charAt(0);
		player.setPlayerSymbol(syb);
		
		return player;
	}
	
	public void initGame() {
		rows=3;
		cols=3;
		
		player1=getPlayerInfo(1);
		player2=getPlayerInfo(2);
		
		while(player1.getPlayerSymbol()==player2.getPlayerSymbol()) {
			player2=getPlayerInfo(2);
		}
		
		//sc.close();
	}
	public void runGame(Board b1) {
		int moveCounter=0;
		int maxMoves=this.rows*this.cols;
		Scanner sc1=new Scanner(System.in);
		boolean won=false;
		while(b1.filledCount<maxMoves) {
			if((moveCounter%2)==0) {
				System.out.println("Enter move for "+player1.getPlayerName()+": ");
				String moveInput=sc1.nextLine();
				try {
					won=b1.addMove(player1, moveInput.charAt(0)-'0', moveInput.charAt(2)-'0');
					if(won) {
						break;
					}
					moveCounter++;
				} catch (InvalidMoveException e) {
					System.out.println("Invalid Move (Row/Column");
				}
				
			}else {
				System.out.println("Enter move for "+player2.getPlayerName()+": ");
				String moveInput=sc1.nextLine();
				try {
					won=b1.addMove(player2, moveInput.charAt(0)-'0', moveInput.charAt(2)-'0');
					if(won) {
						break;
					}
					moveCounter++;
				} catch (InvalidMoveException e) {
					System.out.println("Invalid Move (Row/Column");
				}
			}
			
		}
		if(won==false) {
			System.out.println("Match draw");
		}
		sc1.close();
	}
	
	
}
