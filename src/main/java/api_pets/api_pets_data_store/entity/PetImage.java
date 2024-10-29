package api_pets.api_pets_data_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet_images")
@Data
@NoArgsConstructor
public class PetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Integer verified;

    @Column(name = "pet_id", nullable = true)
    private Long petId;


    public PetImage(Long id, String name, String url, Integer verified, Long petId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.verified = verified;
        this.petId = petId;
    }


    public Long getId(){
        return this.id;
    }

}
