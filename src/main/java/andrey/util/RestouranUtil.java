package andrey.util;

import andrey.model.Restouran;
import andrey.to.RestouranTo;

public class RestouranUtil {


    public static Restouran createNewFromTo(RestouranTo newUser) {
        return new Restouran(null, newUser.getName());
    }

    public static RestouranTo asTo(Restouran user) {
        return new RestouranTo(user.getId(), user.getName());
    }

    public static Restouran updateFromTo(Restouran user, RestouranTo userTo) {
        user.setId(userTo.getId());
        user.setName(userTo.getName());
        return user;
    }

}