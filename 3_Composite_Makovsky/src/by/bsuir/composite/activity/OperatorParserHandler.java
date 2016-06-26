package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class OperatorParserHandler extends AbstractHandler {
    private final String REGEX_OPERATOR = "([^{};]+(?![^{]*})(;))";

    public OperatorParserHandler() {
        setType(CompositeType.OPERATOR);
        LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
        addSuccessor(lexemeParserHandler);
    }

    @Override
    public void parse(String text) {
        Pattern operatorPattern = Pattern.compile(REGEX_OPERATOR);
        Matcher operatorMatcher = operatorPattern.matcher(text);
        while (operatorMatcher.find()) {
            String group = operatorMatcher.group();
            for (AbstractHandler handler : listOfSuccessors) {
                element.add(handler.chain(group));
            }
        }
    }
}
