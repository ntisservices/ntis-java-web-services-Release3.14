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
// UNIT OR MODEL ELEMENT NAME: ANPRTrafficDataServiceImpl.java
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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.MeasuredDataPublication;
import com.thales.ntis.subscriber.datex.MeasuredValue;
import com.thales.ntis.subscriber.datex.SiteMeasurements;
import com.thales.ntis.subscriber.datex.TravelTimeData;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class ANPRTrafficDataServiceImpl implements
        TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(ANPRTrafficDataServiceImpl.class);
    private static final String PUBLICATION_TYPE = "ANPR Publication";

    @Override
    public void handle(
            D2LogicalModel d2LogicalModel) {

        LOG.info(PUBLICATION_TYPE + ": received...");

        MeasuredDataPublication measuredDataPublication = null;

        try {
            measuredDataPublication = (MeasuredDataPublication) d2LogicalModel.getPayloadPublication();
            if (measuredDataPublication != null) {
                List<SiteMeasurements> siteMeasurementsInPayload = measuredDataPublication.getSiteMeasurements();

                LOG.info("Number of Site Measurements in payload: " + siteMeasurementsInPayload.size());

                for (SiteMeasurements measurementsForSite : siteMeasurementsInPayload) {
                    extractTravelTimesFromSiteMeasurements(measurementsForSite);
                }
                LOG.info(PUBLICATION_TYPE + ": processed successfully.");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void extractTravelTimesFromSiteMeasurements(SiteMeasurements siteMeasurements) {
        String anprRouteId = siteMeasurements.getMeasurementSiteReference().getId();
        // Should only be one travel time value per SiteMeasurements
        // element (index=0)
        MeasuredValue value = siteMeasurements.getMeasuredValue().get(0).getMeasuredValue();
        if (TravelTimeData.class.equals(value.getBasicData().getClass()))
        {
            TravelTimeData ttData = (TravelTimeData) value.getBasicData();
            LOG.info("Travel Time for ANPR Route " + anprRouteId + " : " + ttData.getTravelTime().getDuration() + "s");
        }
    }
}