package com.ynon.coupons.logic;

import java.time.LocalDateTime;
import java.util.List;

import com.ynon.coupons.mapper.UsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.SuccessfulLoginData;
import com.ynon.coupons.beans.UserLoginDetails;
import com.ynon.coupons.beans.javabeans.UserBean;
import com.ynon.coupons.dao.IUsersDao;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersController {
    @Autowired
    private IUsersDao usersDao;
    @Autowired
    private CacheController cacheController;
    @Autowired
    private UsersMapper mapper;

    //CREATE  -  ADD
    public ResponseEntity<?> addUser(User user) throws ApplicationException {
        user.setEmail(user.getEmail().toLowerCase());
        userValidations(user);
        if (isUserExist(user.getEmail())) {
            throw new ApplicationException(ErrorType.USER_ALREADY_EXIST, ErrorType.USER_ALREADY_EXIST.getErrorMessage());
        }
        user.setPassword(obfuscation(user.getPassword()));
        log.info("User " + user.getEmail() + " is adding to DB");
        return new ResponseEntity<>((User) usersDao.save((user)), HttpStatus.CREATED);
    }
    public ResponseEntity<?> addUser(UserBean dto) throws ApplicationException {
        User user = mapper.toDao(dto);
        System.out.println("dto: "+dto.getCompanyId());
        System.out.println("Dao: "+user.getCompany().getId());
        user.setEmail(user.getEmail().toLowerCase());
        userValidations(user);
        if (isUserExist(user.getEmail())) {
            throw new ApplicationException(ErrorType.USER_ALREADY_EXIST, ErrorType.USER_ALREADY_EXIST.getErrorMessage());
        }
        user.setPassword(obfuscation(user.getPassword()));
        log.info("User " + user.getEmail() + " is adding to DB");
        return new ResponseEntity<>(usersDao.save((user)), HttpStatus.CREATED);
    }


    //UPDATE
    public ResponseEntity<?> updateUser(long id, User user) throws ApplicationException {
        if (usersDao.existsById(id)) {
            userValidations(user);
//            User update = usersDao.getOne(id);
            user.setPassword(obfuscation(user.getPassword()));
            return new ResponseEntity<User>(this.usersDao.save(user), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    //READ  -  GET
    public UserBean getUserByUserId(long userId) throws ApplicationException {
        return this.usersDao.getUserByUserId(userId);

    }

    public List<UserBean> getAllUsers() throws ApplicationException {
        return this.usersDao.getAllUsers();
    }


    public List<User> findUsersByCompany(long companyId) {
        return this.usersDao.findByCompanyOrderByEmail(companyId);
    }


    //DELETE  -  REMOVE

    public void deleteUsersById(long id) throws ApplicationException {
        this.usersDao.deleteById(id);
    }


    //OBFUSCATION
    public String obfuscation(String password) {
        String pas8w0rd = password.hashCode() + "";
        return pas8w0rd;
    }


    //VALIDATIONS
    private void userValidations(User user) throws ApplicationException {
        if (user == null) {
            throw new ApplicationException(ErrorType.NULL, "User details are missing");
        }
        if (user.getPassword().equals("")) {
            throw new ApplicationException(ErrorType.USER_EMPTY_PASSWORD, "Empty password");
        }
        if (user.getPassword().length() < 6) {
            throw new ApplicationException(ErrorType.USER_SHORT_PASSWORD, "Short password");
        }
        if (user.getPassword().length() > 10) {
            throw new ApplicationException(ErrorType.USER_LONG_PASSWORD, "Long password");
        }


    }


    //EMAIL VALIDATION

    /**
     * Email Validation have been changed to  javax.validation.constraints.Email at entity User.class;
     */
//	public boolean isValidEmailAddress(String email) {
//		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//		java.util.regex.Matcher m = p.matcher(email);
//		return m.matches();
//	}

    //LOGIN
    public SuccessfulLoginData userLogin(UserLoginDetails userLoginDetails) throws ApplicationException {

        userLoginDetails.setPassword(obfuscation(userLoginDetails.getPassword()));
        User user = new User();
        user = usersDao.findByEmail(userLoginDetails.getUserName());
        if (user == null) {
            throw new ApplicationException(ErrorType.WRONG_USERNAME_OR_PASSWORD, ErrorType.WRONG_USERNAME_OR_PASSWORD.getErrorMessage());
        }
        if (!userLoginDetails.getPassword().equals(user.getPassword())) {
            throw new ApplicationException(ErrorType.WRONG_USERNAME_OR_PASSWORD, ErrorType.WRONG_USERNAME_OR_PASSWORD.getErrorMessage());
        }

        String token = generateToken(userLoginDetails);
        SuccessfulLoginData successfulLoginData = new SuccessfulLoginData(user.getType(), user.getId(), token);
        cacheController.put(successfulLoginData);
        return successfulLoginData;
    }

    //	TOKEN GENERATOR
    private String generateToken(UserLoginDetails userLoginDetails) {
        String token = (userLoginDetails.getUserName().hashCode() + "-" + userLoginDetails.getPassword().hashCode()) + LocalDateTime.now() +
                "";
        return token;
    }


    public boolean isUserExist(String userName) {
        User u = new User();
        u = this.usersDao.findByEmail(userName);
        if (u == null) {
            return false;
        }
        return true;
    }


    public void activateUserAccount(long id) {
        User user = usersDao.getOne(id);
        user.setActivated(true);
        usersDao.saveAndFlush(user);
        log.info("User " + user.getEmail() + " has been activated his account", user.getEmail());

    }

    public void resetAccountPassword(String userName) {
        //TODO reset account password method
        //1. Generate a temporary password
        //2. Save temporary password on cache
        //3. Send reset password Email
    }
}



