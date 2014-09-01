package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;

public class Terrain {

	private BufferedImage texture_;
	private int movementCost_;
	private boolean isWalkable_;
	private boolean isPassable_;
	private int actionHelper_;
	private TerrainType type_;
	private Action action_;
	private Event event_;
	
	
	
	
	public Terrain(int movementCost, boolean isWalkable, boolean isPassable, Action action, int actionHelper, TerrainType type,BufferedImage texture) {
		isWalkable_ = isWalkable;
		movementCost_ = movementCost;
		type_ = type;
		isPassable_ = isPassable;
		action_ = action;
		actionHelper_ = actionHelper;
		texture_ = texture;
		
	}
	
	public boolean hasEvent() {
		if (event_ != null)
			return true;
		return false;
	}
	
	public Event getEvent() {
		return event_;
	}
	
	public void putEvent(Event event) {
		event_ = event;
	}
	
	public int getAction() {
		if (this.action_ == Action.NONE) {
			return 0;
		}
		else if (this.action_ == Action.CHANGELEVEL) {
			return 1;
		}
		return 0;
	}
	
	public Terrain getTerrain() {
		return this;
	}
	
	public int getActionHelper() {
		return actionHelper_;
	}
	
	public void setTexture(BufferedImage texture) {
		texture_ = texture;
	}
	
	public void setUnpassable() {
		this.isPassable_ = false;
		this.isWalkable_ = false;
	}
	
		
	public BufferedImage getTexture() {
		return texture_;
	}

	public boolean isWalkable() {
		if (this.isWalkable_)
				return true;
		return false;
	}
	
	
	
	public boolean isPassable() {
		if (this.isPassable_)
				return true;
		return false;
	}
	
	
	
	
}
