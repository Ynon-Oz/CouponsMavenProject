package com.ynon.coupons.logic;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.SuccessfulLoginData;
import com.ynon.coupons.beans.UserLoginDetails;
import com.ynon.coupons.beans.javabeans.UserBean;
import com.ynon.coupons.dao.IUsersDao;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersController{
	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private CacheController cacheController;

	//CREATE  -  ADD
	public long addUser (User user) throws ApplicationException{
		user.setUserName(user.getUserName().trim().toLowerCase());
		userValidations(user);
		if(isUserExist(user.getUserName())) {
			throw new ApplicationException(ErrorType.USER_ALREADY_EXIST, ErrorType.USER_ALREADY_EXIST.getErrorMessage());
		}
		user.setPassword(obfuscation(user.getPassword()));
		log.info("User "+user.getUserName()+" is adding to DB");
		return usersDao.save(user).getId();
	}


	//UPDATE  
	public long updateUser(User user) throws ApplicationException{
		userValidations(user);
		user.setPassword(obfuscation(user.getPassword()));
		return this.usersDao.save(user).getId();
	}


	//READ  -  GET
	public UserBean getUserByUserId (long userId) throws ApplicationException {
		return this.usersDao.getUserByUserId(userId);

	}

	public List<UserBean> getAllUsers() throws ApplicationException {
		return this.usersDao.getAllUsers();
	}


	public List<User>findUsersByCompany (long companyId){
		return this.usersDao.findByCompanyOrderByUserName(companyId);
	}


	//DELETE  -  REMOVE

	public void deleteUsersById(long id) throws ApplicationException{
		this.usersDao.deleteById(id);
	}


	//OBFUSCATION
	public String obfuscation(String password) {
		String pas8w0rd = password.hashCode()+"";
		return pas8w0rd;
	}


	//VALIDATIONS
	private void userValidations(User user) throws ApplicationException{
		if (user == null) {
			throw new ApplicationException(ErrorType.NULL,"User details are missing");
		}
		if (user.getPassword().equals("")) {
			throw new ApplicationException(ErrorType.USER_EMPTY_PASSWORD,"Empty password");
		}
		if (user.getPassword().length() < 6) {
			throw new ApplicationException(ErrorType.USER_SHORT_PASSWORD,"Short password");
		}
		if (user.getPassword().length() > 10) {
			throw new ApplicationException(ErrorType.USER_LONG_PASSWORD,"Long password");
		}


	}


	//EMAIL VALIDATION
//	public boolean isValidEmailAddress(String email) {

	/**
	 *Email Validation have been changed to  javax.validation.constraints.Email at entity User.class;
	 */
//		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//		java.util.regex.Matcher m = p.matcher(email);
//		return m.matches();
//	}

	//LOGIN
	public SuccessfulLoginData userLogin(UserLoginDetails userLoginDetails) throws ApplicationException {

		userLoginDetails.setPassword(obfuscation(userLoginDetails.getPassword()));
		User user= new User();
		user = usersDao.findByUserName(userLoginDetails.getUserName());
		if (user==null) {
			throw new ApplicationException(ErrorType.WRONG_USERNAME_OR_PASSWORD, ErrorType.WRONG_USERNAME_OR_PASSWORD.getErrorMessage());
		}
		if (!userLoginDetails.getPassword().equals(user.getPassword())) {
			throw new ApplicationException(ErrorType.WRONG_USERNAME_OR_PASSWORD, ErrorType.WRONG_USERNAME_OR_PASSWORD.getErrorMessage());
		}

		String token = generateToken(userLoginDetails);
		SuccessfulLoginData successfulLoginData = new SuccessfulLoginData(user.getType(),user.getId(),token);
		cacheController.put(successfulLoginData);
		return successfulLoginData;
	}

	//	TOKEN GENERATOR
	private String generateToken(UserLoginDetails userLoginDetails) {
		String token = (userLoginDetails.getUserName().hashCode()+"-"+userLoginDetails.getPassword().hashCode())+"";
		return token;
	}


	public boolean isUserExist(String userName) {
		User u = new User();
		u = this.usersDao.findByUserName(userName);
		if (u==null) {
			return false;
		}
		return true;
	}


    public void activateUserAccount(long id) {
		User user =   usersDao.getOne(id);
		user.setActivated(true);
		usersDao.saveAndFlush(user);
		log.info("User "+ user.getUserName() +" has been activated his account",user.getUserName());

    }
}



