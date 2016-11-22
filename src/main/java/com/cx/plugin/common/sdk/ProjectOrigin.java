package com.cx.plugin.common.sdk;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProjectOrigin.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProjectOrigin"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="LocalPath"/&gt;
 *     &lt;enumeration value="SharedPath"/&gt;
 *     &lt;enumeration value="TFS"/&gt;
 *     &lt;enumeration value="External"/&gt;
 *     &lt;enumeration value="SVN"/&gt;
 *     &lt;enumeration value="CVS"/&gt;
 *     &lt;enumeration value="GIT"/&gt;
 *     &lt;enumeration value="Perforce"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "ProjectOrigin")
@XmlEnum
public enum ProjectOrigin {

    @XmlEnumValue("LocalPath")
    LOCAL_PATH("LocalPath"),
    @XmlEnumValue("SharedPath")
    SHARED_PATH("SharedPath"),
    TFS("TFS"),
    @XmlEnumValue("External")
    EXTERNAL("External"),
    SVN("SVN"),
    CVS("CVS"),
    GIT("GIT"),
    @XmlEnumValue("Perforce")
    PERFORCE("Perforce");
    private final String value;

    ProjectOrigin(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProjectOrigin fromValue(String v) {
        for (ProjectOrigin c : ProjectOrigin.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
