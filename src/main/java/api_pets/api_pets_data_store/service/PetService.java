package api_pets.api_pets_data_store.service;

import api_pets.api_pets_data_store.entity.Pet;
import api_pets.api_pets_data_store.entity.PetImage;
import api_pets.api_pets_data_store.repository.PetImageRepository;
import api_pets.api_pets_data_store.repository.PetRepository;
import api_pets.api_pets_data_store.types.PetType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;


@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetImageRepository petImageRepository;
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public PetService(PetRepository petRepository, PetImageRepository petImageRepository, ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.petRepository = petRepository;
        this.objectMapper = objectMapper;
        this.petImageRepository = petImageRepository;
    }

    @RabbitListener(queues = "pet.identified")
    public void handleAnimalIdentified(String message) {
        try {

            JsonNode node = objectMapper.readTree(message);
            Long petId = node.has("petId") && !node.get("petId").isNull() 
            ? node.get("petId").asLong() 
            : null; 

            if( petId == null){

                String name = "no name";
                String breed = node.get("breed").asText();
                Long userId = node.get("userId").asLong();
                String typeString = node.get("type").asText();
                PetType type = PetType.fromString(typeString);
                Pet pet = new Pet(null, type, name, breed, userId);
                petRepository.save(pet);
                petId = pet.getId();

            }

            String url = node.get("imageUrl").asText();
            Integer verified = 1;
            PetImage petImage = new PetImage(null, "", url, verified, petId);
            petImageRepository.save(petImage);


            Long petImageId = petImage.getId();
            
            System.out.println("Pet Image Stored: " + petImage);

            Map<String, Object> messageToSend = new HashMap<>();
            messageToSend.put("message", "Pet Data Stored Successfully");
            messageToSend.put("petImageId", petImageId);
            messageToSend.put("petId", petId);

            String jsonMessage = objectMapper.writeValueAsString(messageToSend);

            System.out.println(messageToSend);

            rabbitTemplate.convertAndSend("pet.stored", jsonMessage);

            

        } catch (Exception e) {
            System.err.println("An error ocurred procesing the message : " + e.getMessage());
        }
    }


    

}
