package api_pets.api_pets_data_store.entity;

import api_pets.api_pets_data_store.types.PetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;
    
    private String name;
    private String breed;

    @Column(name = "user_id")
    private Long userId;

    public Pet(Long id, PetType type, String name, String breed, Long userId){
        
        this.id = id;
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.userId = userId;


    }


    public Long getId(){
        return this.id;
    }


}
