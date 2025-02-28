package model.exceptions;

public class FullCapacityException extends RuntimeException {
    public FullCapacityException(String message) {
        super(message);
    }
}
