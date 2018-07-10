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
// UNIT OR MODEL ELEMENT NAME: DatexIIService.java
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

package com.thales.ntis.subscriber.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;

@Service
public class DatexIIService extends AbstractDatexService {

    private static final Logger LOG = LoggerFactory.getLogger(DatexIIService.class);

    public synchronized void handle(D2LogicalModel request) {
        LOG.info("Validate D2Logical Model - Started");

        if (!validate(request)) {
            LOG.info("D2Logical Model is not valid");
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }

        LOG.info("Validate D2Logical Model - Completed Successfuly");

        TrafficDataServiceFactory.newInstance(request).handle(request);

    }
}
