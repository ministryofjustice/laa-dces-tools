//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package uk.gov.justice.laadces.deltaconcor.generated;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Long>
{


    public Long unmarshal(String value) {
        return Long.valueOf(value);
    }

    public String marshal(Long value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}