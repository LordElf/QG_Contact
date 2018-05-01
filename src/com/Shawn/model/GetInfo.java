package com.Shawn.model;

public class GetInfo {
    private int id;
    private String username;
    private String MemberName;
    private String QG_Group;
    private String Grade;
    private String CLASS;
    private int Phone_num;
    private String Email;
    private String Dorm;
    private String Family_Address;

    public GetInfo(int ID, String username, String MemberName, String QG_Group, String Grade,
                   String CLASS, int Phone_num, String Email, String Dorm, String Family_Address)
    {
        this.id = ID;
        this.username = username;
        this.MemberName = MemberName;
        this.QG_Group = QG_Group;
        this.Grade = Grade;
        this.CLASS = CLASS;
        this.Phone_num = Phone_num;
        this.Email = Email;
        this.Dorm = Dorm;
        this.Family_Address = Family_Address;
    }

    public int getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getMemberName()
    {
        return MemberName;
    }

    public String getQG_Group() {
        return QG_Group;
    }

    public String getGrade(){
        return Grade;
    }

    public String getEmail(){
        return Email;
    }

    public String getDorm() {
        return Dorm;
    }

    public String getFamily_Address() {
        return Family_Address;
    }

    public int getPhone_num()
    {
        return Phone_num;
    }

    public String getCLASS() {
        return CLASS;
    }
}
