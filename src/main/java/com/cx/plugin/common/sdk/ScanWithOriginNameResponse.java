package com.cx.plugin.common.sdk;

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
 *         &lt;element name="ScanWithOriginNameResult" type="{http://Checkmarx.com/v7}CxWSResponseRunID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "scanWithOriginNameResult"
})
@XmlRootElement(name = "ScanWithOriginNameResponse")
public class ScanWithOriginNameResponse {

    @XmlElement(name = "ScanWithOriginNameResult")
    protected CxWSResponseRunID scanWithOriginNameResult;

    /**
     * Gets the value of the scanWithOriginNameResult property.
     *
     * @return possible object is
     * {@link CxWSResponseRunID }
     */
    public CxWSResponseRunID getScanWithOriginNameResult() {
        return scanWithOriginNameResult;
    }

    /**
     * Sets the value of the scanWithOriginNameResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseRunID }
     */
    public void setScanWithOriginNameResult(CxWSResponseRunID value) {
        this.scanWithOriginNameResult = value;
    }

}
