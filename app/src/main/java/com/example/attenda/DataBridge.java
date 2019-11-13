package com.example.attenda;

public class DataBridge  {

       String name , rollno , gender  , sem , skill , dob , course , activity;

       DataBridge(){}

       DataBridge(String n , String r , String g){
             this.name = n;
             this.rollno = r;
             this.gender = g;
       }

       DataBridge(String sem , String sk , String date , String c , String act ){
           this.sem = sem;
           this.skill = sk;
           this.dob = date;
           this.course = c;
           this.activity = act;
       }


       public String getname(){
           return this.name;
       }

       public void setname(String name){
           this.name = name;
       }

       public String getRollno(){
           return this.rollno;
       }

       public void setRollno(String rollno){
           this.rollno = rollno;
       }

       public String getGender(){
           return this.gender;
       }

       public void setGender(String gender){
           this.gender = gender;
       }

       public String getdob(){
           return this.dob;
       }

       public void dob(String Dob){
           this.dob = Dob;
       }

    public String getsem(){
        return this.sem;
    }

    public void sem(String Sem){
        this.sem= Sem;
    }

    public String getskill(){
        return this.skill;
    }

    public void skill(String Skill){
        this.skill = Skill;
    }

    public String getcourse(){
        return this.course;
    }

    public void course(String Course){
        this.course = Course;
    }

    public String getactivity(){
        return this.activity;
    }

    public void activity(String Activity){
        this.activity = Activity;
    }

}
