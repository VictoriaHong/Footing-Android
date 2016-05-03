package edu.cmu.footinguidemo.exception;

/**
 * Customized exception for Footing App
 */
public class FootingException extends Exception {

    private String message;

    public FootingException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
