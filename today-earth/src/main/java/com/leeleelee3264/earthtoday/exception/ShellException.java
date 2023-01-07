package com.leeleelee3264.earthtoday.exception;

public class ShellException extends RuntimeException{

    public ShellException(String message) {
        super(message);
    }

    public static final class NotSupportedOS extends ShellException {

        public NotSupportedOS(String OS) {
            super(OS + " is not supported in GifGenerator");
        }
    }

    public static final class FailedExecution extends ShellException {

        public FailedExecution(String command) {
            super("Failed execute command: " + command);
        }
    }

}
