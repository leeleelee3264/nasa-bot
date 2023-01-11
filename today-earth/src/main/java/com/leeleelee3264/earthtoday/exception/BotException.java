package com.leeleelee3264.earthtoday.exception;

public class BotException extends RuntimeException{

    public BotException(String message) {
        super(message);
    }

    public static final class FailedFetchResources extends BotException {

        public FailedFetchResources(String message) {
            super("Fail to run the bot, see detail: " + message);
        }
    }
}
