package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class FieldParserHandler extends AbstractHandler {
    private final String REGEX_FIELD = "((\\s)*(static)( )(\\{)[\\w\\s=();<>,]*)|((\\s){2}(\\{)[\\w\\s=();<>,]*)";

    public FieldParserHandler() {
        setType(CompositeType.LOGICAL_BLOCK);
        LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
        addSuccessor(lexemeParserHandler);
        OperatorParserHandler operatorParserHandler = new OperatorParserHandler();
        addSuccessor(operatorParserHandler);
    }

    @Override
    public void parse(String text) {
        Pattern classPattern = Pattern.compile(REGEX_FIELD);
        Matcher classMatcher = classPattern.matcher(text);
        while (classMatcher.find()) {
            String group = classMatcher.group();
            for (AbstractHandler handler : listOfSuccessors) {
                if (handler instanceof OperatorParserHandler) {
                    element.add(handler.chain(group.substring(group.indexOf('{') + 1)));
                    LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
                    element.add(lexemeParserHandler.chain(" }"));
                } else if (handler instanceof LexemeParserHandler) {
                    element.add(handler.chain(group.substring(0, group.indexOf('{') + 1)));
                }
            }
        }
    }
}
