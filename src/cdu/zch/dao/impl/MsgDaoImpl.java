package cdu.zch.dao.impl;

import cdu.zch.dao.MsgDao;
import cdu.zch.dao.UserDao;
import cdu.zch.model.Msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgDaoImpl extends BaseDao implements MsgDao {
    UserDao userDao = new UserDaoImpl();

    @Override
    public Msg get(int id) {
        Msg msg = null;
        String sql = "SELECT * FROM guestbook_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                msg = new Msg();
                msg.setId(rs.getInt("id"));
                msg.setUser(userDao.get(rs.getInt("userId")));
                msg.setSubject(rs.getString("subject"));
                msg.setContent(rs.getString("content"));
                msg.setAddMsgTime(rs.getLong("addMsgTime"));
                msg.setIsReplied(rs.getInt("isReplied"));
                msg.setReUser(userDao.get(rs.getInt("reUserId")));
                msg.setReply(rs.getString("reply"));
                msg.setReTime(rs.getLong("reTime"));
            }
        } catch (Exception e) {
            System.out.println("DAO 获取留言失败: id=" + id);
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public List<Msg> findAll() {
        List<Msg> msgList = new ArrayList<Msg>();
        String sql = "SELECT * FROM guestbook_table ORDER BY addMsgTime DESC";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Msg msg = new Msg();
                msg.setId(rs.getInt("id"));
                msg.setUser(userDao.get(rs.getInt("userId")));
                msg.setSubject(rs.getString("subject"));
                msg.setContent(rs.getString("content"));
                msg.setAddMsgTime(rs.getLong("addMsgTime"));
                msg.setIsReplied(rs.getInt("isReplied"));
                msg.setReUser(userDao.get(rs.getInt("reUserId")));
                msg.setReply(rs.getString("reply"));
                msg.setReTime(rs.getLong("reTime"));
                msgList.add(msg);
            }
        } catch (Exception e) {
            System.out.println("DAO 获取留言列表失败");
            e.printStackTrace();
        }
        return msgList;
    }


    @Override
    public List<Msg> findByPage(int start, int num, int reStatus) {
        List<Msg> msgList = new ArrayList<Msg>();
        String sql = "SELECT * FROM guestbook_table ORDER BY addMsgTime DESC LIMIT ?,?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            if (reStatus > 0) {
                pstmt.setInt(3, reStatus);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Msg msg = new Msg();
                msg.setId(rs.getInt("id"));
                msg.setUser(userDao.get(rs.getInt("userId")));
                msg.setSubject(rs.getString("subject"));
                msg.setContent(rs.getString("content"));
                msg.setAddMsgTime(rs.getLong("addMsgTime"));
                msg.setIsReplied(rs.getInt("isReplied"));
                msg.setReUser(userDao.get(rs.getInt("reUserId")));
                msg.setReply(rs.getString("reply"));
                msg.setReTime(rs.getLong("reTime"));
                msgList.add(msg);
            }
        } catch (Exception e) {
            System.out.println("DAO 分页言列表失败: ");
            e.printStackTrace();
        }
        return msgList;
    }

    @Override
    public int insert(Msg msg) {
        int rows = 0;
        String sql = "INSERT INTO guestbook_table(userId,subject,content,addMsgTime) VALUES(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, msg.getUser().getId());
            pstmt.setString(2, msg.getSubject());
            pstmt.setString(3, msg.getContent());
            pstmt.setLong(4, new Date().getTime());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 添加留言: " + sql + ", " + msg);
        } catch (Exception e) {
            System.out.println("DAO 添加留言失败: " + sql + ", " + msg);
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateReply(Msg msg) {
        int rows = 0;
        String sql = "UPDATE guestbook_table SET isReplied=?,reUserId=?,reply=?,reTime=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, msg.getIsReplied());
            pstmt.setInt(2, msg.getReUser().getId());
            pstmt.setString(3, msg.getReply());
            pstmt.setLong(4, msg.getReTime());
            pstmt.setInt(5, msg.getId());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 回复留言: " + rows + ", " + msg);

        } catch (Exception e) {
            System.out.println("DAO 回复留言失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(Msg msg) {
        int rows = 0;
        String sql = "UPDATE guestbook_table SET userId=?,subject=?,content=?,addMsgTime=?,isReplied=?,reUserId=?,reply=?,reTime=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, msg.getUser().getId());
            pstmt.setString(2, msg.getSubject());
            pstmt.setString(3, msg.getContent());
            pstmt.setLong(4, msg.getAddMsgTime());
            pstmt.setInt(5, msg.getIsReplied());
            pstmt.setInt(6, msg.getReUser().getId());
            pstmt.setString(7, msg.getReply());
            pstmt.setLong(8, msg.getReTime());
            pstmt.setInt(9, msg.getId());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 修改留言: " + rows + ", " + msg);
        } catch (Exception e) {
            System.out.println("DAO 修改留言失败");
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM guestbook_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
            System.out.println("DAO 删除留言: " + sql + ", id=" + id);
        } catch (Exception e) {
            System.out.println("DAO 删除留言失败: " + sql + ", id=" + id);
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int count(int reStatus) {
        int count = 0;
        String sql = "SELECT count(*) FROM guestbook_table";
        if (reStatus > 0) {
            sql += " WHERE isReplied=" + reStatus;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("DAO 获取留言记录总数失败");
            e.printStackTrace();
        }
        return count;
    }

}
