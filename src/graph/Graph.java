package graph;

import graph.exceptions.CycleException;
import graph.exceptions.MissingDependencyException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Graph<T extends GraphNode> {
    protected final HashMap<String, T> data = new HashMap<>();

    private class GetCycleHelpers {
        private final HashSet<String> visited;

        private GetCycleHelpers() {
            this.visited = new HashSet<>();
        }

        private LinkedList<String> innerFunction(String key, LinkedList<String> path) throws MissingDependencyException {
            visited.add(key);
            if (path.contains(key)) {
                path.add(key);
                return path;
            }

            path.add(key);
            for (String item : data.get(key).getDependencies()) {
                if (!data.containsKey(item)) {
                    throw new MissingDependencyException(key, item);
                }
                LinkedList<String> localResult = innerFunction(item, path);
                if (localResult != null) {
                    return localResult;
                }
            }
            path.removeLast();
            return null;
        }
    }

    /**
     * Проверяет, присутствуют ли в графе циклы.
     * Возвращает первый найденный путь с циклом, если они есть;
     * или null, если их нет
     */
    public LinkedList<String> getCycle() throws MissingDependencyException {
        GetCycleHelpers getCycleHelpers = new GetCycleHelpers();

        for (String key : data.keySet()) {
            if (getCycleHelpers.visited.contains(key)) {
                continue;
            }
            LinkedList<String> localResult = getCycleHelpers.innerFunction(key, new LinkedList<>());
            if (localResult != null) {
                return localResult;
            }
        }

        return null;
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

        private void innerFunction(String key) throws MissingDependencyException {
            visited.add(key);
            for (String item : data.get(key).getDependencies()) {
                if (!data.containsKey(item)) {
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

    public String[] topologicalSort() throws MissingDependencyException, CycleException {
        LinkedList<String> cycle = getCycle();
        if (cycle != null) {
            throw new CycleException(cycle);
        }
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
