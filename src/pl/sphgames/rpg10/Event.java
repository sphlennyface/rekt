package pl.sphgames.rpg10;

public class Event {
	
	private EVENTTYPE eventType;
	private boolean working;
	private int x, y;
	private String stringHelper;
	private int eventHelper;
	

	
	public Event(int x, int y, EVENTTYPE eventType_, String stringHelper_) {
		working = true;
		stringHelper = stringHelper_;
		eventType = eventType_;
		putInArray(x,y);
		
		}
	
	public Event(int x, int y, EVENTTYPE eventType_, int eventHelper_) {
		working = true;
		eventHelper = eventHelper_;
		eventType = eventType_;
		putInArray(x,y);
		}
	
	private void putInArray(int x, int y) {
	
		World.background[x][y].putEvent(this);
	}
	
	public void handleEvent() {
		if (working) {		
			switch (eventType) {
			case PRINTTEXT:
				
			break;
			case SHOWHINT:
				
			break;
			case PLAYSOUND:
				
			break;
			
			default:
			break;
			}
			
			working = false;
		}
	}
	
	
}
