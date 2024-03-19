package JavaExceptions.HomeWork3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> string = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        if (string.size() != 6){
            scanner.close();
            throw new WrongNumberOfValues();
        }
        String line = extractFIO(string) + " " + extractDate(string) + " " + extractTelephone(string) + " " + extractGender(string) + "\n";        
        String fileName = line.split(" ")[0] + ".txt";
        // Path path = Paths.get(fileName + ".txt");

        try{
            wLine(fileName, line);
        } catch (IOException e){
            e.printStackTrace();
        }
        scanner.close();
    }

    static String extractFIO(ArrayList<String> string){
        String tempString = "", result = "";
        int temp = 0, max = 0;
        for (String str : string) {
            if(!isNumber(str) && !isDate(str) &&!isGender(str)){
                temp++;
                tempString = tempString + " " + str;
                if(temp > max){
                    max = temp;
                    result = tempString;
                }
            } else {
                tempString = "";
                temp = 0;
            }
        }
        if(max != 3){
            throw new WrongNumberOfValuesFIO(max);
        }
        return result.trim();
    }

    static String extractGender (ArrayList<String> string){
        int countGender = countGender(string);
        if(countGender == 1){
            for (String str : string) {
                if(isGender(str)){
                    return str;
                }
            }
        } else if (countGender == 0) { 
            throw new WrongNumberOfValuesGender();
        } else {
            throw new WrongNumberOfValuesGender(countGender);
        }
        return "stub";
    }

    static boolean isGender(String s){
        if (s.equals("m") || s.equals("f")){
            return true;
        }
        return false;
    }

    static int countGender(ArrayList<String> string){
        int count = 0;
        for (String str : string){
            if(isGender(str)){
                count++;
            }
        }
        return count;
    }

    static String extractDate(ArrayList<String> string){
        int countDate = countDate(string);
        if(countDate == 1){
            for (String str : string) {
                if(isDate(str)){
                    return str;
                }
            }
        } else if (countDate == 0) { 
            throw new WrongNumberOfValuesDate();
        } else {
            throw new WrongNumberOfValuesDate(countDate);
        }
        return "stub";        
    }

    static boolean isDate(String s){
        String[] date = s.split("\\.");
        if(date.length == 3){
            for(int i = 0; i < 3; i++){
                if(!isNumber(date[i])){
                    return false;
                }
            }
        } else {
            return false;
        }
        if(date[0].length() != 2 || date[1].length() != 2 || date[2].length() != 4){
            return false;
        }
        return true;
    }

    static int countDate(ArrayList<String> string){
        int count = 0;
        for (String str : string){
            if(isDate(str)){
                count++;
            }
        }
        return count;
    }

    static String extractTelephone(ArrayList<String> string){
        int countTelephone = countTelephone(string);
        if(countTelephone == 1){
            for (String str : string) {
                if(isNumber(str)){
                    return str;
                }
            }
        } else if (countTelephone == 0) { 
            throw new WrongNumberOfValuesTelephone();
        } else {
            throw new WrongNumberOfValuesTelephone(countTelephone);
        }
        return "stub";  //заглушка для return
    }

    static boolean isNumber(String s) throws NumberFormatException {
        try {
            for(int i = 0; i < s.length(); i++){
                Integer.parseInt(s.substring(i, i+1));
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static int countTelephone(ArrayList<String> string){
        int count = 0;
        
        for (String str : string){
            if(isNumber(str)){
                count++;
            }
        }
        return count;        
    }

    public static void wLine (String fileName, String data) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))){
            out.write(data);
        }
    }
}

class WrongNumberOfValues extends RuntimeException{
    public WrongNumberOfValues(){
        super("Введено неверное количество значений!");
    }
}

class WrongNumberOfValuesFIO extends RuntimeException{
    public WrongNumberOfValuesFIO(int i){
        super("Для указания фамилии, имени и отчества подходящих подряд идущих значений необходио 3, найдено: " + i + "!");
    }
}

class WrongNumberOfValuesGender extends RuntimeException{
    public WrongNumberOfValuesGender(){
        super("Нет подходящих значений для указания пола!");
    }
    public WrongNumberOfValuesGender(int count){
        super("Для указания пола подходит более одного значения: " + count + "!");
    }
}

class WrongNumberOfValuesDate extends RuntimeException{
    public WrongNumberOfValuesDate(){
        super("Нет подходящих значений для указания даты!");
    }
    public WrongNumberOfValuesDate(int count){
        super("Для указания даты подходит более одного значения: " + count + "!");
    }
}

class WrongNumberOfValuesTelephone extends RuntimeException{
    public WrongNumberOfValuesTelephone(){
        super("Нет подходящих значений для указания номера телефона!");
    }
    public WrongNumberOfValuesTelephone(int count){
        super("Для указания номера телефона подходит более одного значения: " + count + "!");
    }
}