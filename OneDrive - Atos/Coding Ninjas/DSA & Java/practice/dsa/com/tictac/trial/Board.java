package com.tictac.trial;

public class Board {
	Player boardMatrix[][];
	int rows;
	int columns;
	int x[][];
	static int filledCount=0;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		boardMatrix = new Player[rows][columns];
		for(int i=0;i<boardMatrix.length;i++) {
			for(int j=0;j<boardMatrix.length;j++) {
				boardMatrix[i][j]=new Player();
			}
		}
	}
	
	public boolean addMove(Player p,int row,int col) throws InvalidMoveException{
		if(row>(this.rows-1)||col>(this.columns-1)) {
			throw new InvalidMoveException();
		}
		if(boardMatrix[row][col].getPlayerName()==null) {
			filledCount++;
		}
		boardMatrix[row][col]=p;
		printBoard();
		Player wonPlayer=checkLineComplete();
		if(wonPlayer!=null) {
			System.out.println("Player "+wonPlayer.getPlayerName()+" Won!!!");
			return true;
		}
		return false;
	}
	
	/*
	 * 0 0  0 1  0 2
	 * 1 0  1 1  1 2
	 * 2 0  2 1  2 2
	 */
	
	
	
	public Player checkLineComplete() {
		// Row Check
		for(int i=0;i<rows;i++) {
			boolean same=true;
			char firstSym=(boardMatrix[i][0]).getPlayerSymbol();
			if(firstSym!='\0') {
				for(int j=1;j<columns;j++) {
					if(boardMatrix[i][j].getPlayerSymbol()!=firstSym) {
						same=false;
						break;
					}
				}
				if(same) {
					return boardMatrix[i][0];
				}
			}
			
		}
		
		//Column Check
		for(int i=0;i<columns;i++) {
			boolean same=true;
			char firstSym=(boardMatrix[0][i]).getPlayerSymbol();
			if(firstSym!='\0') {
				for(int j=1;j<rows;j++) {
					if(boardMatrix[j][i].getPlayerSymbol()!=firstSym) {
						same=false;
						break;
					}
				}
				if(same) {
					return boardMatrix[0][i];
				}
			}
		}
		
		//Digonal Check (L-R)
		boolean same=true;
		char firstSym=(boardMatrix[0][0]).getPlayerSymbol();
		if(firstSym!='\0') {
			for(int i=1;i<rows;i++) {
				int j=boardMatrix.length-(boardMatrix.length-i);
				if(boardMatrix[i][j].getPlayerSymbol()!=firstSym) {
					same=false;
					break;
				}
			}
			if(same) {
				return boardMatrix[0][0];
			}
		}
		
		//Digonal Check (R-L)
		same=true;
		firstSym=(boardMatrix[0][columns-1]).getPlayerSymbol();
		if(firstSym!='\0') {
			for(int i=1;i<rows;i++) {
				int j=(boardMatrix.length-i)-1;
				if(boardMatrix[i][j].getPlayerSymbol()!=firstSym) {
					same=false;
					break;
				}
			}
			if(same) {
				return boardMatrix[0][0];
			}
		}
		return null;
	}
	
	public void printBoard() {
		for(int i=0;i<boardMatrix.length;i++) {
			for(int j=0;j<boardMatrix.length;j++) {
				System.out.print(boardMatrix[i][j].getPlayerSymbol());
				System.out.print("||");
			}
			System.out.println();
		}
	}
	
	
}
