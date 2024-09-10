package com.apicela.engsoft.utils;

import com.apicela.engsoft.models.PropertyType;
import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.repositories.ApartmentRepository;
import com.apicela.engsoft.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // Injete seus repositórios ou serviços necessários
    private final HouseRepository houseRepository;
    private final ApartmentRepository apartmentRepository;

    private final Faker faker = new Faker();
    // Construtor para injeção de dependência

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setStreet("Rua dos bobos");
        List<String> names = new ArrayList<>(Arrays.asList("Apicela", "Campos", "Costa", "Martins"));
        for (int i = 0; i < 2; i++) {
            Apartment apartment = Apartment.builder()
                    .description(names.get(names.size() - i - 1) + " Penthouse")
                    .rentValue((float) faker.number().randomDouble(2, 700, 4333))
                    .address(address)
                    .condominiumValue(1500f)
                    .propertyType(PropertyType.APARTAMENTO)
                    .build();

            House house = House.builder()
                    .description(names.get(i) + " House")
                    .rentValue((float) faker.number().randomDouble(2, 700, 4333))
                    .address(address)
                    .propertyType(PropertyType.CASA)
                    .build();

            apartmentRepository.save(apartment);
            houseRepository.save(house);

        }

        System.out.println("Dados iniciais foram popularizados!");
    }

}