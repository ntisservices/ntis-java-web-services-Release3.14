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
// UNIT OR MODEL ELEMENT NAME: FeedType.java
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

package com.thales.ntis.subscriber.model;

public enum FeedType {

    ANPR("ANPR Journey Time Data"),

    MIDAS("MIDAS Loop Traffic Data"),

    TMU("TMU Loop Traffic Data"),

    FUSED_SENSOR_ONLY("Fused Sensor-only PTD"),

    FUSED_FVD_AND_SENSOR_PTD("Fused FVD and Sensor PTD"),

    VMS("VMS and Matrix Signal Status Data"),

    NTIS_MODEL_UPDATE_NOTIFICATION("NTIS Model Update Notification"),

    EVENT_DATA("Event Data"),

    FULL_REFRESH("Event Data - Full Refresh");

    private String feedTypeText;

    FeedType(String feedTypeText) {
        this.feedTypeText = feedTypeText;
    }

    public String upperCase() {
        return value().toUpperCase();
    }

    public String lowerCase() {
        return value().toLowerCase();
    }

    public String value() {
        return feedTypeText;
    }
}
