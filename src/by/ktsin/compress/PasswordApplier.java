package by.ktsin.compress;

public class PasswordApplier {
    public static String Encrypt(String input, String passwd){
        StringBuilder res = new StringBuilder();
        input = "$enc" + input;
        for(int i = 0; i < input.length(); i++){
            res.append(input.charAt(i) ^ passwd.charAt(i % passwd.length()));
        }
        return res.toString();
    }

    public static String Decrypt(String input, String passwd){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            res.append(input.charAt(i) ^ passwd.charAt(i % passwd.length()));
        }
        if(res.substring(0, 3).equals("$enc"))
            return res.substring(4);
        else
            return "Wrong password";
    }
}
