package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        //Ну тут понятно: нет строки - нет проверки
        if (telNumber == null || telNumber == "") return false;

        //Вроде и не нужно? Но зачем проверять 4 выражения ИЛИ если однозначно не комильфо
        if (telNumber.length() < 10 || telNumber.length() > 15) return false; //12 цифр, 1:"+", 2:"-";

        //Тогда где проверка на ")" без "(", или на наличие ---, или на наличие букв \w, или заканчивается на "-"....?
        //НЭТУ! всего 4 возможных варианта:
        return (telNumber.matches("^\\+\\d{12}") /* +*********** */
                || telNumber.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{7}") /* +**(***)******* */
                || telNumber.matches("^\\+\\d{8}-\\d{2}-\\d{2}") /* +********-** */
                || telNumber.matches("^\\d{6}-\\d{4}")); /* ******-**** */
    }

    public static void main(String[] args) {
    }
}
