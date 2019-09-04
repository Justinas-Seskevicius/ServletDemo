package com.company;

import java.util.Arrays;
import java.util.Objects;

public class BankCodeFormatter {

	public static boolean isCountryCodeValid(String countryCode){
        return countryCode != null && countryCode.matches("[A-Za-z]{2}");
    }

    public static boolean isBankCodeValid(String bankCode){
        return bankCode != null && bankCode.matches("[0-9]{2,10}");
    }

    public static String formatBankCode(String countryCode, String bankCode){
        String[] numbersInGroups = bankCodeInNumberGroups(bankCode);
        int largestGroupIndex = largestGroupIndex(numbersInGroups);
        StringBuilder sb = new StringBuilder();
        sb.append(countryCode.toUpperCase()).append(" ");
        for (int i = 0; i < numbersInGroups.length; i++){
            String currentGroup = numbersInGroups[i];
            if(i == largestGroupIndex && currentGroup.length() > 1) {
                sb.append("(").append(currentGroup).append(")");
            } else {
                sb.append(currentGroup);
            }
        }
        return sb.toString();
    }

    public static String[] bankCodeInNumberGroups(String bankCode){
        String[] split = bankCode.split("");
        String[] groups = new String[split.length];
        String previousGroup = split[0];
        int index = 0;
        groups[index] = previousGroup;
        for(int i = 1; i < split.length; i++) {
            String currentNumber = split[i];
            if(previousGroup.equals(currentNumber)){
                groups[index] = groups[index] + currentNumber;
            } else {
                previousGroup = currentNumber;
                index++;
                groups[index] = currentNumber;
            }
        }
        return Arrays.stream(groups).filter(Objects::nonNull).toArray(String[]::new);
    }

    public static int largestGroupIndex(String[] numbersInGroups){
        int largestGroupLength = 0;
        int largestGroupIndex = 0;
        for (int i = 0; i < numbersInGroups.length; i++){
            String currentGroup = numbersInGroups[i];
            if(currentGroup.length() > largestGroupLength){
                largestGroupLength = currentGroup.length();
                largestGroupIndex = i;
            }
        }
        return largestGroupIndex;
    }
	
}
