package andrey.util.exception;

import org.springframework.http.HttpStatus;

public class AlreadyVotedException extends ApplicationException {
    public static final String APP_ERROR = "exception.common.notFound";

    public AlreadyVotedException(String message) {

        super(ErrorType.APP_ERROR, APP_ERROR, HttpStatus.PRECONDITION_FAILED, message);
    }
}