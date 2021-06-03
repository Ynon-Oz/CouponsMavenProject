package com.ynon.coupons.beans.javabeans;

public class CustomerRegistrationDetailsBean {
		private String userName;
		private String password;
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private Long personalId;
		private String address;
	
		
		public CustomerRegistrationDetailsBean(String userName, String password, String firstName, String lastName,
											   String phoneNumber, Long personalId, String address) {
			super();
			this.userName = userName;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.personalId = personalId;
			this.address = address;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}


		public Long getPersonalId() {
			return personalId;
		}


		public void setPersonalId(Long personalId) {
			this.personalId = personalId;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		@Override
		public String toString() {
			return "CustomerRegistrationDetailsBean [userName=" + userName + ", password=" + password + ", firstName="
					+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", personalId="
					+ personalId + ", address=" + address + "]";
		}
		
		
		
		
}
