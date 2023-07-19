package com.unibuc.fraudservice.controller;

import com.unibuc.fraudservice.action.GatewayAction;
import com.unibuc.fraudservice.factory.GatewayActionFactory;
import com.unibuc.fraudservice.model.PassAuthorizationRequest;
import com.unibuc.fraudservice.model.PassAuthorizationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passauthorization")
public class PassAuthorizationController {

    private Logger log = LogManager.getLogger(PassAuthorizationController.class);

    private GatewayActionFactory gatewayActionFactory;

    @Autowired
    public PassAuthorizationController(GatewayActionFactory gatewayActionFactory) {
        this.gatewayActionFactory = gatewayActionFactory;
    }

    @PostMapping
    public PassAuthorizationResponse passAuthorization(@RequestBody PassAuthorizationRequest request) {
        log.info(String.format("Entered PassAuthorizationController with request: %s...", request));

        GatewayAction gatewayAction = gatewayActionFactory.getGatewayAction(request.getAuthenticationEvent().getLocation());

        try {
            PassAuthorizationResponse response = gatewayAction.passAuthorization(request);
            log.info(String.format("Successfully retrieved PassAuthorizationResponse: %s", response));
            return response;
        } catch (Exception e) {
            log.error(String.format("Error when getting gateway response: %s", e));
            throw e;
        }
    }

}