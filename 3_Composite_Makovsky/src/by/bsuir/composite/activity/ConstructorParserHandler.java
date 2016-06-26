package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 28.03.2016.
 */
public class ConstructorParserHandler extends AbstractHandler {
    private final String REGEX_CONSTRUCTOR = "((public|protected|private)( )[A-z]*(\\()[A-z )]*[^\\}]*)";
    public ConstructorParserHandler() {
        setType(CompositeType.CONSTRUCTOR);
        LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
        addSuccessor(lexemeParserHandler);
        OperatorParserHandler operatorParserHandler = new OperatorParserHandler();
        addSuccessor(operatorParserHandler);
    }

    @Override
    public void parse(String text) {
        Pattern methodPattern = Pattern.compile(REGEX_CONSTRUCTOR);
        Matcher methodMatcher = methodPattern.matcher(text);
        while (methodMatcher.find()) {
            String group = methodMatcher.group();
            for(AbstractHandler handler : listOfSuccessors){
                if(handler instanceof OperatorParserHandler) {
                    element.add(handler.chain(group.substring(group.indexOf('{') + 1)));
                    LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
                    element.add(lexemeParserHandler.chain(" }"));
                }
                else if(handler instanceof LexemeParserHandler){
                    element.add(handler.chain(group.substring(0, group.indexOf('{')+1)));
                }
            }
        }
    }
}
