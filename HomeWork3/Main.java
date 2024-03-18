package JavaExceptions.HomeWork3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> string = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        if (string.size() != 6){
            scanner.close();
            throw new WrongNumberOfValues();
        }

        // System.out.println(extractFIO(string));;

        scanner.close();
    }
    // static String extractFIO(ArrayList<String> string){
    // static ArrayList<String[]> extractFIO(ArrayList<String> string){
    static void extractFIO(ArrayList<String> string){
        for (int i = 0; i <= string.size()-3; i++){
            if (!isDigit(string.get(i)) && )
            
            System.out.println();
        }
    }    

    static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    static boolean isGender(String s){
        if (s.equals("m") || s.equals("f")){
            return true;
        }
        return false;
    }
    static boolean isDate(String s){
        String[] date = s.split(".");
        if(date.length == 3){
            for(int i = 0; i < 3; i++){
                if(!isDigit(date[i])){
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
}





class WrongNumberOfValues extends RuntimeException{
    public WrongNumberOfValues(){
        super("Введено неверное количество значений!");
    }
    public WrongNumberOfValues(int i){
        super("Для фамилии, имени и отчества неверное количество значений!");
    }
}