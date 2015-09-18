package com.oocl.game;

import java.util.List;

public interface IGameView {

	void displayGameIntro();
	void displayRoundResult(HandShape pc_shape, HandShape user_shape, int roundResult);
	void displayFinalResult(GameSessionData sessionData);
	int getUserRoleChoice(List<GameRole> rolelist);
	int getUserHandShape();
	boolean askIfUserWantsToReplay();
}
