package com.lfd.frontend.common.file;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Pool<T> {
	private GenericObjectPool<T> pool;

	public Pool(GenericObjectPoolConfig config, PooledObjectFactory<T> factory) {
		this.pool = new GenericObjectPool<T>(factory, config);
	}

	public GenericObjectPool<T> getPool() {
		return pool;
	}

	public void setPool(GenericObjectPool<T> pool) {
		this.pool = pool;
	}
	
	public T getResource() {
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void returnResource(T obj) {
		pool.returnObject(obj);
	}
	
	public void destroy() {
		if (pool != null && !pool.isClosed()) {
			pool.close();
		}
	}
	
	public int getActiveNum() {
		if (pool != null && !pool.isClosed()) {
			return pool.getNumIdle();
		}
		
		return 0;
	}
}
