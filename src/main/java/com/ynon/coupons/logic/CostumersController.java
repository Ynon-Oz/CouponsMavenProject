package com.ynon.coupons.logic;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ynon.coupons.beans.javabeans.EmailMessage;
import com.ynon.coupons.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.javabeans.CustomerBean;
import com.ynon.coupons.dao.ICustomersDao;
import com.ynon.coupons.entities.Customer;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Service;


@Service
public class CostumersController {

    @Autowired
    private ICustomersDao customersDao;
    @Autowired
    private UsersController usersController;
    @Autowired
    private EmailService emailService;

    public static final String REGISTERATION_CONFIRMATION_EMAIL_TEMPLATE =
            "Dear %s,\n"+"\n"
                    + "Thank you for choose becoming a member of CouponSystem,\n"
                    + "Please click the link below to activate your account:\n"
                    + " %s %s.\n"
                    + "right after activation you will be able to enjoy our services\n" +
                    "\n"
                    + "Always at your service,\n"
                    + "CouponSystem Team.";

    //CREATE
    public void addCostumer(Customer customer) throws ApplicationException {
        if (this.usersController.isUserExist(customer.getUser().getUserName())) {
            throw new ApplicationException(ErrorType.USER_ALLREADY_EXIST, ErrorType.USER_ALLREADY_EXIST.getErrorMessage());
        }
        customer.setFirstName(customer.getLastName().toLowerCase());
        customer.setFirstName(customer.getFirstName());
        customer.getFirstName().toLowerCase();
        //TODO set first letter of first and last name to capital letter
        customer.getUser().setPassword(usersController.obfuscation(customer.getUser().getPassword()));

        this.customersDao.save(customer);
        //TODO register confirmation mail
        EmailMessage confirmationEmail = new EmailMessage();
        confirmationEmail.setFromName("Coupon System Team");
        confirmationEmail.setSubject("Register confirmation mail");
        confirmationEmail.setToEmail(customer.getUser().getUserName());
        String message =
                String.format(
                        REGISTERATION_CONFIRMATION_EMAIL_TEMPLATE,
                        customer.getFirstName(),
                        "http://localhost:8080/users/active/",
                        customer.getUser().getId());
        confirmationEmail.setMessage(message);
        emailService.sendEmail( confirmationEmail );
    }


    //GET
    public CustomerBean getCustomerBean(long costumerId) throws ApplicationException {
        return this.customersDao.getCustomer(costumerId);
    }

    public Customer getCustomer(long costumerId) throws ApplicationException {
        return this.customersDao.findById(costumerId);
    }

    //GET ALL
    public List<CustomerBean> getAllCustomers() throws ApplicationException {
        return this.customersDao.getAllCustomers();
    }


    //UPDATE
    public long updateCostumer(Customer customer) throws ApplicationException {
        Customer updatedCustomer = new Customer();
        updatedCustomer = this.customersDao.getOne(customer.getId());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setPhone(customer.getPhone());
        updatedCustomer.getUser().setPassword(usersController.obfuscation(customer.getUser().getPassword()));
        return this.customersDao.saveAndFlush(updatedCustomer).getUser().getId();
    }


    //DELETE
    public void deleteCustomerByCustomerId(long costumerId) throws ApplicationException {
        this.customersDao.deleteById(costumerId);

    }
}
