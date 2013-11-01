package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum( String.class )
public enum IODirection {
	
	@XmlEnumValue( value = "in" ) IN,
	
	@XmlEnumValue( value = "out" ) OUT,
	
	@XmlEnumValue( value = "in/out" ) INOUT

}
