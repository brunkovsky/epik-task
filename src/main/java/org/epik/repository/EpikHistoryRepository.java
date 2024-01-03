package org.epik.repository;

import org.epik.model.repo.EpikHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface    EpikHistoryRepository extends JpaRepository<EpikHistory, Integer> {

    @Query(value = "SELECT * " +
            "FROM epik.history " +
            "WHERE (:endpoint IS NULL OR endpoint = :endpoint)" +
            "AND (user_login = :userLogin)" +
            "AND (:typeId IS NULL OR type_id = :typeId)" +
            "AND (event_date BETWEEN :dateStart AND :dateEnd)", nativeQuery = true)
    Page<EpikHistory> findAllByUserLogin(@Param("endpoint") String endpoint,
                                      @Param("userLogin") String userLogin,
                                      @Param("typeId") Integer typeId,
                                      @Param("dateStart") Date dateStart,
                                      @Param("dateEnd") Date dateEnd,
                                      Pageable pageable);

}
