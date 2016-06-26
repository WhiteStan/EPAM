package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.entity.Leaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class TextParserHandler extends AbstractHandler {
    private final String REGEX_LISTING = "(((public|private|protected)(.)*(\\s*)(class)[A-z ]+(\\{)[^~]+))";
    public TextParserHandler() {
        setType(CompositeType.LISTING);
        ClassParserHandler classParserHandler = new ClassParserHandler();
        addSuccessor(classParserHandler);
    }

    @Override
    public void parse(String text) {
        OperatorParserHandler operatorParserHandler = new OperatorParserHandler();
        element.add(operatorParserHandler.chain(text.substring(0, text.indexOf('{')-1)));
        Pattern textPattern = Pattern.compile(REGEX_LISTING);
        Matcher textMatcher = textPattern.matcher(text);
        while (textMatcher.find()) {
            String group = textMatcher.group();
                for(AbstractHandler handler : listOfSuccessors){
                    element.add(handler.chain(group));
                }
        }
    }
}
