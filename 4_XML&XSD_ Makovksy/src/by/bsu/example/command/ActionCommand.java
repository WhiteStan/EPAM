package by.bsu.example.command;
import javax.servlet.http.HttpServletRequest;
import by.bsu.example.command.client.CommandEnum;
public abstract class ActionCommand {
    public abstract String execute(HttpServletRequest request);
}