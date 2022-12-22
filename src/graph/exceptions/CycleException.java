package graph.exceptions;

import data.StaticData;

public class CycleException extends GraphException {
    public CycleException(Iterable<String> cycle) {
        super(StaticData.getCycleExceptionText(cycle));
    }
}
