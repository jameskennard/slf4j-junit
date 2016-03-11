package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import uk.co.webamoeba.slf4j.junit.logger.LoggerFactory;

/**
 * @author James Kennard
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {

	private static final StaticLoggerBinder STATIC_LOGGER_BINDER = new StaticLoggerBinder();

	public static StaticLoggerBinder getSingleton() {
		return STATIC_LOGGER_BINDER;
	}

	/**
	 * @see #getSingleton()
	 */
	private StaticLoggerBinder() {
	}

	public ILoggerFactory getLoggerFactory() {
		return new LoggerFactory();
	}

	public String getLoggerFactoryClassStr() {
		return LoggerFactory.class.getName();
	}

}
