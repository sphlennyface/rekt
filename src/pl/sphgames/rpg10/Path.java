package pl.sphgames.rpg10;

import pl.sphgames.rpg10.PathFinder;

import java.util.ArrayList;


/**
 * A path determined by some path finding algorithm. A series of steps from
 * the starting location to the target location. This includes a step for the
 * initial location.
 * 
 * @author Kevin Glass
 */
public class Path {
	/** The list of steps building up this path */
	private static ArrayList<Step> steps = new ArrayList();
	
	/**
	 * Create an empty path
	 */
	public Path() {
		
	}
	
	public int getNextTileX() {
		return steps.get(0).x;
	}
	
	public int getNextTileY() {
		return steps.get(0).y;
	}
	
	
	public void drawWorld() {
		int hue[][] = new int[16][13];
		
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 13; j++) 
				if (PathFinder.world[i][j] == true)
					hue[i][j] = 0;
				else
					hue[i][j] = 1;
		
		for (int z = 0; z < steps.size(); z++)
				{
					hue[steps.get(z).x][steps.get(z).y] = 2;
				}
		int z = 0;
		boolean done = false;
		for (int i = 0; i < 16; i++) {
			
				
				System.out.printf(z + " ");
				z++;
				if (!done) {
					z--;
					done = true;
				}
				
			
		}
		System.out.printf("\n");
		for (int i = 0; i < 16; i++){
			
			for (int j = 0; j < 12; j++) {
				
				
				if (j == 0)
					System.out.printf(i + " ");
				System.out.printf(hue[j][i] + " ");
			}
			System.out.printf("\n");
		}
	}
	
	public int getFirstX() {
		return steps.get(0).x;
	}
	
	public int getFirstY() {
		return steps.get(0).y;
	}
	
	public static void clearPath() {
		
			steps.clear();
			
	}
	
	public static void printPath() {
		int i = 0;
		for (i = 0; i < steps.size(); i++) {
				System.out.printf("STEP NUMBER " + i + ":");
				steps.get(i).printInfo();
			}
	
	}
	/**
	 * Get the length of the path, i.e. the number of steps
	 * 
	 * @return The number of steps in this path
	 */
	public int getLength() {
		return steps.size();
	}
	
	/**
	 * Get the step at a given index in the path
	 * 
	 * @param index The index of the step to retrieve. Note this should
	 * be >= 0 and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}
	
	/**
	 * Get the x coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getX(int index) {
		return getStep(index).x;
	}

	/**
	 * Get the y coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getY(int index) {
		return getStep(index).y;
	}
	
	/**
	 * Append a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x,y));
	}

	/**
	 * Prepend a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}
	
	
	public void createOneStepPath(int x, int y) {
		steps.clear();
		steps.add(new Step(x,y));
	}
	/**
	 * Check if this path contains the given step
	 * 
	 * @param x The x coordinate of the step to check for
	 * @param y The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x,y));
	}
	
	/**
	 * A single step within the path
	 * 
	 * @author Kevin Glass
	 */
	public class Step {
		/** The x coordinate at the given step */
		private int x;
		/** The y coordinate at the given step */
		private int y;
		
		/**
		 * Create a new step
		 * 
		 * @param x The x coordinate of the new step
		 * @param y The y coordinate of the new step
		 */
		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void printInfo() {
			System.out.printf(" X : " + this.x + " Y : " + this.y + "\n");
		}
		
		/**
		 * Get the x coordinate of the new step
		 * 
		 * @return The x coodindate of the new step
		 */
		public int getX() {
			return x;
		}

		/**
		 * Get the y coordinate of the new step
		 * 
		 * @return The y coodindate of the new step
		 */
		public int getY() {
			return y;
		}
		
		/**
		 * @see Object#hashCode()
		 */
		public int hashCode() {
			return x*y;
		}
		

		/**
		 * @see Object#equals(Object)
		 */
		
	}
}