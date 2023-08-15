package com.unibuc.fraudservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GateAuthenticationEvent {

    private String id;

    @Enumerated(EnumType.STRING)
    private GateLocation location;

    @Enumerated(EnumType.STRING)
    private AuthenticationEventType type;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date timestamp;
}
