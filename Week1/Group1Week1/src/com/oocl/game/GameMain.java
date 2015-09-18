package com.oocl.game;

import java.util.ArrayList;
import java.util.List;


public class GameMain {

	private static final String GAME_ROLE_LOU_P = LanguageProperties.getValue("character-LAU").toString();
	private static final String GAME_ROLE_SUN_Q = LanguageProperties.getValue("character-SUEN").toString();
	private static final String GAME_ROLE_CIAO_C = LanguageProperties.getValue("character-CHO").toString();
	private static final String GAME_ROLE_COMPUTER = LanguageProperties.getValue("computer").toString();
	
	public static void main(String[] args) {
		
		//initialize choices for user role
		final IGameAction userGameAction = new UserGameAction();

		List<GameRole> userRolelist = new ArrayList<GameRole>() {{
			add(new GameRole(GAME_ROLE_LOU_P, 1, userGameAction));
			add(new GameRole(GAME_ROLE_SUN_Q, 2, userGameAction));
			add(new GameRole(GAME_ROLE_CIAO_C, 3, userGameAction));
		}};


		//initialize pc role
		GameRole pcGameRole = new GameRole(
									GAME_ROLE_COMPUTER,
									Integer.MAX_VALUE,
									new PcGameAction()
								);

		
		//get view singleton instance
		IGameView view = ConsoleGameView.getInstance();
		
		//initialize controller
		GameController controller = new GameController(view, userRolelist, pcGameRole);
		
		//start game loop
		controller.startGameLoop(new GameSessionData());
	}
}
