package cdu.lhj.service.impl;

import cdu.lhj.dao.MsgDao;
import cdu.lhj.dao.impl.MsgDaoImpl;
import cdu.lhj.model.Msg;
import cdu.lhj.service.MsgService;

import java.util.List;

public class MsgServiceImpl implements MsgService {

    MsgDao msgDao = new MsgDaoImpl();

    @Override
    public List<Msg> findByPage(int page, int pageSize, int reStatus) {
        if (reStatus < 0 || reStatus > 2) {
            reStatus = 0;
        }
        return msgDao.findByPage((page - 1) * pageSize, pageSize, reStatus);
    }

    @Override
    public List<Msg> findByPage(String sPage, String sPageSize, int reStatus) {
        int page = 1;
        int pageSize = 10;
        if (sPage != null || !sPage.equals("")) {
            page = Integer.parseInt(sPage);
            if (page < 1) {
                page = 1;
            }
        }
        if (sPageSize != null || !sPageSize.equals("")) {
            pageSize = Integer.parseInt(sPageSize);
        }
        return findByPage(page, pageSize, reStatus);

    }

    @Override
    public List<Msg> findByPage(String sPage, int reStatus) {
        return findByPage(sPage, null, reStatus);
    }

    @Override
    public Msg get(String sid) {
        if (sid == null || sid.equals("")) {
            return null;
        }
        return get(Integer.parseInt(sid));
    }

    @Override
    public Msg get(int id) {
        return msgDao.get(id);
    }

    @Override
    public int count(int reStatus) {
        return msgDao.count(reStatus);
    }

    @Override
    public boolean add(Msg msg) {
        if (msg == null) {
            return false;
        }
        return msgDao.insert(msg) == 1 ? true : false;
    }

    @Override
    public boolean reply(Msg msg) {
        if (msg == null) {
            return false;
        }
        return msgDao.updateReply(msg) == 1 ? true : false;
    }

    @Override
    public boolean del(String sid) {
        if (sid == null || sid.equals("")) {
            return false;
        }
        return msgDao.delete(Integer.parseInt(sid)) == 1 ? true : false;
    }
}
