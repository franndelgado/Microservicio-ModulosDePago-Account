package utils;

public final class Utils {

    public Utils() {
    }

    public static String generateRandomDigit(int cant){
        String generateNumber = "";
        for(int i = 0; i < cant; i++) {
            int newNumber = (int) (Math.random() * 10);
            generateNumber += String.valueOf(newNumber);
        }
        return generateNumber;
    }


    public static Boolean verifyNumber(String number){
        try {
            Double.parseDouble(number);
            return true;
        }catch (NumberFormatException e){
            e.getMessage();
            return false;
        }
    }
}
