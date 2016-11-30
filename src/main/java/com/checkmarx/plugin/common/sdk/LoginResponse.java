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
 *         &lt;element name="LoginResult" type="{http://Checkmarx.com/v7}CxWSResponseLoginData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loginResult"
})
@XmlRootElement(name = "LoginResponse")
public class LoginResponse {

    @XmlElement(name = "LoginResult")
    protected CxWSResponseLoginData loginResult;

    /**
     * Gets the value of the loginResult property.
     *
     * @return possible object is
     * {@link CxWSResponseLoginData }
     */
    public CxWSResponseLoginData getLoginResult() {
        return loginResult;
    }

    /**
     * Sets the value of the loginResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseLoginData }
     */
    public void setLoginResult(CxWSResponseLoginData value) {
        this.loginResult = value;
    }

}
