package com.jd.wms.container.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.wms.container.BusinessException;
import com.jd.wms.container.BusinessService;
import com.jd.wms.container.ErrorHandler;
import com.jd.wms.container.Handler;
import com.jd.wms.context.DataContext;

/**
 * 抽象业务模板类
 * <p>
 * 继承<CODE>BusinessService</CODE>接口
 * </p>
 * @author liubing
 * Date Oct 24, 2013
 */
public abstract class AbstractBusinessService implements BusinessService {
	
	protected static Logger LOG = LoggerFactory.getLogger( AbstractBusinessService.class );
		
	protected Handler handler;
	
	protected ErrorHandler errorHandler;
		
	public AbstractBusinessService() {
		super();
	}
	
	public AbstractBusinessService( Handler handler ) {
		this();
		this.handler = handler;
	}
	
	public AbstractBusinessService( Handler handler, ErrorHandler errorHandler ) {
		this.handler = handler;
		this.errorHandler = errorHandler;
	}
	
	/* (non-Javadoc)
	 * @see com.jd.wms.container.BusinessService#process(com.jd.wms.container.DataContext)
	 */
	@Override
	public void doService( DataContext context ) throws BusinessException {
		if ( context.isInvalid() ) {
			getLogger().warn( "****************Current context is invalid.******************" );
			return ;
		}
		try {
			process( context ); 
		} catch ( Exception e ) {
			getLogger().error( e.getMessage(), e ) ;
			context.setException( e );
			throw new BusinessException( context );
		}
	}
	
	public abstract void process( DataContext context ) throws Exception;
	
	/************************setter/getter***************************/

	/**
	 * @return the handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler( Handler handler ) {
		this.handler = handler;
	}

	/**
	 * @return the errorHandler
	 */
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	/**
	 * @param errorHandler the errorHandler to set
	 */
	public void setErrorHandler( ErrorHandler errorHandler ) {
		this.errorHandler = errorHandler;
	}
	
	public Logger getLogger() {
		return LOG;
	}
	
}
