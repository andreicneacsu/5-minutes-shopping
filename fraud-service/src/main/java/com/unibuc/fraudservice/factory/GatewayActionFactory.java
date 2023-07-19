package com.unibuc.fraudservice.factory;

import com.unibuc.fraudservice.action.EntryGateway;
import com.unibuc.fraudservice.action.ExitGateway;
import com.unibuc.fraudservice.action.GatewayAction;
import com.unibuc.fraudservice.exception.UnknownGatewayException;
import com.unibuc.fraudservice.model.GateLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GatewayActionFactory {

    private static final Logger log = LogManager.getLogger(GatewayActionFactory.class);

    private EntryGateway entryGateway;

    private ExitGateway exitGateway;

    @Autowired
    public GatewayActionFactory(EntryGateway entryGateway, ExitGateway exitGateway) {
        this.entryGateway = entryGateway;
        this.exitGateway = exitGateway;
    }

    public GatewayAction getGatewayAction(GateLocation location) {
        log.info("Getting gateway action...");
        switch (location) {
            case ENTRY:
                log.info("Returning ENTRY gateway location.");
                return entryGateway;
            case EXIT:
                log.info("Returning EXIT gateway location.");
                return exitGateway;
            default:
                log.warn(String.format("Unknown gateway: %s", location));
                throw new UnknownGatewayException(String.format("Unknown gateway: %s", location));
        }
    }
}
