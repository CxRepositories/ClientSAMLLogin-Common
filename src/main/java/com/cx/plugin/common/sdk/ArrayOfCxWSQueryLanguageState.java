package com.cx.plugin.common.sdk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ArrayOfCxWSQueryLanguageState complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ArrayOfCxWSQueryLanguageState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CxWSQueryLanguageState" type="{http://Checkmarx.com/v7}CxWSQueryLanguageState" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCxWSQueryLanguageState", propOrder = {
        "cxWSQueryLanguageState"
})
public class ArrayOfCxWSQueryLanguageState {

    @XmlElement(name = "CxWSQueryLanguageState", nillable = true)
    protected List<CxWSQueryLanguageState> cxWSQueryLanguageState;

    /**
     * Gets the value of the cxWSQueryLanguageState property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cxWSQueryLanguageState property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCxWSQueryLanguageState().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CxWSQueryLanguageState }
     */
    public List<CxWSQueryLanguageState> getCxWSQueryLanguageState() {
        if (cxWSQueryLanguageState == null) {
            cxWSQueryLanguageState = new ArrayList<CxWSQueryLanguageState>();
        }
        return this.cxWSQueryLanguageState;
    }

}
