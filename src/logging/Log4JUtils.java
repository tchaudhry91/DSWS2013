package logging;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class Log4JUtils {

	private static Log4JUtils logs = new Log4JUtils();
	
	public static Log4JUtils instance() {
		return logs;
	}
	
	public URL findLoggerConfigFile() {
		return Thread.currentThread().getContextClassLoader().getResource("log4j.xml");
	}
	
	public void prepareLogFile(Logger root) {
		Appender appender = root.getAppender("file");
		
		if (!(appender instanceof FileAppender))
			return;
		FileAppender fAppender = (FileAppender)appender;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date date = new Date();
		
		String logFileName = fAppender.getFile();
		logFileName = logFileName.substring(0, logFileName.lastIndexOf('.')) + "-test.log " + dateFormat.format(date);
		
		File logFile = new File(logFileName);
		File renamedFile = new File(logFile.getAbsoluteFile() + dateFormat.format(date));
		
		if (logFile.exists()) {
			if (!logFile.renameTo(renamedFile))
				System.err.println("Could not rename log file!");
		}
		
		fAppender.setFile(logFile.getAbsolutePath());
		fAppender.activateOptions();
	}
	
	
	
}
