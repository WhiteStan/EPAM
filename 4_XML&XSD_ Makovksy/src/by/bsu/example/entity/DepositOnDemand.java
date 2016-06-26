package by.bsu.example.entity;

/**
 * Created by Lenovo on 04.04.2016.
 */
public class DepositOnDemand extends Deposit {
    private int checkAccount;

    public int getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(int checkingAccount) {
        this.checkAccount = checkingAccount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DepositOnDemand that = (DepositOnDemand) o;

        return checkAccount == that.checkAccount;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + checkAccount;
        return result;
    }
}
