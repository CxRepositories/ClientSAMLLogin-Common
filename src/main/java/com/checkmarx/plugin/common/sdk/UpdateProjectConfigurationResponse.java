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
 *         &lt;element name="UpdateProjectConfigurationResult" type="{http://Checkmarx.com/v7}CxWSBasicRepsonse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "updateProjectConfigurationResult"
})
@XmlRootElement(name = "UpdateProjectConfigurationResponse")
public class UpdateProjectConfigurationResponse {

    @XmlElement(name = "UpdateProjectConfigurationResult")
    protected CxWSBasicRepsonse updateProjectConfigurationResult;

    /**
     * Gets the value of the updateProjectConfigurationResult property.
     *
     * @return possible object is
     * {@link CxWSBasicRepsonse }
     */
    public CxWSBasicRepsonse getUpdateProjectConfigurationResult() {
        return updateProjectConfigurationResult;
    }

    /**
     * Sets the value of the updateProjectConfigurationResult property.
     *
     * @param value allowed object is
     *              {@link CxWSBasicRepsonse }
     */
    public void setUpdateProjectConfigurationResult(CxWSBasicRepsonse value) {
        this.updateProjectConfigurationResult = value;
    }

}
