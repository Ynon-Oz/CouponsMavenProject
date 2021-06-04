package com.ynon.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ynon.coupons.beans.SuccessfulLoginData;
import com.ynon.coupons.beans.UserLoginDetails;
import com.ynon.coupons.beans.javabeans.UserBean;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.UsersController;



@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UsersController usersController;

	//	 http://localhost:8080/users/login
	@PostMapping("/login")
	public  SuccessfulLoginData userLogin (@RequestBody UserLoginDetails userLoginDetails)throws ApplicationException  {
		return this.usersController.userLogin(userLoginDetails);
	}


	//CREATE
	@PostMapping
	public long addUser(@RequestBody User user) throws ApplicationException {
		return this.usersController.addUser(user);
	}

	//UPDATE
	@PutMapping
	public void updateUser(@RequestBody User user) throws ApplicationException {
		this.usersController.updateUser(user);
	}
	//GET
	@GetMapping("/{id}")
	public UserBean getUser(@PathVariable("id") long id) throws ApplicationException{
		return this.usersController.getUserByUserId(id);
	}



	//GET ALL
	@GetMapping
	public List<UserBean> getAllUsers()throws ApplicationException{
		return this.usersController.getAllUsers();
	}

	@GetMapping("/by")
	public List<User> getAllCompanyUsers(@RequestParam("companyId") long companyId)throws ApplicationException{
		return this.usersController.findUsersByCompany(companyId);
	}

	@GetMapping("/active/{id}")
	public void activateUserAccount(@PathVariable("id")long id){
		this.usersController.activateUserAccount(id);
	}

//		@GetMapping("/byName")
//		public List<User> findByName(@RequestParam("name") String name){
//			return this.usersController.findByName(name);
//		}
	//DELETE
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id")long id)throws ApplicationException {
		this.usersController.deleteUsersById(id);
	}



}


