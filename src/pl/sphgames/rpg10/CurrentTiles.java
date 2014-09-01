package pl.sphgames.rpg10;

public class CurrentTiles {

	private Terrain tile1_, tile2_, tile3_, tile4_;
	private int action, actionHelper;
	private Event event_;
	
	public CurrentTiles() {};
	
	public CurrentTiles(Terrain tile1) {
		tile1_ = tile1;
		
		
	}
	
	public CurrentTiles(Terrain tile1, Terrain tile2) {
		tile1_ = tile1;
		tile2_ = tile2;
	}
	
	public CurrentTiles(Terrain tile1, Terrain tile2, Terrain tile3, Terrain tile4) {
		tile1_ = tile1;
		tile2_ = tile2;
		tile3_ = tile3;
		tile4_ = tile4;
	}
	
	public Event getEvent() {
		return event_;
	}
	
	public boolean areEventTiles() {
		if (tile1_ != null) {
			if (tile1_.hasEvent()) {
				event_ = tile1_.getEvent();
				return true;
			}
		}
		if (tile2_ != null) {
			if (tile2_.hasEvent()) {
				event_ = tile2_.getEvent();
				return true;
			}
		}
		if (tile3_ != null) {
			if (tile3_.hasEvent()) {
				event_ = tile3_.getEvent();
				return true;
			}
		}
		if (tile4_ != null) {
			if (tile4_.hasEvent()) {
				event_ = tile4_.getEvent();
				return true;
			}
		}		
		
		return false;
	}
	
	
	
	public boolean areSwitchLevelTiles() {
		if (tile1_ != null) {
			if (tile1_.getAction() == 1) {
				return true;
			}
		}
		if (tile2_ != null) {
			if (tile2_.getAction() == 1) {
				return true;
			}
		}
		if (tile3_ != null) {
			if (tile3_.getAction() == 1) {
				return true;
			}
		}
		if (tile4_ != null) {
			if (tile4_.getAction() == 1) {
				return true;
			}
		}		
		
		return false;
	}
	
	
	public boolean areWalkableTiles() {
		if (tile1_ != null) {
			if (tile2_ != null) {
				if (tile3_ != null) {
					if (tile1_.isWalkable() && tile2_.isWalkable() && tile3_.isWalkable() && tile4_.isWalkable() )
						return true;
					return false;
				}
				else {
					if (tile1_.isWalkable() && tile2_.isWalkable())
						return true;
					return false;					
				}
			}
			else {
				if (tile1_.isWalkable())
					return true;
				return false;				
			}
			
		}
		return false;
	}
	
	
	public int getActionHelper() {
		if (tile1_ != null && tile1_.getAction() == 1) 
			return tile1_.getActionHelper();		
		if (tile2_ != null && tile2_.getAction() == 1)
			return tile2_.getActionHelper();
		if (tile3_ != null && tile3_.getAction() == 1)
			return tile3_.getActionHelper();		
		if (tile4_ != null && tile4_.getAction() == 1) 
			return tile4_.getActionHelper();
		return -1;
	}
}
