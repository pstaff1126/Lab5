package pokerEnums;

public enum eGameState {
	StartOfGame(1),
	PlayOfGame(2),
	EndOfGame(3),
	DrawingCard(4);
	
	private int gameState;

	private eGameState(final int gameState){
		this.gameState = gameState;
	}
	
	public int getgameState(){
		return gameState;
	}
}
