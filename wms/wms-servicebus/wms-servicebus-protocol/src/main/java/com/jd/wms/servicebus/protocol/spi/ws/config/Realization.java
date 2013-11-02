/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.spi.ws.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 2, 2013
 */
@XmlEnum( String.class )
public enum Realization {
	@XmlEnumValue( value = "axis2" )
	AXIS2,
	@XmlEnumValue( value = "cxf" )
	CXF,
	@XmlEnumValue( value = "http+soap" )
	HTTP_SOAP

}
