package pss.trabalho.config;

import com.ufes.logger.JsonLogAdapter;
import com.ufes.logger.LogAdapter;
import com.ufes.logger.XmlLogAdapter;

public class UserConfig {
    private static UserConfig instance;
    private LogType logType;
    private LogAdapter logger;

    private UserConfig() {
        logType = LogType.JSON;
        logger = new JsonLogAdapter();
    }

    public static UserConfig getInstance() {
        if (instance == null) {
            instance = new UserConfig();
        }
        return instance;
    }

    public void setLogType(LogType logType) {
        if (logType == this.logType) return;
        LogAdapter logger;
        switch (logType) {
            case XML:
                logger = new XmlLogAdapter();
                break;
            case JSON:
            default:
                logger = new JsonLogAdapter();
        }
        this.logType = logType;
        this.logger = logger;
    }

    public LogAdapter getLogger() {
        return logger;
    }
}
