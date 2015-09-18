package com.oocl.game;

import java.util.Random;

public class PcGameAction implements IGameAction {

	private Random random = new Random();
	
	@Override
	public HandShape guess(IGameView view) {
		return HandShape.getHandShapeById(
				random.nextInt(HandShape.values().length) + 1
			);
	}

}