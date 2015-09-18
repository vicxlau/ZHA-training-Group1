package com.oocl.game;

public class GameSessionData {

	private GameRole userRole;
	private Integer winCnt;
	
	public GameSessionData() {
		setRole(new GameRole(
					"Default",
					Integer.MAX_VALUE,
					new PcGameAction()
				));

		resetWinCnt();
	}

	public GameSessionData(GameRole userRole) {
		setRole(userRole);
		resetWinCnt();
	}
	
	public GameRole getRole() {	return userRole; }
	public int getWinCnt() { return winCnt; }

	public void setRole(GameRole userRole) {
		if (userRole == null)
			throw new IllegalArgumentException("Null GameRole input argument.");
		
		this.userRole = userRole;
	}
	
	// reset win count to zero
	public void resetWinCnt() {
		this.winCnt = 0;
	}

	// return the win count after increment by one
	public int incrementWinCnt() {
		return ++this.winCnt;
	}
}