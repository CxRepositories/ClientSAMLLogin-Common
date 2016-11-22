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
 *         &lt;element name="GetAllUsersResult" type="{http://Checkmarx.com/v7}CxWSResponseUserData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getAllUsersResult"
})
@XmlRootElement(name = "GetAllUsersResponse")
public class GetAllUsersResponse {

    @XmlElement(name = "GetAllUsersResult")
    protected CxWSResponseUserData getAllUsersResult;

    /**
     * Gets the value of the getAllUsersResult property.
     *
     * @return possible object is
     * {@link CxWSResponseUserData }
     */
    public CxWSResponseUserData getGetAllUsersResult() {
        return getAllUsersResult;
    }

    /**
     * Sets the value of the getAllUsersResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseUserData }
     */
    public void setGetAllUsersResult(CxWSResponseUserData value) {
        this.getAllUsersResult = value;
    }

}
