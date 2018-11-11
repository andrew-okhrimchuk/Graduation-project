package andrey.util;

import andrey.model.Restouran;
import andrey.model.Role;
import andrey.model.User;
import andrey.to.RestouranTo;
import andrey.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.Collections;

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