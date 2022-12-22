package graph;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Graph<T extends GraphNode> {
    protected final HashMap<String, T> data = new HashMap<>();

    private class TopologicalSortHelpers {
        private final String[] result;
        private final HashSet<String> visited;
        private int currentPos;

        private TopologicalSortHelpers() {
            this.result = new String[data.size()];
            this.visited = new HashSet<>();
            this.currentPos = 0;
        }

        private void innerFunction(String key) throws MissingDependencyException {
            visited.add(key);
            for (String item : data.get(key).getDependencies()) {
                if (data.get(item) == null) {
                    throw new MissingDependencyException(key, item);
                }
                if (visited.contains(item)) {
                    continue;
                }
                innerFunction(item);
            }
            result[currentPos] = key;
            ++currentPos;
        }
    }

    public String[] topologicalSort() throws MissingDependencyException {
        TopologicalSortHelpers topologicalSortHelpers = new TopologicalSortHelpers();

        for (String key : data.keySet()) {
            if (topologicalSortHelpers.visited.contains(key)) {
                continue;
            }
            topologicalSortHelpers.innerFunction(key);
        }

        return topologicalSortHelpers.result;
    }
}
