package org.thinhdev.thebankproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo {

    @Schema(
            description = "Unique identifier for the user account",
            example = "123456789"
    )
    private String accountNumber;

    @Schema(
            description = "Name associated with the user account",
            example = "John Doe"
    )
    private String accountName;

    @Schema(
            description = "Current balance in the user account",
            example = "1500.75"
    )
    private BigDecimal accountBalance;
}
