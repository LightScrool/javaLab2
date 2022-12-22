package graph.exceptions;

import data.StaticData;

public class CycleException extends IllegalArgumentException {
    public CycleException(Iterable<String> cycle) {
        super(StaticData.getCycleExceptionText(cycle));
    }
}
