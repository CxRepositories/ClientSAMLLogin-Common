package com.cx.plugin.common.sdk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ArrayOfCxWSIssueTrackingParam complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ArrayOfCxWSIssueTrackingParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CxWSIssueTrackingParam" type="{http://Checkmarx.com/v7}CxWSIssueTrackingParam" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCxWSIssueTrackingParam", propOrder = {
        "cxWSIssueTrackingParam"
})
public class ArrayOfCxWSIssueTrackingParam {

    @XmlElement(name = "CxWSIssueTrackingParam", nillable = true)
    protected List<CxWSIssueTrackingParam> cxWSIssueTrackingParam;

    /**
     * Gets the value of the cxWSIssueTrackingParam property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cxWSIssueTrackingParam property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCxWSIssueTrackingParam().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CxWSIssueTrackingParam }
     */
    public List<CxWSIssueTrackingParam> getCxWSIssueTrackingParam() {
        if (cxWSIssueTrackingParam == null) {
            cxWSIssueTrackingParam = new ArrayList<CxWSIssueTrackingParam>();
        }
        return this.cxWSIssueTrackingParam;
    }

}
