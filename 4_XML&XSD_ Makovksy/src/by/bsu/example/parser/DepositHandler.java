package by.bsu.example.parser;

import by.bsu.example.entity.Deposit;
import by.bsu.example.entity.DepositOnDemand;
import by.bsu.example.entity.DepositUrgent;
import by.bsu.example.parser.client.DepositEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class DepositHandler extends DefaultHandler  {
    private Set<Deposit> deposits;
    private Deposit current = null;
    private DepositEnum currentEnum = null;
    private EnumSet<DepositEnum> withText;
    public DepositHandler(){
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositEnum.NAME, DepositEnum.CHECKACCOUNT);
    }
    public Set<Deposit> getDeposits(){
        return  deposits;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("deposit".equals(localName)) {
            switch (attrs.getValue(0)){
                case "urgent":
                    current = new DepositUrgent();
                    current.setType(attrs.getValue(0));
                    break;
                case "onDemand":
                    current = new DepositOnDemand();
                    current.setType(attrs.getValue(0));
                    break;
                default:
                    current = new Deposit();
                    current.setType(attrs.getValue(0));
            }
        } else {
            DepositEnum temp = DepositEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName){
        if ("deposit".equals(localName)) {
            deposits.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                case DEPOSITOR:
                    current.setDepositor(s);
                    break;
                case ACCOUNTID:
                    current.setAccountId(Integer.parseInt(s));
                    break;
                case DEPOSITAMOUNT:
                    current.setDepositAmount(Long.parseLong(s));
                    break;
                case PROFITABILITY:
                    current.setProfitability(Short.parseShort(s));
                    break;
                case DEPOSITTIME:
                    ((DepositUrgent)current).setDepositTime(Integer.parseInt(s));
                    break;
                case CHECKACCOUNT:
                    ((DepositOnDemand)current).setCheckAccount(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
