package br.com.fiap.fintech.exceptions;

public class AppFintechException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorTypeEnum errorType;

    public AppFintechException(String msg, Throwable throwable, ErrorTypeEnum errorType) {
        super(msg, throwable);
        this.errorType = errorType;
    }

    public ErrorTypeEnum getErrorType() {
        return errorType;
    }
}
