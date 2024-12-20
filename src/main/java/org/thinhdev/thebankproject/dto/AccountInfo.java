package org.thinhdev.thebankproject.dto;


import java.math.BigDecimal;

public class AccountInfo {
    private String accountNumber;
    private String accountName;
    private BigDecimal accountBalance;

    public AccountInfo(String accountNumber, String accountName, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    public AccountInfo() {
    }

    public static AccountInfoBuilder builder() {
        return new AccountInfoBuilder();
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountInfo)) return false;
        final AccountInfo other = (AccountInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accountNumber = this.getAccountNumber();
        final Object other$accountNumber = other.getAccountNumber();
        if (this$accountNumber == null ? other$accountNumber != null : !this$accountNumber.equals(other$accountNumber))
            return false;
        final Object this$accountName = this.getAccountName();
        final Object other$accountName = other.getAccountName();
        if (this$accountName == null ? other$accountName != null : !this$accountName.equals(other$accountName))
            return false;
        final Object this$accountBalance = this.getAccountBalance();
        final Object other$accountBalance = other.getAccountBalance();
        if (this$accountBalance == null ? other$accountBalance != null : !this$accountBalance.equals(other$accountBalance))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccountInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accountNumber = this.getAccountNumber();
        result = result * PRIME + ($accountNumber == null ? 43 : $accountNumber.hashCode());
        final Object $accountName = this.getAccountName();
        result = result * PRIME + ($accountName == null ? 43 : $accountName.hashCode());
        final Object $accountBalance = this.getAccountBalance();
        result = result * PRIME + ($accountBalance == null ? 43 : $accountBalance.hashCode());
        return result;
    }

    public String toString() {
        return "AccountInfo(accountNumber=" + this.getAccountNumber() + ", accountName=" + this.getAccountName() + ", accountBalance=" + this.getAccountBalance() + ")";
    }

    public static class AccountInfoBuilder {
        private String accountNumber;
        private String accountName;
        private BigDecimal accountBalance;

        AccountInfoBuilder() {
        }

        public AccountInfoBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountInfoBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountInfoBuilder accountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public AccountInfo build() {
            return new AccountInfo(this.accountNumber, this.accountName, this.accountBalance);
        }

        public String toString() {
            return "AccountInfo.AccountInfoBuilder(accountNumber=" + this.accountNumber + ", accountName=" + this.accountName + ", accountBalance=" + this.accountBalance + ")";
        }
    }
}
