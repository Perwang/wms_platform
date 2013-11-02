/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.jd.wms.Env;
import com.jd.wms.servicebus.protocol.config.ProtocolConfig;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class ProtocolConfigTest {
	@Before
	public void setup() {
		Env.getInstance().init( "/home/bjyfliubing/Test" );
		try {
			ProtocolPluginManager.getInstance().start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	@Test
	public void tsetConfig() {
		ProtocolConfigManager.getInstance().readAll();
		final Map< String, ProtocolConfig > configs = ProtocolConfigManager.getInstance().getAll();
		Assert.assertNotNull( configs );
		Assert.assertTrue( configs.size() == 1 );
		Assert.assertNotNull( configs.get( "testws" ) );
	}

}
