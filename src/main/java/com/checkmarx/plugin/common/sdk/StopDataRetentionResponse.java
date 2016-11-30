package com.checkmarx.plugin.common.sdk;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StopDataRetentionResult" type="{http://Checkmarx.com/v7}CxWSBasicRepsonse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "stopDataRetentionResult"
})
@XmlRootElement(name = "StopDataRetentionResponse")
public class StopDataRetentionResponse {

    @XmlElement(name = "StopDataRetentionResult")
    protected CxWSBasicRepsonse stopDataRetentionResult;

    /**
     * Gets the value of the stopDataRetentionResult property.
     *
     * @return possible object is
     * {@link CxWSBasicRepsonse }
     */
    public CxWSBasicRepsonse getStopDataRetentionResult() {
        return stopDataRetentionResult;
    }

    /**
     * Sets the value of the stopDataRetentionResult property.
     *
     * @param value allowed object is
     *              {@link CxWSBasicRepsonse }
     */
    public void setStopDataRetentionResult(CxWSBasicRepsonse value) {
        this.stopDataRetentionResult = value;
    }

}
