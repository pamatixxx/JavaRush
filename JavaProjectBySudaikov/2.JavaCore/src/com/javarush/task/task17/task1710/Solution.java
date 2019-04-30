package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        String name;
        Date bd;
        Sex sex;
        int id;
        Person person;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);

        if (args[0].equals("-c")){
            name = args[1];
            sex=(args[2] == "м")?Sex.MALE:Sex.FEMALE;
            bd=format.parse(args[3]);
            if (sex==Sex.FEMALE){
                person = Person.createFemale(name,bd);
            }else person = Person.createMale(name,bd);

            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }
        if (args[0].equals("-u")){
            person = allPeople.get(Integer.parseInt(args[1]));
            name = args[2];
            sex=(args[3]=="м")?Sex.MALE:Sex.FEMALE;
            bd = format.parse(args[4]);
            person.setBirthDay(bd);
            person.setSex(sex);
            person.setName(name);
        }
        if (args[0].equals("-d")){
        id = Integer.parseInt(args[1]);
        person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
        }
        if (args[0].equals("-i")){
            id = Integer.parseInt(args[1]);
            person = allPeople.get(id);
            String xXXx = (person.getSex()==Sex.MALE)?"м":"ж";
            SimpleDateFormat db = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
            System.out.println(person.getName()+" "+xXXx+" "+db.format(person.getBirthDay()));
        }

    }
}
