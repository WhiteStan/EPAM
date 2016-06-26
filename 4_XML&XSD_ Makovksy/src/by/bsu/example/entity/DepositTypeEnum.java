package by.bsu.example.entity;

/**
 * Created by Lenovo on 06.04.2016.
 */
public enum DepositTypeEnum {
    URGENT("urgent"),
    ONDEMAND("onDemand"),
    ROLLUP("rollup"),
    SAVINGS("savings"),
    CALCULATED("calculated"),
    METAL("metal");
    private String value;
    private DepositTypeEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
