package M3208.MihailMM.Models;

public class Address {
    private String Street;
    private int BuildingNumber;

    public Address(String street, int buildingNumber) {
        Street = street;
        BuildingNumber = buildingNumber;
    }

    public String getStreet() {
        return Street;
    }

    public int getBuildingNumber() {
        return BuildingNumber;
    }
}
