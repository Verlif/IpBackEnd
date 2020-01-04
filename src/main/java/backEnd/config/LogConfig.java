package backEnd.config;

import backEnd.model.RecordLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Configuration
public class LogConfig {

    @Value("${path.log}")
    private String logPath;

    @Bean("recordLog")
    public RecordLogger getRecordLog() {
        Logger logger = Logger.getLogger("recordLog");
        setHandler(logger, logPath);
        return new RecordLogger(logger);
    }

    private void setHandler(Logger logger, String logPath) {
        try {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
            FileHandler fileHandler = new FileHandler(logPath + System.getProperty("file.separator") + logger.getName() + s.format(timestamp) + ".log");
            fileHandler.setFormatter(new FileLogFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FileLogFormatter extends Formatter {

        private static DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public String format(LogRecord r) {
            Date date = new Date();
            String separator = System.getProperty("line.separator");
            return "  --[" + dFormat.format(date) + "]" + "[" + r.getLevel() + "] " +
                    r.getMessage() +
                    separator;
        }
    }
}
