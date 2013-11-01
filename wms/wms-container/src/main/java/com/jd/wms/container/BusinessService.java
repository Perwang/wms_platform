package com.jd.wms.container;

import com.jd.wms.container.BusinessException;
import com.jd.wms.context.DataContext;

public interface BusinessService {
	
	public void doService( DataContext context ) throws BusinessException;

}
