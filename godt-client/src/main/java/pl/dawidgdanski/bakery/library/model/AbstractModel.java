package pl.dawidgdanski.bakery.library.model;

abstract class AbstractModel implements Model {

    private final int databaseId;

    protected AbstractModel(int databaseId) {
        this.databaseId = databaseId;
    }


    @Override
    public int getDatabaseId() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
