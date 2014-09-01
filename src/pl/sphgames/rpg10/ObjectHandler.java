package pl.sphgames.rpg10;

import java.util.ArrayList;

public class ObjectHandler {
	
	private static ArrayList<Object> objectsList;
	private ObjectEncoder objectEncoder_;
	
		
	public ObjectHandler() { 
		objectsList = new ArrayList<Object>();
		objectEncoder_ = new ObjectEncoder();
	}
	
	public static ArrayList<Object> getList() {
		return objectsList;
	}
	
	
	
	public static void createNewObject(Object newOne) {
		objectsList.add(newOne);
	}
	
	public void clearArray() {
		objectsList.clear();
	}
	
		
}
