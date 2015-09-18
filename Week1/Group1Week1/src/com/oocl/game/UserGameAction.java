package com.oocl.game;

public class UserGameAction implements IGameAction {

	@Override
	public HandShape guess(IGameView view) {
	
		return HandShape.getHandShapeById(
						view.getUserHandShape()
					);
	}

}
