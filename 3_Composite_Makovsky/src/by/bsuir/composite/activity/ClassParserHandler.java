package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.entity.Leaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class ClassParserHandler extends AbstractHandler {
    private final String REGEX_CLASS = "(((.)*(\\s*)(class|interface)[A-z 0-9]+(\\{)))";

    public ClassParserHandler() {
        setType(CompositeType.CLASS);
        MethodParserHandler methodParserHandler = new MethodParserHandler();
        addSuccessor(methodParserHandler);
        FieldParserHandler fieldParserHandler = new FieldParserHandler();
        addSuccessor(fieldParserHandler);
        OperatorParserHandler operatorParserHandler = new OperatorParserHandler();
        addSuccessor(operatorParserHandler);
        ConstructorParserHandler constructorParserHandler = new ConstructorParserHandler();
        addSuccessor(constructorParserHandler);
    }

    @Override
    public void parse(String text) {
        Pattern classPattern = Pattern.compile(REGEX_CLASS);
        Matcher classMatcher = classPattern.matcher(text);
        while (classMatcher.find()) {
            int startOfClass = classMatcher.start();
            if (classMatcher.find()) {
                String group = text.substring(startOfClass, classMatcher.start());
                LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
                element.add(lexemeParserHandler.chain(group.substring(0, group.indexOf('{') + 1)));
                for (AbstractHandler handler : listOfSuccessors) {
                    element.add(handler.chain(group.substring(group.indexOf('{') + 1)));
                }
            } else {
                String group = text.substring(startOfClass);
                LexemeParserHandler lexemeParserHandler = new LexemeParserHandler();
                element.add(lexemeParserHandler.chain(group.substring(0, group.indexOf('{') + 1)));
                for (AbstractHandler handler : listOfSuccessors) {
                    element.add(handler.chain(group.substring(group.indexOf('{') + 1)));
                }
            }
        }
    }
}
