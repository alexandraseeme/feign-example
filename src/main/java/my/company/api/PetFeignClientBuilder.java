package my.company.api;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import my.company.api.client.PetStoreClient;

public class PetFeignClientBuilder {

    private PetStoreClient petStoreClient = createClient(PetStoreClient.class, "http://petstore.swagger.io/v2");

    private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.FULL)
                .target(type, uri);
    }

    public PetStoreClient getClient(){
        return  petStoreClient;
    }
}
