package by.epam.pharmacy.entity;

public class DrugOrdered {
    private String name;
    private int recipe;
    private boolean recipeNeed;
    private int amount;

    public DrugOrdered() {

    }

    public DrugOrdered(String name, int recipe, int amount) {
        this.name = name;
        this.recipe = recipe;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public boolean isRecipeNeed() {
        return recipeNeed;
    }

    public void setRecipeNeed(boolean recipeNeed) {
        this.recipeNeed = recipeNeed;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
