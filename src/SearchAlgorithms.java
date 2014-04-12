import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAlgorithms {

	// Members Variables
	private List<String> people;
	private HashMap<String, List<Node>> adjacencyList;

	// Constructor
	public SearchAlgorithms() {
		people = new ArrayList<String>();
		adjacencyList = new HashMap<String, List<Node>>();
	}

	// Main Function
	public static void main(String[] args) throws IOException {
		SearchAlgorithms search = new SearchAlgorithms();

		String data = search.takeInputFromFile("socialnetwork.txt");
		search.fillDataIntoDataStructure(data);

		BFS bfs = new BFS();
		DFS dfs = new DFS();
		UCS ucs = new UCS();

		String bfsV = bfs.breadthFirstSearch(search.adjacencyList, "Alice",
				"Noah");
		search.putOutputToFile("breadth-first-result.txt", bfsV);
		String dfsV = dfs.depthFirstSearch(search.adjacencyList, "Alice",
				"Noah");
		search.putOutputToFile("depth-first-result.txt", dfsV);
		String ucsV = ucs.uniformCostSearch(search.adjacencyList, "Alice",
				"Noah");
		search.putOutputToFile("uniform-cost-search-result.txt", ucsV);
	}

	// Fill the values of the file in the data structure
	public void fillDataIntoDataStructure(String data) {
		String[] ls = data.split("\n");
		List<String> numberLs = new ArrayList<String>();
		for (int i = 0; i < ls.length; i++) {
			String str = ls[i].replaceAll("(\\r|\\n)", "");
			if (!str.contains("0"))
				people.add(str);
			else
				numberLs.add(str);
		}

		// Filling Data Structure
		for (int i = 0; i < people.size(); i++) {
			List<Node> node = new ArrayList<Node>();
			String[] valueList = numberLs.get(i).split(" ");
			for (int j = 0; j < valueList.length; j++) {
				int val = 0;
				if ((val = Integer.parseInt(valueList[j])) != 0) {
					Node n = new Node(0, people.get(j), val, 0);
					node.add(n);
				}
			}
			adjacencyList.put(people.get(i), node);
		}
	}

	// Open the Input file and extract values from it.
	public String takeInputFromFile(String path) throws IOException {
		StringBuffer output = new StringBuffer();
		File file = new File(path);
		BufferedReader bufferReader = new BufferedReader(new FileReader(file));
		char[] buffer = new char[1024];
		int length = 0;
		while ((length = bufferReader.read(buffer)) != -1) {
			output.append(String.valueOf(buffer, 0, length));
		}
		bufferReader.close();
		return output.toString();
	}

	// Print the output in the file
	public void putOutputToFile(String path, String data) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(), false);
		BufferedWriter writer = new BufferedWriter(fileWritter);
		writer.write(data);
		writer.flush();
		writer.close();
	}
}