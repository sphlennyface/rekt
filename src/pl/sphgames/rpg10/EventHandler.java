package pl.sphgames.rpg10;

public class EventHandler {
	private Player player_;
	private Event event_;
	
	public EventHandler() {
		
	}
	
	public void passPlayer (Player player) {
		player_ = player;
	}
	
	public void handleEvents() {
		if (player_.isOnEventTile(player_.x,player_.y)) {
			event_ = player_.getEvent();
			event_.handleEvent();
		}
	}
	
	
}
