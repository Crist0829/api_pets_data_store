package api_pets.api_pets_data_store.repository;

import api_pets.api_pets_data_store.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImageRepository extends JpaRepository<PetImage, Long> {
    
}