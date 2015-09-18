package com.oocl.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController {

	private IGameView view;
	private GameRole pcGameRole;
	private List<GameRole> userRolelist;
	private Random random;
	
	
	public GameController(IGameView view, GameRole[] userRolelist, GameRole pcGameRole) {
		setView(view);
		setRolelist(userRolelist);
		setPcGameRole(pcGameRole);
	}

	
	public GameController(IGameView view, List<GameRole> userRolelist, GameRole pcGameRole) {
		setView(view);
		setRolelist(userRolelist);
		setPcGameRole(pcGameRole);
	}


	public IGameView getView() { return view; }
	public GameRole[] getRolelist() { return (GameRole[]) userRolelist.toArray(); }
	public GameRole getPcGameRole() { return pcGameRole; }

	public void setPcGameRole(GameRole pcGameRole) {
		if (pcGameRole == null)
			throw new IllegalArgumentException("Null PC GameRole.");

		this.pcGameRole = pcGameRole;
	}
	
	public void setView(IGameView view) {
		if (view == null)
			throw new IllegalArgumentException("Null view.");

		this.view = view;
	}


	public void setRolelist(GameRole[] userRolelist) {
		if (userRolelist == null)
			throw new IllegalArgumentException("Null rolelist");
		
		this.setRolelist(Arrays.asList(userRolelist));
	}


	public void setRolelist(List<GameRole> userRolelist) {
		if (userRolelist == null || userRolelist.isEmpty())
			throw new IllegalArgumentException("Null or empty role list.");
		
		this.userRolelist = new ArrayList<GameRole>(userRolelist);
	}


	public void startGameLoop(GameSessionData sessionData) {

		// display introduction
		view.displayGameIntro();

		// initialize session data
		sessionData.resetWinCnt();

		// initialize user role after getting user's choice
		GameRole userRole = null;

		int userRoleChoice = view.getUserRoleChoice(userRolelist);
		for (GameRole role : userRolelist) {
			if (userRoleChoice == role.getRoleId()) {
				userRole = role;
				break;
			}
		}
		
		sessionData.setRole((userRole==null? new GameRole("Default", Integer.MAX_VALUE, new UserGameAction()) : userRole));


		//game loop: allow user to replay
		do {
	
			HandShape pc_shape, user_shape;

			//get PC's HandShape
			pc_shape = pcGameRole.guess(view);

			//assume user enter the corrent ID
			user_shape = userRole.guess(view);
			
			//calculate result
			int roundResult = getRoundResult(pc_shape, user_shape);
			if (roundResult > 0)
				sessionData.incrementWinCnt();
			
			//display this round's result
			view.displayRoundResult(pc_shape, user_shape, roundResult);
			
		} while(view.askIfUserWantsToReplay());


		// display win count and other info.
		view.displayFinalResult(sessionData);
	}


	// logic for rock-scissor-paper game
	// return:
	//		> 0 : user wins
	//		= 0 : draw
	//		< 0 : pc wins
	private int getRoundResult(HandShape pc_shape, HandShape user_shape) {

		// draw
		if (pc_shape.equals(user_shape)) return 0;
		
		// user wins
		if (user_shape.equals(HandShape.PAPER)    && pc_shape.equals(HandShape.ROCK)) return 1;
		if (user_shape.equals(HandShape.SCISSORS) && pc_shape.equals(HandShape.PAPER)) return 1;
		if (user_shape.equals(HandShape.ROCK)     && pc_shape.equals(HandShape.SCISSORS)) return 1;
		
		// pc wins 
		return -1;
	}
}
