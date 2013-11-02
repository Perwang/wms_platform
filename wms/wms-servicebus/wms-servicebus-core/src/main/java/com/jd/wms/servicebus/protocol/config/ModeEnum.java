package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum( String.class )
public enum ModeEnum {

	@XmlEnumValue( value = "a" ) ASYNC,
	
	@XmlEnumValue( value = "s" ) SYNC
	
}
