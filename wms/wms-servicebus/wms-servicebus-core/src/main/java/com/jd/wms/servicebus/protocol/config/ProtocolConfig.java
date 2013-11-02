package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ProtocolConfig extends AbstractDefinition {
	@XmlAttribute
	protected String name;
	@XmlAttribute
	protected ModeEnum mode;
	@XmlAttribute
	protected SideEnum side;
	@XmlAttribute
	protected IODirection ioDirection;
	@XmlElementRef
	protected RequestConfig request;
	@XmlElementRef
	protected ResponseConfig response;
	@XmlElementRef
	protected CommonsConfig commons;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}
	/**
	 * @return the mode
	 */
	public ModeEnum getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode( ModeEnum mode ) {
		this.mode = mode;
	}
	/**
	 * @return the side
	 */
	public SideEnum getSide() {
		return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide( SideEnum side ) {
		this.side = side;
	}
	/**
	 * @return the ioDirection
	 */
	public IODirection getIoDirection() {
		return ioDirection;
	}
	/**
	 * @param ioDirection the ioDirection to set
	 */
	public void setIoDirection( IODirection ioDirection ) {
		this.ioDirection = ioDirection;
	}
	/**
	 * @return the request
	 */
	public RequestConfig getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest( RequestConfig request ) {
		this.request = request;
	}
	/**
	 * @return the response
	 */
	public ResponseConfig getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse( ResponseConfig response ) {
		this.response = response;
	}
	/**
	 * @return the commons
	 */
	public CommonsConfig getCommons() {
		return commons;
	}
	/**
	 * @param commons the commons to set
	 */
	public void setCommons( CommonsConfig commons ) {
		this.commons = commons;
	}

}
