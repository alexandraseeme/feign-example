package my.company.api.client;

import feign.*;
import my.company.api.model.Pet;
import my.company.api.model.StatusEnum;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PetStoreClient {

        @RequestLine("GET /pet/{petId}")
        public Response getPet(@Param("petId") String petId);

        @RequestLine("GET /pet/findByStatus?status={status}")
        @Headers("Content-Type: application/json")
        public List<Pet> getPetByStatus(@Param(value = "status", expander = StatusEnumExpander.class) StatusEnum statusEnum);

        @RequestLine("POST /pet")
        @Headers({"Content-Type: application/json", "api_key: qwe345ert5567"})
        public Pet addPet(@RequestBody Pet pet);

        @RequestLine("PUT /pet")
        @Headers("Content-Type: application/json")
        public Pet updatePet(@RequestBody Pet pet);

        @RequestLine("DELETE /pet/{petId}")
        public void deletePetByID(@Param("petId") String petId);

        @RequestLine("DELETE /pet/{petId}/uploadImage")
        public Pet uploadImage(@Param("petId") String petId);


}
