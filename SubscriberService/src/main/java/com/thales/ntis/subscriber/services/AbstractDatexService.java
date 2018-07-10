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
// UNIT OR MODEL ELEMENT NAME: AbstractDatexService.java
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

import com.thales.ntis.subscriber.datex.D2LogicalModel;

public abstract class AbstractDatexService {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDatexService.class);

    public boolean validate(D2LogicalModel request) {

        // D2LogicalModel is at the base element of the request so must not be
        // null.
        if (request != null) {

            if (LOG.isDebugEnabled()) {
                LOG.debug("D2LogicalModel is " + request);
            }

        } else {
            LOG.error("D2LogicalModel is null! Incoming request does not appear to be valid!");
            return false;
        }

        // Exchange must not be null.
        if (request.getExchange() != null) {
            LOG.info("Country is "
                    + request.getExchange().getSupplierIdentification()
                            .getCountry().value());
            LOG.info("National Identifier is "
                    + request.getExchange().getSupplierIdentification()
                            .getNationalIdentifier());
        } else {
            LOG.error("Exchange is null! Incoming request does not appear to be valid!");
            return false;
        }
        return true;
    }

}
