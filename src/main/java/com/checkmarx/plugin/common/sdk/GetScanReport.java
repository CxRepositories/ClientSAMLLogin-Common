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
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReportID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionID",
        "reportID"
})
@XmlRootElement(name = "GetScanReport")
public class GetScanReport {

    @XmlElement(name = "SessionID")
    protected String sessionID;
    @XmlElement(name = "ReportID")
    protected long reportID;

    /**
     * Gets the value of the sessionID property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the reportID property.
     */
    public long getReportID() {
        return reportID;
    }

    /**
     * Sets the value of the reportID property.
     */
    public void setReportID(long value) {
        this.reportID = value;
    }

}
