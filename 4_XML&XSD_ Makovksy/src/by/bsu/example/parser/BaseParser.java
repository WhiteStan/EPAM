package by.bsu.example.parser;

import by.bsu.example.entity.Deposit;

import java.util.Set;

/**
 * Created by Lenovo on 06.04.2016.
 */
public abstract class BaseParser {
    public abstract void buildSetDeposits(String fileName);
    public abstract Set<Deposit> getDeposits();
}
