package com.example.mycontactdemo.vo;

//7.2导入联系人
public class Contact {
    //7.2.1定义类的属性
    // 定义姓名、电话、邮箱三个属性
    private  String  name,phone,email;
    //选择性导入联系人
    private  boolean isChecked;

    //7.2.2定义构造方法
    public Contact(String name, String phone,String email, boolean isChecked) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
