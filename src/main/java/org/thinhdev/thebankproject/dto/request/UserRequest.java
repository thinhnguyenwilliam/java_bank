package org.thinhdev.thebankproject.dto.request;


public class UserRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;


    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;

    public UserRequest(String firstName, String lastName, String otherName, String gender, String address, String stateOfOrigin, String email, String phoneNumber, String alternativePhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.address = address;
        this.stateOfOrigin = stateOfOrigin;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.alternativePhoneNumber = alternativePhoneNumber;
    }

    public UserRequest() {
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getOtherName() {
        return this.otherName;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getStateOfOrigin() {
        return this.stateOfOrigin;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAlternativePhoneNumber() {
        return this.alternativePhoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAlternativePhoneNumber(String alternativePhoneNumber) {
        this.alternativePhoneNumber = alternativePhoneNumber;
    }

    public static class UserRequestBuilder {
        private String firstName;
        private String lastName;
        private String otherName;
        private String gender;
        private String address;
        private String stateOfOrigin;
        private String email;
        private String phoneNumber;
        private String alternativePhoneNumber;

        UserRequestBuilder() {
        }

        public UserRequestBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserRequestBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRequestBuilder otherName(String otherName) {
            this.otherName = otherName;
            return this;
        }

        public UserRequestBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserRequestBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserRequestBuilder stateOfOrigin(String stateOfOrigin) {
            this.stateOfOrigin = stateOfOrigin;
            return this;
        }

        public UserRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserRequestBuilder alternativePhoneNumber(String alternativePhoneNumber) {
            this.alternativePhoneNumber = alternativePhoneNumber;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(this.firstName, this.lastName, this.otherName, this.gender, this.address, this.stateOfOrigin, this.email, this.phoneNumber, this.alternativePhoneNumber);
        }

        public String toString() {
            return "UserRequest.UserRequestBuilder(firstName=" + this.firstName + ", lastName=" + this.lastName + ", otherName=" + this.otherName + ", gender=" + this.gender + ", address=" + this.address + ", stateOfOrigin=" + this.stateOfOrigin + ", email=" + this.email + ", phoneNumber=" + this.phoneNumber + ", alternativePhoneNumber=" + this.alternativePhoneNumber + ")";
        }
    }
}
