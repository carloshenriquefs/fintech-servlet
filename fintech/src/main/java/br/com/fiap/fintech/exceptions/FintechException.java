package br.com.fiap.fintech.exceptions;

public class FintechException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorTypeEnum errorType;

    public FintechException(String msg, Throwable throwable, ErrorTypeEnum errorType) {
        super(msg, throwable);
        this.errorType = errorType;
    }

    public FintechException(String message) {
        super(message);
    }

    public ErrorTypeEnum getErrorType() {
        return errorType;
    }
}
