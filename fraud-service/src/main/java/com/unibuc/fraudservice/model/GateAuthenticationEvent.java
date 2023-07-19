package com.unibuc.fraudservice.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class GateAuthenticationEvent {

    private String id;

    @Enumerated(EnumType.STRING)
    private GateLocation location;

    @Enumerated(EnumType.STRING)
    private AuthenticationEventType type;

    private Date timestamp;
}
