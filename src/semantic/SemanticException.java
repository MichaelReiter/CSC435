package semantic;

public class SemanticException extends RuntimeException {
    private final String message;
    private final int line;
    private final int offset;

    public SemanticException(String message, int line, int offset) {
        this.message = message;
        this.line = line;
        this.offset = offset;
    }

    public String toString() {
        return "Error:" + line + ":" + offset + ": " + message;
    }
}
