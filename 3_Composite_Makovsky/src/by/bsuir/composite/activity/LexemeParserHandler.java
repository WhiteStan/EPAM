package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.entity.Leaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 20.03.2016.
 */
public class LexemeParserHandler extends AbstractHandler {
    private final String REGEX_LEXEME = "([^\\s]+)";

    public LexemeParserHandler() {
        setType(CompositeType.LEXEME);
    }

    @Override
    public void parse(String text) {
        Pattern classPattern = Pattern.compile(REGEX_LEXEME);
        Matcher classMatcher = classPattern.matcher(text);
        while (classMatcher.find()) {
            String group = classMatcher.group();
            Leaf leaf = new Leaf(group, CompositeType.LEXEME);
            element.add(leaf);
        }
    }
}


