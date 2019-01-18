package com.example.fhl.aidl.inouts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/17 18:32
 */
public class Preson implements Parcelable{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    /**
     *默认生成的模板类的对象只支持为 in 的定向 tag
     * 如果要支持为 out 或者 inout 的定向 tag 的话，
     * 还需要实现 readFromParcel() 方法——而这个方法其实并没有在 Parcelable 接口里面
     */
    public void readFromParcel(Parcel dest){
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        age = dest.readInt();
    }

    public Preson() {
    }

    protected Preson(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Creator<Preson> CREATOR = new Creator<Preson>() {
        @Override
        public Preson createFromParcel(Parcel source) {
            return new Preson(source);
        }

        @Override
        public Preson[] newArray(int size) {
            return new Preson[size];
        }
    };

    @Override
    public String toString() {
        return "Preson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
