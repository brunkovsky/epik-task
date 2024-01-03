package org.epik.config;

import org.epik.model.repo.EpikType;
import org.epik.repository.EpikHistoryRepository;
import org.epik.repository.EpikTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpikConfiguration {

    @Bean
    public CommandLineRunner mappingExamples(EpikTypeRepository epikTypeRepository,
                                            EpikHistoryRepository epikHistoryRepository) {
        return args -> {
            EpikType epikType1 = new EpikType(1, "scanner");
            EpikType epikType2 = new EpikType(2, "logowanie");

            epikTypeRepository.save(epikType1);
            epikTypeRepository.save(epikType2);

//            epikHistoryRepository.deleteAll();
//
//            epikHistoryRepository.save(EpikHistory.builder()
//                    .epikType(epikType1)
//                    .eventDate(Instant.now().minusSeconds(2000))
//                    .endpoint("endpoint1")
//                    .userLogin("user1")
//                    .build());
//            epikHistoryRepository.save(EpikHistory.builder()
//                    .epikType(epikType1)
//                    .eventDate(Instant.now().minusSeconds(1000))
//                    .endpoint("endpoint2")
//                    .userLogin("user2")
//                    .build());
//            epikHistoryRepository.save(EpikHistory.builder()
//                    .epikType(epikType2)
//                    .eventDate(Instant.now())
//                    .endpoint("endpoint3")
//                    .userLogin("user1")
//                    .build());
//            epikHistoryRepository.save(EpikHistory.builder()
//                    .epikType(epikType2)
//                    .eventDate(Instant.now().plusSeconds(1000))
//                    .endpoint("endpoint4")
//                    .userLogin("user2")
//                    .build());

        };
    }

}
