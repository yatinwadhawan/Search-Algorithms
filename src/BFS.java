import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BFS {

	// BFS Algorithm
	public String breadthFirstSearch(HashMap<String, List<Node>> adjacencyList,
			String startNode, String destinationNode) {
		int layer = 0, count = 1, parent = 0;
		String data = "";
		boolean flag = false, goal = false;
		HashMap<Integer, List<Node>> index = new HashMap<Integer, List<Node>>();
		List<Node> visited = new ArrayList<Node>();
		List<String> output = new ArrayList<String>();
		List<Node> lsStart = new ArrayList<Node>();
		List<Node> nodeList;

		// First Element
		Node start = new Node(count++, startNode, 0, parent++);
		lsStart.add(start);
		index.put(layer, lsStart);
		visited.add(start);

		while (!index.get(layer).isEmpty() && !goal) {
			nodeList = new ArrayList<Node>();
			// Each node in the layer
			for (int j = 0; j < index.get(layer).size(); j++) {

				Node current = index.get(layer).get(j);
				List<Node> x = adjacencyList.get(current.getName());
				for (int k = 0; k < x.size(); k++) {
					Node adjacentNode = x.get(k);
					boolean present = false;
					for (int i = 0; i < visited.size(); i++) {
						if (visited.get(i).getName()
								.compareTo(adjacentNode.getName()) == 0) {
							present = true;
							break;
						}
					}
					if (!present) {
						adjacentNode.setCounter(count++);
						adjacentNode.setParent(current.getCounter());
						nodeList.add(adjacentNode);
						if (adjacentNode.getName().compareTo(destinationNode) == 0) {
							// Goal Reached .. Now BackTrack
							output.add(adjacentNode.getName());
							output.add(current.getName());
							parent = current.getParent();
							while (parent != 0) {
								for (int i = 0; i < visited.size(); i++) {
									if (visited.get(i).getCounter() == parent) {
										output.add(visited.get(i).getName());
										parent = visited.get(i).getParent();
										break;
									}
								}
							}
							goal = true;
							break;
						}
						flag = true;
					}
				}
				if (flag) {
					flag = false;
					++parent;
				}

				// Goal Reached, Break the loop
				if (goal)
					break;
			}

			// Sorting of the next layer
			for (int i = 0; i < nodeList.size(); i++) {
				for (int j = i; j < nodeList.size(); j++) {
					if (nodeList.get(i).getName().charAt(0) > nodeList.get(j)
							.getName().charAt(0)) {
						Node y = new Node();
						y = nodeList.get(i);
						nodeList.set(i, nodeList.get(j));
						nodeList.set(j, y);
					}
				}
			}

			for (int i = 0; i < nodeList.size(); i++) {
				visited.add(nodeList.get(i));
			}
			layer++;
			index.put(layer, nodeList);

			// Goal Reached, Break the loop
			if (goal)
				break;
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
