package my.company;

import com.google.gson.Gson;
import feign.Response;
import feign.gson.GsonDecoder;
import my.company.api.PetFeignClientBuilder;
import my.company.api.client.PetStoreClient;
import my.company.api.model.Pet;
import my.company.api.model.ResponseMessage;
import my.company.api.model.StatusEnum;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PetTest {

    private PetStoreClient petStoreClient;

    @BeforeTest
    public void setup() {
        PetFeignClientBuilder feignClientBuilder = new PetFeignClientBuilder();
        petStoreClient = feignClientBuilder.getClient();
    }

    @Test
    public void getPetByIdTest() throws Exception {
        Response r = petStoreClient.getPet("789786644906577");
        Pet pet = (Pet) new GsonDecoder().decode(r, Pet.class);
        assertThat(pet.getName()).isEqualTo("doggie");
    }

    @Test
    public void getUnexistingPet() throws IOException {
        Response r = petStoreClient.getPet("0");
        ResponseMessage msg = new Gson().fromJson(r.body().toString(), ResponseMessage.class);
        assertThat(msg.getMessage()).isEqualTo("Pet not found");
    }

    @Test
    public void getPetByStatusTest() throws Exception {
        List<Pet> pets = petStoreClient.getPetByStatus(StatusEnum.AVAILABLE);
        assertThat(pets).extracting("status").contains(StatusEnum.AVAILABLE);
    }

    @Test
    public void addNewPet() {
        Pet newPet = new Pet()
                .id("12121212")
                .name("MyBulldog")
                .status(StatusEnum.AVAILABLE);
        Pet pet = petStoreClient.addPet(newPet);
        assertThat(pet.equals(newPet));
        System.out.println(pet.toString());
    }

    @Test
    public void deletePet() {
        String id = RandomStringUtils.randomNumeric(8); //generates random ID

        //add new Pet
        Pet newPet = new Pet()
                .id(id)
                .name("ToBeDeleted")
                .status(StatusEnum.AVAILABLE);
        Pet pet = petStoreClient.addPet(newPet);
        assertThat(pet.equals(newPet));

        //delete created Pet
        petStoreClient.deletePetByID(id);

        //verify that deleted Pet is not present in the list of available Pets
        List<Pet> pets = petStoreClient.getPetByStatus(StatusEnum.AVAILABLE);
        assertThat(pets).doesNotContain(newPet);
    }

}
