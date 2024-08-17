package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    Random random = new Random();

    @PostMapping(
        value = "/info/postBalances",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            String currency;
            BigDecimal maxLimit;

            switch (firstDigit) {
                case '8':
                    currency = "US";
                    maxLimit = new BigDecimal(2000);
                    break;
                case '9':
                    currency = "EU";
                    maxLimit = new BigDecimal(1000);
                    break;
                default:
                    currency = "RUB";
                    maxLimit = new BigDecimal(10000);
            }

            BigDecimal randomBalance = maxLimit
                .multiply(BigDecimal.valueOf(random.nextDouble()))
                .setScale(2, RoundingMode.UP);

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(requestDTO.getClientId());
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setBalance(randomBalance);
            responseDTO.setCurrency(currency);
            responseDTO.setMaxLimit(maxLimit);

            log.info(
                "\n========== RequestDTO ==========" +
                mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(requestDTO)
            );

            log.info(
                "\n========== ResponseDTO ==========" +
                mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(responseDTO)
            );

            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        }
    }
}
