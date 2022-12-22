package graph;

import graph.exceptions.CycleException;
import graph.exceptions.MissingDependencyException;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Graph<T extends GraphNode> {
    protected final HashMap<String, T> data = new HashMap<>();

    private class HasCyclesHelpers {
        private final HashSet<String> currentVisited;

        private HasCyclesHelpers() {
            this.currentVisited = new HashSet<>();
        }

        private boolean invokeInner(String key) throws MissingDependencyException {
            if (currentVisited.contains(key)) {
                return true;
            }
            currentVisited.add(key);

            for (String item : data.get(key).getDependencies()) {
                if (!data.containsKey(item)) {
                    throw new MissingDependencyException(key, item);
                }
                if (invokeInner(item)) {
                    return true;
                }
            }
            return false;
        }

        private boolean invoke(String key) throws MissingDependencyException {
            currentVisited.clear();
            return invokeInner(key);
        }
    }

    public boolean hasCycles() throws MissingDependencyException {
        HasCyclesHelpers topologicalSortHelpers = new HasCyclesHelpers();

        for (String key : data.keySet()) {
            if (topologicalSortHelpers.invoke(key)) {
                return true;
            }
        }

        return false;
    }

    private class TopologicalSortHelpers {
        private final String[] result;
        private final HashSet<String> visited;
        private int currentPos;

        private TopologicalSortHelpers() {
            this.result = new String[data.size()];
            this.visited = new HashSet<>();
            this.currentPos = 0;
        }

        private void invoke(String key) throws MissingDependencyException {
            visited.add(key);
            for (String item : data.get(key).getDependencies()) {
                if (!data.containsKey(item)) {
                    throw new MissingDependencyException(key, item);
                }
                if (visited.contains(item)) {
                    continue;
                }
                invoke(item);
            }
            result[currentPos] = key;
            ++currentPos;
        }
    }

    public String[] topologicalSort() throws MissingDependencyException, CycleException {
        if (hasCycles()) {
            throw new CycleException();
        }
        TopologicalSortHelpers topologicalSortHelpers = new TopologicalSortHelpers();

        for (String key : data.keySet()) {
            if (topologicalSortHelpers.visited.contains(key)) {
                continue;
            }
            topologicalSortHelpers.invoke(key);
        }

        return topologicalSortHelpers.result;
    }
}
