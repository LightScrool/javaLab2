package graph;

import data.StaticData;

public class MissingDependencyException extends IllegalArgumentException {
    public MissingDependencyException(String node, String dependency) {
        super(StaticData.getMissingDependencyExceptionText(node, dependency));
    }
}
