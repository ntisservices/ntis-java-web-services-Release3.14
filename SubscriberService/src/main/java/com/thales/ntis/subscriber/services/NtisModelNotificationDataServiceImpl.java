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
// UNIT OR MODEL ELEMENT NAME: NtisModelNotificationDataServiceImpl.java
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
import com.thales.ntis.subscriber.datex.GenericPublication;
import com.thales.ntis.subscriber.datex.GenericPublicationExtensionType;
import com.thales.ntis.subscriber.datex.NtisModelVersionInformation;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class NtisModelNotificationDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(NtisModelNotificationDataServiceImpl.class);
    private static final String PUBLICATION_TYPE = "NTIS Model Update Notification";

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {

        LOG.info(PUBLICATION_TYPE + ": received...");

        GenericPublication genericPublication = null;

        try {
            genericPublication = (GenericPublication) d2LogicalModel.getPayloadPublication();
            
            if (genericPublication != null) {
                GenericPublicationExtensionType genericPublicationExtension = genericPublication.getGenericPublicationExtension();
                NtisModelVersionInformation ntisModelVersionInformation = genericPublicationExtension
                        .getNtisModelVersionInformation();
                LOG.info("Model file name: " + ntisModelVersionInformation.getModelFilename());
                LOG.info("Model version: v" + ntisModelVersionInformation.getModelVersion());
                LOG.info("Model publication time: " + ntisModelVersionInformation.getModelPublicationTime());

                LOG.info(PUBLICATION_TYPE + ": processed successfully.");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
