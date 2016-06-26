package by.bsuir.composite.main;

import by.bsuir.composite.activity.JavaParser;
import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.util.LexemeDelete;
import by.bsuir.composite.util.LexemeSwap;
import by.bsuir.composite.util.SortOperators;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        JavaParser javaParser = new JavaParser();
        javaParser.parse("resources/Main.java");
        CompositeElement element = javaParser.getElement();
        javaParser.assembleText("resources/Assembled.java");
        LexemeSwap lexemeSwap = new LexemeSwap("resources/Swaped.java");
        lexemeSwap.swap(element);
        SortOperators sortOperators = new SortOperators("resources/SortedOpeators.java");
        sortOperators.sort(element);
        LexemeDelete lexemeDelete = new LexemeDelete("resources/DeletedLexemes.java");
        lexemeDelete.delete(element, 'p', 6);
    }
}
