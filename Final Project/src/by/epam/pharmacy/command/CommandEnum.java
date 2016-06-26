package by.epam.pharmacy.command;
import by.epam.pharmacy.command.*;

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
    REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },
    OPENPAGE{
        {
            this.command = new OpenPageCommand();
        }
    },
    DRUGLIST{
        {
            this.command = new DrugListCommand();
        }
    },
    ADDDRUG{
        {
            this.command = new AddDrugCommand();
        }
    },
    DELETEDRUG{
        {
            this.command = new DeleteDrugCommand();
        }
    },
    DRUGSELECT{
        {
            this.command = new ModDrugSelectCommand();
        }
    },
    MODDRUGCOMMIT{
        {
            this.command = new ModDrugCommitCommand();
        }
    },
    OPENBASKET{
        {
            this.command = new OpenBasketCommand();
        }
    },
    ADDTOBASKET{
        {
            this.command = new AddToBasketCommand();
        }
    },
    MAKEORDER{
        {
            this.command = new MakeOrderCommand();
        }
    },
    OPENDOCTORPAGE {
        {
            this.command = new OpenDoctorPageCommand();
        }
    },
    CONFIRMRECIPES{
        {
            this.command = new ConfirmRecipesCommand();
        }
    },
    OPENDRUGPAGE{
        {
            this.command = new OpenDrugPageCommand();
        }
    },
    SEARCHDRUGGIST{
        {
            this.command = new SearchDruggistCommand();
        }
    },
    MANAGEORDERS{
        {
            this.command = new ManageOrdersCommand();
        }
    },
    CONFIRMORDERS{
        {
            this.command = new ConfirmOrdersCommand();
        }
    },
    OPENRECIPEPAGE{
        {
            this.command = new OpenRecipePageCommand();
        }
    },
    REQUESTRECIPE{
        {
            this.command = new RequestRecipeCommand();
        }
    },
    CONFIRMRECIPEREQUEST{
        {
            this.command = new ConfirmRecipeRequestCommand();
        }
    },
    OPENRECIPECONFIRMATIONPAGE{
        {
            this.command = new OpenRecipeConfirmationPageCommand();
        }
    },
    ADDRECIPE{
        {
            this.command = new AddRecipeCommand();
        }
    },
    SEARCHORDER{
        {
            this.command = new SearchOrderCommand();
        }
    },
    CHECKOUTORDER{
        {
            this.command = new CheckoutOrderCommand();
        }
    },
    ADDPRODUCT{
        {
            this.command = new AddProductCommand();
        }
    },
    BACK{
        {
            this.command = new BackCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}