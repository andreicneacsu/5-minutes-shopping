package com.unibuc.fraudservice.action;

import org.springframework.web.bind.annotation.RequestBody;

import com.unibuc.fraudservice.model.PassAuthorizationRequest;
import com.unibuc.fraudservice.model.PassAuthorizationResponse;

public interface GatewayAction {

    PassAuthorizationResponse passAuthorization(@RequestBody PassAuthorizationRequest request);
}
