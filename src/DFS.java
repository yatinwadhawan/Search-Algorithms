import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DFS {

	// Depth First Search Algorithm
	public String depthFirstSearch(HashMap<String, List<Node>> adjacencyList,
			String startNode, String destinationNode) {
		int count = 1, parent = 0;
		String data = "";
		boolean goal = false;
		List<String> output = new ArrayList<String>();
		List<Node> discovered = new ArrayList<Node>();
		LinkedList<Node> index = new LinkedList<>();
		Node start = new Node(count++, startNode, 0, parent++);
		index.add(start);

		while (!index.isEmpty() && !goal) {
			Node current = index.removeFirst();
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
					}
					// Sorting of the next layer
					for (int i = 0; i < currentList.size(); i++) {
						for (int j = i; j < currentList.size(); j++) {
							if (currentList.get(i).getName().charAt(0) > currentList
									.get(j).getName().charAt(0)) {
								Node y = new Node();
								y = currentList.get(i);
								currentList.set(i, currentList.get(j));
								currentList.set(j, y);
							}
						}
					}
					index.addAll(0, currentList);
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
