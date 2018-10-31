package andrey.util.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughRightsException extends ApplicationException {
    public static final String Not_Enough_Rights_exeption = "exception.common.notEnoughRights";
    public static final String ADMIN_LIST_EMPTY_EXEPTION = "exception.common.AdminListIsEmpty"; //"Admin list is empty. User is not admin of this restouran!"

    public NotEnoughRightsException(String arg) {
        super(ErrorType.DATA_NOT_ENOUGH_RIGHTS, Not_Enough_Rights_exeption, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}