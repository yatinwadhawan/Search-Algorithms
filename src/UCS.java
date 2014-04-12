import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UCS {

	// Uniform Cost Search Algorithm
	public String uniformCostSearch(HashMap<String, List<Node>> adjacencyList,
			String startNode, String destinationNode) {
		int count = 1, parent = 0;
		String data = "";
		boolean goal = false;
		List<String> output = new ArrayList<String>();
		List<Node> discovered = new ArrayList<Node>();
		List<Node> priorityQueue = new ArrayList<Node>();
		Node start = new Node(count++, startNode, 0, parent++);
		priorityQueue.add(start);

		while (!priorityQueue.isEmpty() && !goal) {
			Node current = priorityQueue.remove(0);
			discovered.add(current);
			if (current.getName().compareTo(destinationNode) != 0) {
				List<Node> currentList = new ArrayList<Node>();
				currentList.addAll(adjacencyList.get(current.getName()));
				if (!currentList.isEmpty()) {
					for (int i = 0; i < currentList.size(); i++) {
						Node n = currentList.get(i);
						for (int j = 0; j < discovered.size(); j++) {
							if (discovered.get(j).getName()
									.compareTo(n.getName()) == 0) {
								currentList.remove(i);
								i--;
								break;
							}
						}
					}
					for (int i = 0; i < currentList.size(); i++) {
						Node n = currentList.get(i);
						n.setCounter(count++);
						n.setParent(current.getCounter());
						n.setCost(n.getCost() + current.getCost());
						priorityQueue.add(n);
					}
					// Sorting of the next layer
					for (int i = 0; i < priorityQueue.size(); i++) {
						for (int j = i; j < priorityQueue.size(); j++) {
							if (priorityQueue.get(i).getCost() > priorityQueue
									.get(j).getCost()) {
								Node y = new Node();
								y = priorityQueue.get(i);
								priorityQueue.set(i, priorityQueue.get(j));
								priorityQueue.set(j, y);
							} else if (priorityQueue.get(i).getCost() == priorityQueue
									.get(j).getCost()) {
								if (priorityQueue.get(i).getName().charAt(0) > priorityQueue
										.get(j).getName().charAt(0)) {
									Node y = new Node();
									y = priorityQueue.get(i);
									priorityQueue.set(i, priorityQueue.get(j));
									priorityQueue.set(j, y);
								}
							}
						}
					}
				}
			} else {
				goal = true;
				output.add(current.getName());
				parent = current.getParent();
				while (parent != 0) {
					for (int i = 0; i < discovered.size(); i++) {
						if (discovered.get(i).getCounter() == parent) {
							output.add(discovered.get(i).getName());
							parent = discovered.get(i).getParent();
							break;
						}
					}
				}
				goal = true;
				break;
			}
		}
		if (goal) {
			data = output.get(output.size() - 1);
			for (int i = output.size() - 2; i >= 0; i--) {
				data = data + "-" + output.get(i);
			}
		}
		return data;
	}

}
