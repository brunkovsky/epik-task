package org.epik.controller;

import lombok.AllArgsConstructor;
import org.epik.annotation.LogTimeExecution;
import org.epik.model.dto.EpikHistoryDTO;
import org.epik.model.dto.GetHistoryRequest;
import org.epik.service.EpikService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${server.host}")
@AllArgsConstructor
public class EpikController {

    private final EpikService service;

    @GetMapping("/findAll")
    public List<EpikHistoryDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public EpikHistoryDTO findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @LogTimeExecution
    @PostMapping("/getHistoryForUser/{userLogin}")
    public Page<EpikHistoryDTO> getHistoryForUser(@PathVariable("userLogin") String userLogin,
                                                  @RequestBody GetHistoryRequest request) {
        return service.getHistoryForUserLogin(userLogin, request);
    }

    @LogTimeExecution
    @PostMapping("/save")
    public void save(@RequestBody @Valid EpikHistoryDTO epikHistoryDTO) {
        service.save(epikHistoryDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
    }

}
