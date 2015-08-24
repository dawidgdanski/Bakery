package pl.dawidgdanski.bakery.exception;

public class SwipeValidationException extends Exception {

    private final int errorStringId;

    public SwipeValidationException(int errorStringId) {
        this.errorStringId = errorStringId;
    }

    public int getErrorStringId() {
        return errorStringId;
    }
}
