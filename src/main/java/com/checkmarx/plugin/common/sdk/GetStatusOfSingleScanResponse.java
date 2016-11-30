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
 *         &lt;element name="GetStatusOfSingleScanResult" type="{http://Checkmarx.com/v7}CxWSResponseScanStatus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getStatusOfSingleScanResult"
})
@XmlRootElement(name = "GetStatusOfSingleScanResponse")
public class GetStatusOfSingleScanResponse {

    @XmlElement(name = "GetStatusOfSingleScanResult")
    protected CxWSResponseScanStatus getStatusOfSingleScanResult;

    /**
     * Gets the value of the getStatusOfSingleScanResult property.
     *
     * @return possible object is
     * {@link CxWSResponseScanStatus }
     */
    public CxWSResponseScanStatus getGetStatusOfSingleScanResult() {
        return getStatusOfSingleScanResult;
    }

    /**
     * Sets the value of the getStatusOfSingleScanResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseScanStatus }
     */
    public void setGetStatusOfSingleScanResult(CxWSResponseScanStatus value) {
        this.getStatusOfSingleScanResult = value;
    }

}
