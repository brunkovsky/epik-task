package org.epik.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpikHistoryDTO {

    @NotEmpty(message = "endpoint is mandatory")
    private String endpoint;

    @NotEmpty(message = "userLogin is mandatory")
    private String userLogin;

    @NotEmpty(message = "epikType is mandatory")
    private String epikType;

    private Instant eventDate;

}
