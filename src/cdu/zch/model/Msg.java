package cdu.zch.model;

import java.io.Serializable;

public class Msg implements Serializable {
    private int id;
    private User user;
    private String subject;  // 留言主题
    private String content;  //留言内容
    private long addMsgTime;  //留言时间
    private int isReplied;  //是否被回复
    private User reUser;  //回复人
    private String reply;  //回复内容
    private long reTime;  //回复时间

    public Msg() {
    }

    public Msg(int id, User user, String subject, String content, long addMsgTime, int isReplied, User reUser, String reply, long reTime) {
        this.id = id;
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.addMsgTime = addMsgTime;
        this.isReplied = isReplied;
        this.reUser = reUser;
        this.reply = reply;
        this.reTime = reTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAddMsgTime() {
        return addMsgTime;
    }

    public void setAddMsgTime(long addMsgTime) {
        this.addMsgTime = addMsgTime;
    }

    public int getIsReplied() {
        return isReplied;
    }

    public Boolean isReplied(){
        return isReplied == 1 ? true : false;
    }

    public void setIsReplied(int isReplied) {
        this.isReplied = isReplied;
    }

    public User getReUser() {
        return reUser;
    }

    public void setReUser(User reUser) {
        this.reUser = reUser;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public long getReTime() {
        return reTime;
    }

    public void setReTime(long reTime) {
        this.reTime = reTime;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", addMsgTime=" + addMsgTime +
                ", isReplied=" + isReplied +
                ", reUser=" + reUser +
                ", reply='" + reply + '\'' +
                ", reTime=" + reTime +
                '}';
    }
}
