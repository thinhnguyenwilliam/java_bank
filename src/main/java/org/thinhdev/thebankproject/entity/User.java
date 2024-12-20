package org.thinhdev.thebankproject.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String status;

    @CreationTimestamp
    private LocalDate createAt;

    @UpdateTimestamp
    private LocalDate modifiedAt;

    public User(Long id, String firstName, String lastName, String otherName, String gender, String address, String stateOfOrigin, String accountNumber, BigDecimal accountBalance, String email, String phoneNumber, String alternativePhoneNumber, String status, LocalDate createAt, LocalDate modifiedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.address = address;
        this.stateOfOrigin = stateOfOrigin;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.alternativePhoneNumber = alternativePhoneNumber;
        this.status = status;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Long getId() {
        return this.id;
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

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance;
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

    public String getStatus() {
        return this.status;
    }

    public LocalDate getCreateAt() {
        return this.createAt;
    }

    public LocalDate getModifiedAt() {
        return this.modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class UserBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String otherName;
        private String gender;
        private String address;
        private String stateOfOrigin;
        private String accountNumber;
        private BigDecimal accountBalance;
        private String email;
        private String phoneNumber;
        private String alternativePhoneNumber;
        private String status;
        private LocalDate createAt;
        private LocalDate modifiedAt;

        UserBuilder() {
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder otherName(String otherName) {
            this.otherName = otherName;
            return this;
        }

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder stateOfOrigin(String stateOfOrigin) {
            this.stateOfOrigin = stateOfOrigin;
            return this;
        }

        public UserBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public UserBuilder accountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder alternativePhoneNumber(String alternativePhoneNumber) {
            this.alternativePhoneNumber = alternativePhoneNumber;
            return this;
        }

        public UserBuilder status(String status) {
            this.status = status;
            return this;
        }

        public UserBuilder createAt(LocalDate createAt) {
            this.createAt = createAt;
            return this;
        }

        public UserBuilder modifiedAt(LocalDate modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public User build() {
            return new User(this.id, this.firstName, this.lastName, this.otherName, this.gender, this.address, this.stateOfOrigin, this.accountNumber, this.accountBalance, this.email, this.phoneNumber, this.alternativePhoneNumber, this.status, this.createAt, this.modifiedAt);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", otherName=" + this.otherName + ", gender=" + this.gender + ", address=" + this.address + ", stateOfOrigin=" + this.stateOfOrigin + ", accountNumber=" + this.accountNumber + ", accountBalance=" + this.accountBalance + ", email=" + this.email + ", phoneNumber=" + this.phoneNumber + ", alternativePhoneNumber=" + this.alternativePhoneNumber + ", status=" + this.status + ", createAt=" + this.createAt + ", modifiedAt=" + this.modifiedAt + ")";
        }
    }
}
