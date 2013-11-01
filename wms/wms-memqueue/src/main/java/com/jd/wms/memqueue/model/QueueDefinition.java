package com.jd.wms.memqueue.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "queue" )
@XmlAccessorType( XmlAccessType.FIELD )
public class QueueDefinition {

	@XmlAttribute( required = true )
	private String name;

	@XmlAttribute( required = true )
	private int capacity;

	@XmlAttribute
	private boolean priority;

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity( int capacity ) {
		this.capacity = capacity;
	}

	public boolean isPriority() {
		return this.priority;
	}

	public void setPriority( boolean priority ) {
		this.priority = priority;
	}
}