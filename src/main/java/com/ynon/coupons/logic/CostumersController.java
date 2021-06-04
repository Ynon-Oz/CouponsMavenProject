package com.ynon.coupons.logic;

import java.util.List;

import com.ynon.coupons.beans.javabeans.EmailMessage;
import com.ynon.coupons.config.Config;
import com.ynon.coupons.messages.EmailMessages;
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


    //CREATE
    public void addCostumer(Customer customer) throws ApplicationException {
        if (this.usersController.isUserExist(customer.getUser().getUserName())) {
            throw new ApplicationException(ErrorType.USER_ALREADY_EXIST, ErrorType.USER_ALREADY_EXIST.getErrorMessage());
        }
        // set first letter of first and last name to capital letter
        customer.setFirstName(nameFormatter(customer.getFirstName()));
        customer.setLastName(nameFormatter(customer.getLastName()));

        customer.getUser().setPassword(usersController.obfuscation(customer.getUser().getPassword()));


        this.customersDao.save(customer);
        //TODO register confirmation Email
        EmailMessage confirmationEmail = new EmailMessage();
        confirmationEmail.setFromName(Config.ORGANIZATION_NAME+" Team");
        confirmationEmail.setSubject(EmailMessages.SUBJECT_NEW_USER_REGISTRATION);
        confirmationEmail.setToEmail(customer.getUser().getUserName());
        confirmationEmail.setToName(customer.getFirstName() + " " + customer.getLastName());
        String message =
                String.format(
                        EmailMessages.REGISTRATION_CONFIRMATION_EMAIL_TEMPLATE,
                        customer.getFirstName() + " " + customer.getLastName(),
                        Config.HOST_URL+"/users/active/",// TODO Add activation key generator
                        customer.getUser().getId());
        confirmationEmail.setMessage(message);
        emailService.sendEmail(confirmationEmail);
    }

    /**
     * This method gets String and returns a String name
     * having a capital first letter and the rest of letters small
     *
     */
    private String nameFormatter(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        return name;
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
