package com.jd.wms.container;

public class ErrorResult< T > {
	
	private boolean isContinue;
		
	private int skip;
	
	private T payload;
	
	public ErrorResult() {
	}

	public boolean isContinue() {
		return isContinue;
	}

	public void setContinue( boolean isContinue ) {
		this.isContinue = isContinue;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip( int skip ) {
		this.skip = skip;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload( T payload ) {
		this.payload = payload;
	}

}
