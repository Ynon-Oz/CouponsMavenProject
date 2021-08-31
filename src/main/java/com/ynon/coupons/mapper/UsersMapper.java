package com.ynon.coupons.mapper;

import com.ynon.coupons.beans.javabeans.UserBean;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ynon on  25/08/2021
 */
@Component
public class UsersMapper implements Mapper<User, UserBean> {
    @Override
    public User toDao(UserBean userBean) throws ApplicationException {
        User u = new User();

        return u;
    }

    @Override
    public UserBean toDto(User user) {
        UserBean u1 = new UserBean();
        if (user.getId() != 0) {
            u1.setId(user.getId());
        }
        if (user.getCompany() != null) {
            u1.setCompanyId(user.getCompany().getId());
        } else {
            u1.setCompanyId(null);
        }
        u1.setPassword(user.getPassword());
        u1.setUserName(user.getEmail());
        u1.setType(user.getType());

        return u1;
    }

    @Override
    public List<User> toDaoList(List<UserBean> userBeans) throws ApplicationException {
        return null;
    }

    @Override
    public List<UserBean> toDtoList(List<User> users) {
        return  null;
    }
}
