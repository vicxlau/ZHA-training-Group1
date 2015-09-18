package com.oocl.game;

public enum HandShape {
	
	
	
	PAPER(1, LanguageProperties.getValue("paper")),
	SCISSORS(2, LanguageProperties.getValue("scissor")),
	ROCK(3, LanguageProperties.getValue("stone"));
	
	private Integer shapeId;
	private String displayName;

	//search HandShape instance by shapeId
	//return null if no instance found
	public static HandShape getHandShapeById(int shapeId) {
		
		for (HandShape shape : HandShape.values()) {
			if (shape.shapeId == shapeId)
				return shape;
		}
		
		return null;
	}
	
	private HandShape(int shapeId, String displayName) {
		setShapeId(shapeId);
		setDisplayName(displayName);
	}
	
	private void setShapeId(int shapeId) {
		if (shapeId < 0)
			throw new IllegalArgumentException("Negative shapeId.");
		
		this.shapeId = shapeId;
	}
	
	public void setDisplayName(String displayName) {
		if (displayName == null)
			throw new IllegalArgumentException("Null display name.");

		this.displayName = displayName;
	}
	
	
	public int getShapeId() { return shapeId; }
	
	public String getDisplayName() { return displayName; }
}
