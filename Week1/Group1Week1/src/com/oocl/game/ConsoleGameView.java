package com.oocl.game;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ConsoleGameView implements IGameView {

	private static final String WELLCOME = "---------------------- " + LanguageProperties.getValue("welcome").toString() + " ----------------------";
	private static final String GAME_NAME = "          ******* " +LanguageProperties.getValue("stone").toString()+"-" + LanguageProperties.getValue("scissor").toString()+"-"+LanguageProperties.getValue("paper").toString()+" *******";
	private static final String YOU = LanguageProperties.getValue("you").toString();
	private static final String PC = LanguageProperties.getValue("computer").toString();
	private static final String MSG_YOU_WIN_THIS_ROUND = LanguageProperties.getValue("win-msg").toString();
	private static final String MSG_DRAW = LanguageProperties.getValue("draw-msg").toString();
	private static final String MSG_PC_WIN_THIS_ROUND = LanguageProperties.getValue("lose-msg").toString();
	private static final String YOUR_ROLE = "";//Your role
	private static final String YOU_HAVE_WON = LanguageProperties.getValue("final-win-msg").toString();;
	private static final String TIMES = "times";
	private static final String CHOOSE_UR_ROLE =  LanguageProperties.getValue("choose-character").toString();
	private static final String OTHERS = "";
	private static final String CHOOSE_UR_SHAPE = LanguageProperties.getValue("enter-input").toString();
	private static final String CONTINUE = LanguageProperties.getValue("continue").toString();;
	

	private Scanner inputScanner = new Scanner(System.in);
	private PrintStream outStream = System.out;
	
	// lazy initialization
	private static ConsoleGameView SINGLE_VIEW = null;
	
	private ConsoleGameView() {}
	
	public static ConsoleGameView getInstance() {
		if (SINGLE_VIEW == null)
			SINGLE_VIEW = new ConsoleGameView();
		
		return SINGLE_VIEW;
	}
	
	
	@Override
	public void displayGameIntro() {
		
		outStream.println(WELLCOME);
		outStream.println();
		outStream.println(GAME_NAME);
		outStream.println();
	}

	@Override
	public void displayRoundResult(HandShape pc_shape, HandShape user_shape, int roundResult) {
		
		outStream.println("");
		outStream.println(YOU + ": " + user_shape.getDisplayName());
		outStream.println(PC + ":  " + pc_shape.getDisplayName());
		
		if (roundResult > 0)
			outStream.println(MSG_YOU_WIN_THIS_ROUND);
		
		else if (roundResult == 0)
			outStream.println(MSG_DRAW);
		
		else
			outStream.println(MSG_PC_WIN_THIS_ROUND);
	}
	
	@Override
	public void displayFinalResult(GameSessionData sessionData) {

		if (sessionData == null)
			throw new IllegalArgumentException("Null session Data.");

		outStream.println("");
		outStream.println(YOUR_ROLE + "" + sessionData.getRole().getRolename());
		outStream.println(YOU_HAVE_WON  + sessionData.getWinCnt()); //TIMES
	}

	private int readUserIntegerInput() {
		return inputScanner.nextInt();
	}
	
	private char readUserCharacterInput() {
		return inputScanner.next().charAt(0);
	}
	
	
	@Override
	public int getUserRoleChoice(List<GameRole> rolelist) {
		
		outStream.println();
		outStream.print(CHOOSE_UR_ROLE + ": [");
		
		for (GameRole role : rolelist) {
			outStream.print(String.format(" %d:%s ",
						role.getRoleId(),
						role.getRolename()
					));
		}
		
		//outStream.println(OTHERS + ":Default]");
		outStream.println("]");
		return readUserIntegerInput();
	}

	@Override
	public int getUserHandShape() {
		
		outStream.println();
		outStream.print(CHOOSE_UR_SHAPE + ": [");
		
		for (HandShape shape : HandShape.values()) {
			outStream.print(String.format(" %d:%s ",
						shape.getShapeId(),
						shape.getDisplayName()
					));
		}
		
		outStream.println("]");
		
		return readUserIntegerInput();
	}

	@Override
	public boolean askIfUserWantsToReplay() {
		outStream.println();
		outStream.println(CONTINUE +  " [Y/N]");
		
		char userInput = readUserCharacterInput();
		
		outStream.println();
		
		if (userInput == 'Y' || userInput == 'y')
			return true;

		return false;
	}
}
