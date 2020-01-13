package com.valarcfcc.xyz.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "div")
@Data
public class XmlReq {

    @JacksonXmlProperty(localName = "p")

    private String p;

}