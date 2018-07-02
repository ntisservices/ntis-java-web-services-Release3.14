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
// UNIT OR MODEL ELEMENT NAME: TrafficDataServiceFactory.java
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
import com.thales.ntis.subscriber.model.FeedType;

public class TrafficDataServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficDataService.class);

    public static TrafficDataService newInstance(D2LogicalModel request) {

        String feedType = request.getPayloadPublication().getFeedType();

        LOG.info("FeedType is : " + feedType);

        if (feedType.toLowerCase().contains(FeedType.ANPR.lowerCase())) {
            return new ANPRTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.MIDAS.lowerCase())) {
            return new MIDASTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.TMU.lowerCase())) {
            return new TMUTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.FUSED_SENSOR_ONLY.lowerCase())) {
            return new FusedSensorOnlyTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.FUSED_FVD_AND_SENSOR_PTD.lowerCase())) {
            return new FusedFvdAndSensorTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.VMS.lowerCase())) {
            return new VMSTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.NTIS_MODEL_UPDATE_NOTIFICATION.lowerCase())) {
            return new NtisModelNotificationDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.EVENT_DATA.lowerCase())) {
            return new EventDataServiceImpl();

        } else {
            LOG.error("Unrecognised Feed Type: " + feedType);
            return null;
        }
    }
}
