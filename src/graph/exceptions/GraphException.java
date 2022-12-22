package graph.exceptions;

public abstract class GraphException extends Exception {
    public GraphException() {
        super();
    }

    public GraphException(String message) {
        super(message);
    }
}
