package com.unibuc.fraudservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassAuthorizationRequest {

    private String requestId;

    private GateAuthenticationEvent authenticationEvent;

    private Long storeId;

    private Long shopperId;
}
