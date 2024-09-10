package com.apicela.engsoft.utils;

import com.apicela.engsoft.dtos.ApartmentDTO;
import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.PropertyType;
import com.apicela.engsoft.services.ApartmentService;
import com.apicela.engsoft.services.HouseService;
import com.apicela.engsoft.utils.converter.ImageHelper;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // Injete seus repositórios ou serviços necessários
    private final HouseService houseService;
    private final ApartmentService apartmentService;

    private final Faker faker = new Faker();
    // Construtor para injeção de dependência

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setStreet("Rua dos bobos");
        address.setCep("31000333");
        address.setCity("Belo Horizonte");
        address.setNeighborhood(faker.dragonBall().character());
        address.setState("MG");
        Set<Image> images = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            var imageBase64 = ImageHelper.base64ToByteArray(faker.image().base64JPG());
            Image image = Image.builder()
                    .imageType("image/jpg")
                    .imageData(imageBase64)
                    .build();
            image.setId(null);
            images.add(image);
        }
//        / faker.avatar().image()
        List<String> names = new ArrayList<>(Arrays.asList("Apicela", "Campos", "Costa", "Martins"));
        for (int i = 0; i < 2; i++) {
            ApartmentDTO apartment = ApartmentDTO.builder()
                    .description(names.get(names.size() - i - 1) + " Penthouse")
                    .rentValue((float) faker.number().randomDouble(2, 700, 4333))
                    .address(address)
                    .imagesSet(images)
                    .condominiumValue(1500f)
                    .propertyType(PropertyType.APARTAMENTO)
                    .build();

            HouseDTO house = HouseDTO.builder()
                    .description(names.get(i) + " House")
                    .rentValue((float) faker.number().randomDouble(2, 700, 4333))
                    .address(address)
                    .imagesSet(images)
                    .propertyType(PropertyType.CASA)
                    .build();

            apartmentService.save(apartment);
            houseService.save(house);

        }

        System.out.println("Dados iniciais foram popularizados!");
    }

}