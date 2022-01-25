package Calc;

public class Calculate {
    public static String calc(String calculatedString) {
        String[] arguments = calculatedString.split("(\\s)([+\\-*/])(\\s)");

        if (arguments.length != 2) {
            throw new CalculateException("There must be two arguments");
        }

        String result = "";
        String firsStrArg = arguments[0].trim();
        checkStringFormat(firsStrArg);

        String secondStrArg;
        String sign = getSign(calculatedString);
        int numberArg;

        try {
            numberArg = Integer.parseInt(arguments[1].trim());
            checkNumber(numberArg);
            result = calcWithNumber(firsStrArg, numberArg, sign);
        } catch (NumberFormatException e) {
            secondStrArg = arguments[1].trim();
            checkStringFormat(secondStrArg);
            result = calcWithString(firsStrArg, secondStrArg, sign);
        }
        if (result.length() > 42){
            result = result.substring(0,41) + "...\"";
        }

        return result;
    }

    private static boolean checkStringFormat(String str) {
        if (str.startsWith("\"") && str.endsWith("\"") && str.length() <= 12) {
            return true;
        } else {
            throw new CalculateException("First argument must be string, strings must start and end with \" and argument length < 10");
        }

    }

    private static boolean checkNumber(int a) {
        if (a < 0 || a > 10) {
            throw new CalculateException("Numbers must be between 0 and 10 ");
        }
        return true;
    }

    private static String getSign(String str) {
        if (str.contains(" + ")) {
            return "+";
        } else if (str.contains(" - ")) {
            return "-";
        } else if (str.contains(" * ")) {
            return "*";
        } else if (str.contains(" / ")) {
            return "/";
        } else {
            throw new CalculateException("Not such operation");
        }
    }

    private static String calcWithNumber(String str, int a, String sign) {
        String result;
        switch (sign) {
            case "*":
                result = str.repeat(a).replace("\"\"","");
                break;
            case "/":
                result = str.substring(0,str.length() / a).concat("\"");
                break;
            default:
                throw new CalculateException("Operation not supported");
        }
        return result;
    }

    private static String calcWithString(String str1, String str2, String sign) {
        String result;
        switch (sign) {
            case "+":
                result = str1.concat(str2).replace("\"\"","");
                break;
            case "-":
                result = str1.replace(str2.replace("\"",""), "");
                break;
            default:
                throw new CalculateException("Operation not supported");
        }
        return result;
    }
}
