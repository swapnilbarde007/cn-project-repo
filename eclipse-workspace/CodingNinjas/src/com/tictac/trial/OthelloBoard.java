package com.tictac.trial;

public class OthelloBoard {

	private int board[][];
	final static int player1Symbol = 1;
	final static int player2Symbol = 2;

	public OthelloBoard() {
		board = new int[8][8];
		board[3][3] = player1Symbol;
		board[3][4] = player2Symbol;
		board[4][3] = player2Symbol;
		board[4][4] = player1Symbol;
	}

	public void print() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean move(int symbol, int x, int y){
		int otherSym;
		boolean moveValid=false;
		otherSym = (symbol == player1Symbol) ? player2Symbol : player1Symbol;
		//Check Move is valid
		//	Check adjacent has opposite symbol
			//Left
			if(y-1>=0 && board[x][y-1]==otherSym){
				//Move in left direction till you find same symbol
				boolean contin=true;
				int lastFound=y-1;
				for(int i=y-1;i>=0&&contin==true;i--) {
					if(board[x][i]!=otherSym) {
						contin=false;
					}else {
						lastFound--;	
					}
					
				}
				if(lastFound!=y-1) {
					for(int i=y-1;i>=lastFound;i--) {
						board[x][i]=symbol;
					}
				}
				board[x][y]=symbol;
				moveValid=true;
			}
			
			//Right
			if(y+1<8 && board[x][y+1]==otherSym){
				//Move in left direction till you find same symbol
				boolean contin=true;
				int lastFound=y+1;
				for(int i=y+1;i<8&&contin==true;i++) {
					if(board[x][i]!=otherSym) {
						contin=false;
					}else {
						lastFound++;	
					}
					
				}
				if(lastFound!=y+1) {
					for(int i=y+1;i<=lastFound;i++) {
						board[x][i]=symbol;
					}
				}
				board[x][y]=symbol;
				moveValid=true;
			}
			//Up
			if(x-1>=0 && board[x-1][y]==otherSym){
				//Move in Up direction till you find same symbol
				boolean contin=true;
				int lastFound=x-1;
				for(int i=x-1;i>=0&&contin==true;i--) {
					if(board[x][i]!=otherSym) {
						contin=false;
					}else {
						lastFound--;	
					}
					
				}
				if(lastFound!=x-1) {
					for(int i=x-1;i>=lastFound;i--) {
						board[x][i]=symbol;
					}
				}
				board[x][y]=symbol;
				moveValid=true;
			}
			//Down
			if(x+1<8 && board[x+1][y]==otherSym){
				boolean contin=true;
				int lastFound=x+1;
				for(int i=x+1;i<8&&contin==true;i++) {
					if(board[i][y]!=otherSym) {
						contin=false;
					}else {
						lastFound++;	
					}
					
				}
				if(lastFound!=x+1) {
					for(int i=x+1;i<lastFound;i++) {
						board[i][y]=symbol;
					}
				}
				board[x][y]=symbol;
				moveValid=true;
			}			
		
			//Yes
				//In which direction?
				//Up
					//Proceed incrementing and checking symbol should be opposite
					//As soon as same symbol is seen
					//change all checked symbols till now to same.
				//Down
		
				//Left
		
				//Right
				
			//No
				//Invalid move
			return moveValid;
	}
}

