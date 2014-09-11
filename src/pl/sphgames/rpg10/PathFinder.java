package pl.sphgames.rpg10;


import java.util.LinkedList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;



public class PathFinder {

	public static boolean world[][];
	private ArrayList closed = new ArrayList();	
	private SortedList open = new SortedList();
	private Node[][] nodes;
	public static Checker checker;
	private int mapX, mapY;
	public float getCost(int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;

		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		return result;
	}

	public void drawWorld2() {
		int hue[][] = new int[10][10];

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) 
				if (PathFinder.world[i][j] == true)
					hue[i][j] = 0;
				else
					hue[i][j] = 1;

		int z = 0;
		boolean done = false;
		for (int i = 0; i < 11; i++) {


			System.out.printf(z + " ");
			z++;
			if (!done) {
				z--;
				done = true;
			}


		}
		System.out.printf("\n");
		for (int i = 0; i < 10; i++){

			for (int j = 0; j < 10; j++) {


				if (j == 0)
					System.out.printf(i + " ");
				System.out.printf(hue[j][i] + " ");
			}
			System.out.printf("\n");
		}
	}

	public void drawWorld() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				if (world[j][i] == true)
					System.out.printf("0 ");
				else
					System.out.printf("1 ");
			}
			System.out.printf("\n");
		}
	}

	

	public class Node  {
	
		private int x;
		private int y;
		private float cost;
		private Node parent;
		private float heuristic;
		private int depth;

	
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}



	}





	public PathFinder() {
		mapX = World.getMapX();
		mapY = World.getMapY();
		world = World.getPathsArray();
		checker = new Checker();
		nodes = new Node[mapX][mapY];
		for (int x=0;x<mapX;x++) {
			for (int y=0;y<mapY;y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	

	}




	


	public Path findPath(int sx, int sy, int tx, int ty) {
		World.createPathsArray();
		world = World.getPathsArray();
		open.clear();
		closed.clear();
		nodes[sx][sy].depth = 0;
		nodes[sx][sy].parent = null;
		open.add(nodes[sx][sy]);
		
		if (sx == tx && sy == ty) {
			Path path1 = new Path();
			path1.createOneStepPath(sx,sy);
			return path1;
		}
		
		while (open.size() != 0) {
			Node current = getFirstInOpen();
			removeFromOpen(current);
			addToClosed(current);

			// PATH FOUND
			if (inOpenList(nodes[tx][ty])) {
				break;
			}

			for (int i = -1; i <= 1; i++)
				for (int j = -1; j<= 1; j++) {




					int newX, newY;
					newX = current.x + i;
					newY = current.y + j;	


					if ((newX == 0) && (newY == 0)) {
						continue;
					}

				


					if (!isValidLocation(newX,newY) || inClosedList(nodes[newX][newY])) 
						continue;



					if (!inOpenList(nodes[newX][newY])) {
						addToOpen(nodes[newX][newY]);
						nodes[newX][newY].parent = current;
	/* G SCORE */		nodes[newX][newY].depth = nodes[newX][newY].parent.depth + 1; 
	/* H SCORE */		nodes[newX][newY].heuristic = getCost(newX, newY, tx, ty);
	/* F SCORE */		nodes[newX][newY].cost = nodes[newX][newY].depth + nodes[newX][newY].heuristic;


					}

					if (inOpenList(nodes[newX][newY])) {
						if (nodes[newX][newY].depth < current.depth) {
							nodes[newX][newY].parent = current;
	/* G SCORE */			nodes[newX][newY].depth = nodes[newX][newY].parent.depth + 1; 
	/* H SCORE */			nodes[newX][newY].heuristic = getCost(newX, newY, tx, ty);
	/* F SCORE */			nodes[newX][newY].cost = nodes[newX][newY].depth + nodes[newX][newY].heuristic;
							open.sort();
						}



					}



					//

				}			
		}

		if (nodes[tx][ty].parent == null) {	
			System.out.printf("\nnie ma xD\n");
			return null;
		}
		
		Path path = new Path();
		Node target = nodes[tx][ty];
		while (target != nodes[sx][sy]) {
			path.prependStep(target.x, target.y);
			target = target.parent;
		}
		//path.prependStep(sx,sy);
		return path;




	}



	public Node getFirstInOpen() {
		return (Node) open.first();
	}


	public void addToOpen(Node node) {
		open.add(node);
	}

	public boolean inOpenList(Node node) {
		return open.contains(node);
	}


	public void removeFromOpen(Node node) {
		open.remove(node);
	}


	public void addToClosed(Node node) {
		closed.add(node);
	}


	public boolean inClosedList(Node node) {
		return closed.contains(node);
	}


	public void removeFromClosed(Node node) {
		closed.remove(node);
	}




	protected boolean isValidLocation(int x, int y) {
		if (x < 0 || y < 0 || x >= mapX || y >= mapY) {
			return false;
		}
		if (world[x][y] == false) {
			return false;
		}
		return true;
	}




	private class Checker implements Comparator<Node> {
		@Override
		public int compare(Node o1_, Node o2_) {
			
			float co1 = o1_.cost;
			float co2 = o2_.cost;

			if (co1 < co2)
				return -1;
			else if (co1 > co2)
				return 1;
			else
				return 0;


		}

	}



	private class SortedList {
		private ArrayList<Node> list = new ArrayList();

		public Node first() {
			return list.get(0);
		}

		public void clear() {
			list.clear();
		}

		public Node get(int index) {
			return list.get(index);
		}

		public void add(Node o) {
			list.add(o);
			Collections.sort(list, PathFinder.checker);
		}

		public void sort() {
			Collections.sort(list, new Checker());
		}

		public void remove(Node o) {
			list.remove(o);
		}

		public int size() {
			return list.size();
		}

		public boolean contains(Node o) {
			return list.contains(o);
		}
	}





}
