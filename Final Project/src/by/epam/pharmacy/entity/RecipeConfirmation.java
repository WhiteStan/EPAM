package by.epam.pharmacy.entity;

/**
 * Created by Lenovo on 11.06.2016.
 */
public class RecipeConfirmation {
    private boolean confirmation;
    private int Id;

    public RecipeConfirmation(boolean confirmation, int id) {
        this.confirmation = confirmation;
        Id = id;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
