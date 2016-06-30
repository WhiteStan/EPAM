package by.epam.pharmacy.entity;

public class Drug {
    private int idDrugs;
    private String name;
    private String internationalName;
    private int price;
    private int amount;
    private String measurementUnit;
    private int inStock;
    private boolean recipeNeed;
    private String description;

    public Drug() {

    }

    public Drug(String name, String internationalName, int price,
                String measurementUnit, int inStock, boolean recipeNeed, String description) {
        this.name = name;
        this.internationalName = internationalName;
        this.price = price;
        this.measurementUnit = measurementUnit;
        this.inStock = inStock;
        this.recipeNeed = recipeNeed;
        this.description = description;
    }

    public int getIdDrugs() {
        return idDrugs;
    }

    public void setIdDrugs(int idDrugs) {
        this.idDrugs = idDrugs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public boolean isRecipeNeed() {
        return recipeNeed;
    }

    public void setRecipeNeed(boolean recipeNeed) {
        this.recipeNeed = recipeNeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drug drug = (Drug) o;

        if (idDrugs != drug.idDrugs) return false;
        if (price != drug.price) return false;
        if (amount != drug.amount) return false;
        if (inStock != drug.inStock) return false;
        if (recipeNeed != drug.recipeNeed) return false;
        if (name != null ? !name.equals(drug.name) : drug.name != null) return false;
        if (internationalName != null ? !internationalName.equals(drug.internationalName) : drug.internationalName != null)
            return false;
        if (measurementUnit != null ? !measurementUnit.equals(drug.measurementUnit) : drug.measurementUnit != null)
            return false;
        return !(description != null ? !description.equals(drug.description) : drug.description != null);

    }

    @Override
    public int hashCode() {
        int result = idDrugs;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (internationalName != null ? internationalName.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + amount;
        result = 31 * result + (measurementUnit != null ? measurementUnit.hashCode() : 0);
        result = 31 * result + inStock;
        result = 31 * result + (recipeNeed ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
