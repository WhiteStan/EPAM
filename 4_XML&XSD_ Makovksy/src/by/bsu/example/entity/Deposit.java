package by.bsu.example.entity;

/**
 * Created by Lenovo on 04.04.2016.
 */
public class Deposit {

    private String name;
    private String country;
    private String depositor;
    private DepositTypeEnum type;
    private int accountId;
    private long depositAmount;
    private short profitability;

    public String getType() {
        return type.toString();
    }

    public void setType(String type) {
        DepositTypeEnum current = DepositTypeEnum.valueOf(type.toUpperCase());
        this.type = current;
    }
    public void setType(DepositTypeEnum type){
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public short getProfitability() {
        return profitability;
    }

    public void setProfitability(short profitability) {
        this.profitability = profitability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (accountId != deposit.accountId) return false;
        if (depositAmount != deposit.depositAmount) return false;
        if (profitability != deposit.profitability) return false;
        if (!name.equals(deposit.name)) return false;
        if (!country.equals(deposit.country)) return false;
        if (!depositor.equals(deposit.depositor)) return false;
        return type == deposit.type;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + depositor.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + accountId;
        result = 31 * result + (int) (depositAmount ^ (depositAmount >>> 32));
        result = 31 * result + (int) profitability;
        return result;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", depositor='" + depositor + '\'' +
                ", type=" + type +
                ", accountId=" + accountId +
                ", depositAmount=" + depositAmount +
                ", profitability=" + profitability +
                '}';
    }

}