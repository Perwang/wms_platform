package com.jd.wms.memqueue.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "configuration" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ConfigurationDefinition {

	@XmlElementRef
	private List< QueueDefinition > queues = new LinkedList< QueueDefinition >();

	public List< QueueDefinition > getQueues() {
		return this.queues;
	}

	public void setQueues( List< QueueDefinition > queues ) {
		this.queues = queues;
	}
}