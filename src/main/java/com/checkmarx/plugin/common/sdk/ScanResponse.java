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
 *         &lt;element name="ScanResult" type="{http://Checkmarx.com/v7}CxWSResponseRunID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "scanResult"
})
@XmlRootElement(name = "ScanResponse")
public class ScanResponse {

    @XmlElement(name = "ScanResult")
    protected CxWSResponseRunID scanResult;

    /**
     * Gets the value of the scanResult property.
     *
     * @return possible object is
     * {@link CxWSResponseRunID }
     */
    public CxWSResponseRunID getScanResult() {
        return scanResult;
    }

    /**
     * Sets the value of the scanResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseRunID }
     */
    public void setScanResult(CxWSResponseRunID value) {
        this.scanResult = value;
    }

}
