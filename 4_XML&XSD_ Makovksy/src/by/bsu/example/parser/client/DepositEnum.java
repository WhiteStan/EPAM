package by.bsu.example.parser.client;

/**
 * Created by Lenovo on 06.04.2016.
 */
public enum DepositEnum {
    BANKS("banks"),
    DEPOSIT("deposit"),
    TYPE("type"),
    NAME("name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    ACCOUNTID("accountId"),
    DEPOSITAMOUNT("depositAmount"),
    PROFITABILITY("profitability"),
    DEPOSITTIME("depositTime"),
    CHECKACCOUNT("checkAccount");
    private String value;
    private DepositEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
