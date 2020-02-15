package com.example.thirdspring;
/**
 * @author oyc
 * @Description:用户实体类
 * @date 2018/7/8 22:51
 */
public class User{
    private String id;
    private String name;
    private String mark;
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setMark(String age){
        this.mark=mark;
    }
    public String getMark(){
        return mark;
    }
}

