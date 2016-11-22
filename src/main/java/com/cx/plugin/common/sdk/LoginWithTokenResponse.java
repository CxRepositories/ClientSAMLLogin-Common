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
 *         &lt;element name="LoginWithTokenResult" type="{http://Checkmarx.com/v7}CxWSResponseLoginData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loginWithTokenResult"
})
@XmlRootElement(name = "LoginWithTokenResponse")
public class LoginWithTokenResponse {

    @XmlElement(name = "LoginWithTokenResult")
    protected CxWSResponseLoginData loginWithTokenResult;

    /**
     * Gets the value of the loginWithTokenResult property.
     *
     * @return possible object is
     * {@link CxWSResponseLoginData }
     */
    public CxWSResponseLoginData getLoginWithTokenResult() {
        return loginWithTokenResult;
    }

    /**
     * Sets the value of the loginWithTokenResult property.
     *
     * @param value allowed object is
     *              {@link CxWSResponseLoginData }
     */
    public void setLoginWithTokenResult(CxWSResponseLoginData value) {
        this.loginWithTokenResult = value;
    }

}
