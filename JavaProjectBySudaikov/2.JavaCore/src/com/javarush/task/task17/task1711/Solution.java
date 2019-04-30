package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 3) {
                        name = args[i];
                        sex = (args[i + 1] == "м") ? Sex.MALE : Sex.FEMALE;
                        bd = format.parse(args[i + 2]);
                        if (sex == Sex.MALE) {
                            person = Person.createMale(name, bd);
                        } else person = Person.createFemale(name, bd);
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                    break;
                }
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 4) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        name = args[i + 1];
                        sex = (args[i + 2] == "м") ? Sex.MALE : Sex.FEMALE;
                        bd = format.parse(args[i + 3]);
                        person.setBirthDay(bd);
                        person.setSex(sex);
                        person.setName(name);
                    }
                    break;

                }
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        id = Integer.parseInt(args[i]);
                        person = allPeople.get(id);
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDay(null);
                    }
                    break;
                }
            case "-i":
                synchronized (allPeople){
                    for(int i = 1; i < args.length; i++) {
                        id = Integer.parseInt(args[i]);
                        person = allPeople.get(id);
                        String xXXx = (person.getSex()==Sex.MALE)?"м":"ж";
                        SimpleDateFormat db = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                        System.out.println(person.getName()+" "+xXXx+" "+db.format(person.getBirthDay()));
                    }
                    break;
                }
        }
    }
}
