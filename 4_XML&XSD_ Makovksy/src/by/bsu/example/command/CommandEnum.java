package by.bsu.example.command.client;
import by.bsu.example.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    LOCALE{
        {
            this.command = new LocaleCommand();
        }
    },
    PARSE{
        {
            this.command = new ParseCommand();
        }
    },
    RETURN{
        {
            this.command = new ReturnCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}