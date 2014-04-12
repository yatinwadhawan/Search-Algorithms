//Data Structure to encapsulate the value
public class Node {
	int counter;
	private String name;
	private int cost;
	private int parent;

	public Node() {
		counter = 0;
		name = "";
		cost = 0;
		parent = 0;
	}

	public Node(int count, String n, int c, int p) {
		counter = count;
		name = n;
		cost = c;
		parent = p;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
