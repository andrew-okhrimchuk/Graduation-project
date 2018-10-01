package andrey.util.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughRightsException extends ApplicationException {
    public static final String NOT_Enough_Rights_EXCEPTION = "exception.common.notEnoughRights";

    public NotEnoughRightsException(String arg) {
        super(ErrorType.DATA_NOT_Enough_Rights, NOT_Enough_Rights_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}