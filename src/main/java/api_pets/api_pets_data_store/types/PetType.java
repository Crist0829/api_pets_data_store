package api_pets.api_pets_data_store.types;

public enum PetType {
    DOG,
    CAT,
    OTHER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static PetType fromString(String type) {
        
        return PetType.valueOf(type.toUpperCase());
    }
}