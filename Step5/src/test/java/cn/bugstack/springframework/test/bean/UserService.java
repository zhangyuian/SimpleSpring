package cn.bugstack.springframework.test.bean;

public class UserService {

    private String uId;

    private UserDao userDao;

    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
        return userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
