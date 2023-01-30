package utils;

import com.accenture.modulosPago.entities.Account;

import java.math.BigDecimal;
import java.util.Objects;

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

    public static Boolean verifyBalanceAccount(Account account){
        //Objects.equals(account.getBalance().doubleValue(), 0.00)
        if(account.getBalance().doubleValue() == 0.00){
            return true;
        }
        else {
            return  false;
        }
    }
}
