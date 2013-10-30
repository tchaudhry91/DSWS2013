package logging;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class MyLogger {
	
	private static MyLogger instance = null;
	
	Logger logger = null;
	FileAppender fa;
	
	protected MyLogger() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date date = new Date();
		
		String logDir = "logs/client-" + dateFormat.format(date) + ".log";
		String pattern = "%d{ISO8601} %-5p %c: %m%n";
		
		PatternLayout pLayout = new PatternLayout(pattern);
		try {
			fa = new FileAppender(pLayout, logDir, true );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MyLogger getInstance() {
		if (instance == null) {
			instance = new MyLogger();
		}
		return instance;
	}
	
	public void createLogger(Class logClass) {		
		logger = Logger.getLogger(logClass);
		logger.addAppender(fa);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger mylogger) {
		this.logger = mylogger;
	}
	
	
	
}
