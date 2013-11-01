package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class AbstractAttributeDefinition extends AbstractDefinition {
	@XmlAttribute
	protected String uri;
	@XmlAttribute
	protected ContentType contentType;

	public String getUri() {
		return uri;
	}

	public void setUri( String uri ) {
		this.uri = uri;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType( ContentType contentType ) {
		this.contentType = contentType;
	}

}
