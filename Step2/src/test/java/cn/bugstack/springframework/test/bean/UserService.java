package cn.bugstack.springframework.test.bean;

public class UserService {


    //private String name;

    // 这里如果自定义构造函数，clazz.newInstance()就会报错
    //public UserService(String name) {
    //    this.name = name;
    //}
    public void queryUserInfo() {
        System.out.println("查询用户信息");
    }

}
