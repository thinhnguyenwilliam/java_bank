package org.thinhdev.thebankproject.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinhdev.thebankproject.dto.AccountInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;
}
