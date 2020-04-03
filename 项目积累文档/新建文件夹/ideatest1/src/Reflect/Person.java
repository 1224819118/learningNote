package Reflect;

public class Person {
    private String name;
    public String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void show(){
        System.out.println("这是show方法");
    }

}
