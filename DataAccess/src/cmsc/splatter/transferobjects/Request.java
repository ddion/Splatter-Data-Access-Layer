//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.14 at 10:57:53 AM EDT 
//


package cmsc.splatter.transferobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import cmsc.splatter.transferobjects.reference.IReference;
import cmsc.splatter.transferobjects.reference.IReferencable;
import cmsc.splatter.transferobjects.reference.ReferenceFactory;

/**
 * <p>Java class for request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestedUser" type="{}userReference"/>
 *         &lt;element name="requestingUser" type="{}requestingUser"/>
 *         &lt;element name="createtime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "request", propOrder = {
    "requestedUser",
    "requestingUser",
    "createtime"
})
@XmlRootElement(name = "request")
public class Request implements IReferencable {

    @XmlElement(required = true)
    protected UserReference requestedUser;
    @XmlElement(required = true)
    protected RequestingUser requestingUser;
    @XmlElement(required = true)
    protected String createtime;
    @XmlElement(required = true)
    private String message;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the requestedUser property.
     * 
     * @return
     *     possible object is
     *     {@link UserReference }
     *     
     */
    public UserReference getRequestedUser() {
        return requestedUser;
    }

    /**
     * Sets the value of the requestedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserReference }
     *     
     */
    public void setRequestedUser(UserReference value) {
        this.requestedUser = value;
    }

    /**
     * Gets the value of the requestingUser property.
     * 
     * @return
     *     possible object is
     *     {@link RequestingUser }
     *     
     */
    public RequestingUser getRequestingUser() {
        return requestingUser;
    }

    /**
     * Sets the value of the requestingUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestingUser }
     *     
     */
    public void setRequestingUser(RequestingUser value) {
        this.requestingUser = value;
    }

    /**
     * Gets the value of the createtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * Sets the value of the createtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatetime(String value) {
        this.createtime = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    @Override
    public IReference getReference() {
        return ReferenceFactory.getReferenceFactory()
                .getRequestReference(this);
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}