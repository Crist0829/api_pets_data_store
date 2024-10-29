package api_pets.api_pets_data_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api_pets.api_pets_data_store.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
