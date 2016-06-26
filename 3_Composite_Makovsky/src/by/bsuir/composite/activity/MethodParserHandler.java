package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.entity.Leaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class MethodParserHandler extends AbstractHandler {
    private final String REGEX_METHOD = "(((@)[A-z]*)?(\\s)*(public|protected|private)( )[\\w <>,]+( )[\\w <>,]+(\\()[^\\}]*)";
    public MethodParserHandler() {
        setType(CompositeType.METHOD);
        LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
        addSuccessor(lexemeParserHandler);
        OperatorParserHandler operatorParserHandler = new OperatorParserHandler();
        addSuccessor(operatorParserHandler);
    }

    @Override
    public void parse(String text) {
        Pattern methodPattern = Pattern.compile(REGEX_METHOD);
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
                else {
                    element.add(handler.chain(group));
                }
            }
        }
    }
}
