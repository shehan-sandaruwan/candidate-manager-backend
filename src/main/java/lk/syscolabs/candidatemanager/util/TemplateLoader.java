package lk.syscolabs.candidatemanager.util;


import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TemplateLoader {
    private ClassLoader classLoader;
    @Autowired
    private Log appLog;

    public TemplateLoader() {
        classLoader = getClass().getClassLoader();
    }

    public String getTemplate(String templateName) {

        InputStream inputStream = classLoader.getResourceAsStream("template/" + templateName + ".template");
        String template = "";
        try {
            template = readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            appLog.error(e.getStackTrace());
        }
        return template;
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
