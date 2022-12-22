package graph.exceptions;

import data.StaticData;

public class MissingDependencyException extends GraphException {
    public MissingDependencyException(String node, String dependency) {
        super(StaticData.getMissingDependencyExceptionText(node, dependency));
    }
}
