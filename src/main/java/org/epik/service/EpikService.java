package org.epik.service;

import lombok.AllArgsConstructor;
import org.epik.model.dto.EpikHistoryDTO;
import org.epik.model.dto.GetHistoryRequest;
import org.epik.exception.EpikNotFoundException;
import org.epik.model.repo.EpikHistory;
import org.epik.model.repo.EpikType;
import org.epik.repository.EpikHistoryRepository;
import org.epik.repository.EpikTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EpikService {

    private final EpikHistoryRepository historyRepository;
    private final EpikTypeRepository typeRepository;


    public List<EpikHistoryDTO> findAll() {
        return historyRepository.findAll().stream()
                .map(this::epikHistoryToEpikHistoryDto)
                .collect(Collectors.toList());
    }

    public EpikHistoryDTO findById(int id) {
        return historyRepository.findById(id)
                .map(this::epikHistoryToEpikHistoryDto)
                .orElseThrow(() -> new EpikNotFoundException("Not found by id: " + id));
    }

    public Page<EpikHistoryDTO> getHistoryForUserLogin(String userLogin, GetHistoryRequest request) {
        GetHistoryRequest.EpikPageable page = request.getPage();
        Pageable pageable = page == null
                ? Pageable.unpaged()
                : PageRequest.of(page.getPageNumber(), page.getPageSize());

        GetHistoryRequest.EpikFilter filter = request.getFilter();
        if (filter.getDateStart() == null) {
            filter.setDateStart(new Date(0));
        }
        if (filter.getDateEnd() == null) {
            filter.setDateEnd(new Date(10_000_000_000_000L));    // Nov 20 2286 17:46:40 as MAX dateEnd
        }

        return historyRepository.findAllByUserLogin(
                        filter.getEndpoint(),
                        userLogin,
                        filter.getType() == null ? null : getTypeIdByType(filter.getType()),
                        filter.getDateStart(),
                        filter.getDateEnd(),
                        pageable)
                .map(this::epikHistoryToEpikHistoryDto);
    }

    public void save(EpikHistoryDTO epikHistoryDTO) {
        EpikHistory epikModel = epikHistoryDtoToEpikHistory(epikHistoryDTO);
        historyRepository.save(epikModel);
    }

    public void deleteById(int id) {
        historyRepository.deleteById(id);
    }

    private Integer getTypeIdByType(String type) throws IllegalArgumentException {
        List<EpikType> allEpikTypes = typeRepository.findAll();
        Optional<EpikType> first = allEpikTypes.stream().filter(x -> x.getActivityType().equals(type)).findFirst();
        if (first.isEmpty()) {
            throw new IllegalArgumentException("No type found: " + type + ". Allowed: " + allEpikTypes);
        }
        return first.get().getId();
    }

    private EpikHistory epikHistoryDtoToEpikHistory(EpikHistoryDTO epikHistoryDTO) {
        return EpikHistory.builder()
                .epikType(EpikType.builder().id(getTypeIdByType(epikHistoryDTO.getEpikType())).build())
                .endpoint(epikHistoryDTO.getEndpoint())
                .eventDate(epikHistoryDTO.getEventDate())
                .userLogin(epikHistoryDTO.getUserLogin()).build();
    }

    private EpikHistoryDTO epikHistoryToEpikHistoryDto(EpikHistory epikHistory) {
        return EpikHistoryDTO.builder()
                .endpoint(epikHistory.getEndpoint())
                .userLogin(epikHistory.getUserLogin())
                .epikType(epikHistory.getEpikType().getActivityType())
                .eventDate(epikHistory.getEventDate())
                .build();
    }

}
