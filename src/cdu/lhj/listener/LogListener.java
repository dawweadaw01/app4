package cdu.lhj.listener;
import cdu.lhj.model.User;
import cdu.lhj.util.MyDate;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.*;
@WebListener
public class LogListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {
    File logFile;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String logDirPath = sce.getServletContext().getRealPath("/log");
        File logDir = new File(logDirPath);
        if (!logDir.exists()) {
            System.out.println("日志目录不存在，新建：" + logDir.mkdir());
        }
        if (!logDir.isDirectory()) {
            System.out.println("日志目录有误，删除：" + logDir.delete());
            System.out.println("日志目录重建：" + logDir.mkdir());
        }
        logFile = new File(logDirPath + "/" + MyDate.today() + ".txt");
        try {
            fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        User user = null;
        if (se.getName().equals("user")) {
            user = (User) se.getValue();
        }
        log(user, "登录");
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        User user = (User) se.getSession().getAttribute("user");
        String action = (String) se.getSession().getAttribute("action");
        log(user, action);
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("action", "");
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        User user = (User) se.getSession().getAttribute("user");
        log(user, "注销");
    }
    private void log(User user, String action) {
        if (user == null || action == null) {
            return;
        }
        String info = "";
        if (user.getIsAdmin()) {
            info += "管理员";
        }
        info += user.getName() + "(" + user.getId() + ")" + action + " : " + MyDate.now();
        try {
            bufferedWriter.append(info);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}