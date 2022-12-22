package graph.exceptions;

import data.StaticData;

public class CycleException extends IllegalArgumentException {
    public CycleException() {
        super(StaticData.getCycleExceptionText());
    }
}
