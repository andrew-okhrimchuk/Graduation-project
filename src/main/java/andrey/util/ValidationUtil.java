package andrey.util;


import andrey.util.exception.NotFoundException;

import java.util.List;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static  <T> List<T> checkNotFoundListWithId(List<T> object, int id) {
        return checkNotFoundList(object, "id=" + id);
    }
    public static <T> List<T>  checkNotFoundList(List<T> object, String msg) {
        checkNotFound(object.size() != 0 , msg);
        return object;
    }
////
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }
    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null , msg);
        return object;
    }
////
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

//// ----------->
    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }



}