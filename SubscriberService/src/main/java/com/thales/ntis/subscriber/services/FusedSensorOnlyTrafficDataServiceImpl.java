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
// UNIT OR MODEL ELEMENT NAME: FusedSensorOnlyTrafficDataServiceImpl.java
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

import com.thales.ntis.subscriber.datex.BasicData;
import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.ElaboratedData;
import com.thales.ntis.subscriber.datex.ElaboratedDataPublication;
import com.thales.ntis.subscriber.datex.LocationByReference;
import com.thales.ntis.subscriber.datex.TrafficConcentration;
import com.thales.ntis.subscriber.datex.TrafficFlow;
import com.thales.ntis.subscriber.datex.TrafficHeadway;
import com.thales.ntis.subscriber.datex.TrafficSpeed;
import com.thales.ntis.subscriber.datex.TravelTimeData;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class FusedSensorOnlyTrafficDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(FusedSensorOnlyTrafficDataServiceImpl.class);
    private static final String PUBLICATION_TYPE = "Fused Sensor-Only Traffic Data Publication";

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {

        LOG.info(PUBLICATION_TYPE + ": received...");

        ElaboratedDataPublication elaboratedDataPublication = null;

        try {
            elaboratedDataPublication = (ElaboratedDataPublication) d2LogicalModel.getPayloadPublication();
            if (elaboratedDataPublication != null) {
                List<ElaboratedData> elaboratedDataList = elaboratedDataPublication.getElaboratedData();
                
                LOG.info("Number of data items in the publication: " + elaboratedDataList.size());
                
                for(ElaboratedData dataItem : elaboratedDataList) {
                    extractTrafficDataFromElaboratedData(dataItem);
                }
                LOG.info(PUBLICATION_TYPE + ": processed successfully.");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void extractTrafficDataFromElaboratedData(ElaboratedData dataItem) {

        // Location is always specified as LocationByReference (referenced to a
        // single Network Link)
        LocationByReference location = (LocationByReference) dataItem.getBasicData().getPertinentLocation();
        LOG.info("Data for Network Link: " + location.getPredefinedLocationReference().getId());

        BasicData basicData = dataItem.getBasicData();

        // Determine what class (type) of traffic data is contained in the basic
        // data
        if (TrafficSpeed.class.equals(basicData.getClass())) {
            TrafficSpeed speed = (TrafficSpeed) basicData;
            LOG.info("Average Speed: " + speed.getAverageVehicleSpeed().getSpeed());

        } else if (TravelTimeData.class.equals(basicData.getClass())) {
            TravelTimeData travelTimeData = (TravelTimeData) basicData;
            LOG.info("Travel Time: " + travelTimeData.getTravelTime().getDuration());
            LOG.info("Free Flow Travel Time: " + travelTimeData.getFreeFlowTravelTime().getDuration());
            LOG.info("Normally Expected Travel Time: " + travelTimeData.getNormallyExpectedTravelTime().getDuration());

        } else if (TrafficFlow.class.equals(basicData.getClass())) {
            TrafficFlow flow = (TrafficFlow) basicData;
            LOG.info("Traffic Flow: " + flow.getVehicleFlow().getVehicleFlowRate());

        } else if (TrafficConcentration.class.equals(basicData.getClass())) {
            TrafficConcentration concentration = (TrafficConcentration) basicData;
            LOG.info("Occupancy (%age): " + concentration.getOccupancy().getPercentage());

        } else if (TrafficHeadway.class.equals(basicData.getClass())) {
            TrafficHeadway headway = (TrafficHeadway) basicData;
            LOG.info("Headway: " + headway.getAverageTimeHeadway().getDuration());

        } else {
            LOG.error("Unexpected traffic data type contained in publication: " + dataItem.getClass().getSimpleName());
        }
    }
}