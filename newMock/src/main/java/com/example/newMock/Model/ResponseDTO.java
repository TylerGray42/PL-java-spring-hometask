package com.example.newMock.Model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;
}
