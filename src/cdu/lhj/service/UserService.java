package cdu.lhj.service;

import cdu.lhj.model.User;

import java.util.List;

public interface UserService {

    List<User> findByPage(int page, int pageSize);

    List<User> findByPage(String sPage, String sPageSize);

    List<User> findByPage(String sPage);

    User get(String sid);

    User get(String name, String password);

    int count();

    boolean add(User user);

    boolean del(String sid);

    boolean mod(User user);

}
