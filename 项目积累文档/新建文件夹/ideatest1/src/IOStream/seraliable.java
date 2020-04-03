package IOStream;

import java.io.*;

public class seraliable {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\序列化.txt"));
        person person = new person();
        person.setName("a");
        person.setSex("nan");
        try(oos) {
            oos.writeObject(person);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\序列化.txt"));
        try(ois) {
            Object object = ois.readObject();
            person person1 = (person) object;
            System.out.println(person.getName()+person1.getSex());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
class person implements Serializable {

    private String name;
    private String sex;

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
}
