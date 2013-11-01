/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.container;

import java.beans.PropertyChangeSupport;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 14, 2013
 */
public abstract class GenericModel {

	protected PropertyChangeSupport changeSupport;
	
	public GenericModel() {
		super();
		this.changeSupport = new PropertyChangeSupport( this );
		initPropertyChangeListeners();
	}
	
	protected void firePropertyChange( String propertyName, Object oldValue, Object newValue ) {
		final PropertyChangeSupport support = this.changeSupport;
		fire : {
			if ( support == null || newValue == oldValue ) {
				break fire;
			}
			support.firePropertyChange( propertyName, oldValue, newValue );
		}
	}
	
	protected abstract void initPropertyChangeListeners();

}
