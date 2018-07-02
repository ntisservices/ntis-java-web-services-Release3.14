///////////////////////////////////////////////////////////////
//
// Copyright (c) Thales UK Limited 2018, All Rights Reserved
// The copyright herein, subject to any pre-existing rights of third parties, is the property of Thales UK Limited.
// It may not be sold, licensed, reproduced, modified, adapted, published, disclosed or translated in any material
// form (including storage in any medium by electronic means whether or not transiently or incidentally) in whole
// or in part without the prior written permission of Thales UK Limited neither shall it be used other than for the
// purpose for which it is supplied.
//
///////////////////////////////////////////////////////////////
//
// PROJECT REFERENCE: NTIS
//
// COMPONENT NAME: ntis-subscriberservice
//
// UNIT OR MODEL ELEMENT NAME: SubscriberServiceEndpoint.java
//
// CONFIGURATION NAME: ntis-subscriberservice
//
// OVERVIEW: See Javadoc
//
///////////////////////////////////////////////////////////////
//
// CLASSIFICATION
//  1) Country of Origin: UK
//  2) Classification: OFFICIAL
//
///////////////////////////////////////////////////////////////

package com.thales.ntis.subscriber.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.services.DatexIIService;

/**
 * This is a reference SubscriberServiceEndpoint. Business logic is delegated to
 * separate service classes.
 */

@Endpoint("subscriberServiceEndpoint")
public class SubscriberServiceEndpoint {

    private Logger log = LoggerFactory.getLogger(SubscriberServiceEndpoint.class);

    @Autowired
    private DatexIIService datexIIService;

    @PayloadRoot(namespace = "http://datex2.eu/schema/2/2_0", localPart = "d2LogicalModel")
    public void handle(@RequestPayload D2LogicalModel request) {
        log.info("Received request for subscription");
        datexIIService.handle(request);
    }
}
