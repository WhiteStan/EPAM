package by.bsu.example.entity;

/**
 * Created by Lenovo on 04.04.2016.
 */
public class DepositUrgent extends Deposit {
    private int depositTime;

    public int getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(int depositTime) {
        this.depositTime = depositTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DepositUrgent that = (DepositUrgent) o;

        return depositTime == that.depositTime;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + depositTime;
        return result;
    }

}
