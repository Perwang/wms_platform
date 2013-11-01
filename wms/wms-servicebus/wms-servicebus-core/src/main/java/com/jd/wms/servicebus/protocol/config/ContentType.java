package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum( String.class )
public enum ContentType {
	
	@XmlEnumValue( value = "text" )TEXT,
	@XmlEnumValue( value = "byte" )BYTE,

}
