package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import uk.co.webamoeba.slf4j.junit.context.LoggingContext;

/**
 * @author James Kennard
 */
public class LoggerFactory implements ILoggerFactory {

	public Logger getLogger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (LoggingContext.getContext().loggingIsEnabled()) {
			return new RecordingLogger(name);
		}
		return new IgnoreLoggingLogger(name);
	}

}
