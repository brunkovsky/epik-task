package org.epik.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetHistoryRequest {

    private EpikFilter filter;
    private EpikPageable page;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EpikFilter {
        private String endpoint;
        private Date dateStart;
        private Date dateEnd;
        private String type;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EpikPageable {
        private Integer pageNumber;
        private Integer pageSize;
    }

}
