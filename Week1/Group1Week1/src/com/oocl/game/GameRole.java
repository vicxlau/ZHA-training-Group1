package com.oocl.game;

public class GameRole {
	
	private String rolename;
	private Integer roleId;
	private IGameAction gameAction;
	
	public GameRole(String rolename, int roleId, IGameAction gameAction) {
		setRolename(rolename);
		setRoleId(roleId);
		setGameAction(gameAction);
	}
	
	public String getRolename() { return rolename; }
	public int getRoleId() { return roleId; }


	public void setRolename(String rolename) {
		if (rolename == null || rolename.isEmpty())
			throw new IllegalArgumentException("Null or empty rolename.");
		
		this.rolename = rolename;
	}

	public void setRoleId(int roleId) {
		if (roleId < 1)
			throw new IllegalArgumentException("Role ID should be larger than zero.");
		
		this.roleId = roleId;
	}

	public IGameAction getGameAction() {
		return gameAction;
	}

	public void setGameAction(IGameAction gameAction) {
		if (gameAction == null)
			throw new IllegalArgumentException("Null game action.");
		
		this.gameAction = gameAction;
	}
	
	public HandShape guess(IGameView view) {
		return gameAction.guess(view);
	}
}
