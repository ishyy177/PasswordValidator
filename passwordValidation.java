package passwordChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class passwordValidation {
    public static void main(String[] args) {
        // create the file path
        String path = "/Users/ismail/Desktop/passwords.txt";
        // create an array of strings
        String[] passwords = new String[13];

        // create a file
        File file = new File(path);
        // create try catch sblock
        try {
            // get the bufferreader object from the java utilities
            //
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i < passwords.length; i++) {
                passwords[i] = reader.readLine();
                System.out.println(passwords[i]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("cannot read the file");
            e.printStackTrace();
        }

        // create a for each
        for (String password : passwords) {
            System.out.println("****\n" + password);
            boolean hasnumber = false;
            boolean hasletter = false;
            boolean hasspecialchara = false;
            for (int i = 0; i < password.length(); i++) {
                // cheak if password contains a number
                if ("0123456789".contains(password.substring(i, i + 1))) {
                    hasnumber = true;
                } else if ("abcdefghijklmnopqrstuvwxyz".contains(password.substring(i, i + 1).toLowerCase())) {
                    hasletter = true;
                } else if ("!”#$%&’()*+,-./:;<=>?^_@][`{|}~".contains(password.substring(i, i + 1))) {
                    hasspecialchara = true;
                } else {
                    try {
                        // create a constructor exception
                        throw new invalidCharacters(password.substring(i, i + 1));
                    } catch (invalidCharacters e) {
                        System.out.println(e.toString());
                    }
                }
            }

            // if the each one is not true then throw an exception
            // try catch block

            try {
                if (!hasnumber) {
                    throw new hasnonumberexception(password);
                } else if (!hasletter) {
                    throw new hasnoletterexception(password);
                } else if (!hasspecialchara) {
                    throw new hasnospecialcharaexception(password);
                } else {
                    System.out.println("valid password");
                }
            } catch (hasnonumberexception e) {
                System.out.println(e.toString());
            } catch (hasnoletterexception e) {
                System.out.println(e.toString());
            } catch (hasnospecialcharaexception e) {
                System.out.println(e.toString());
            }
        }

    }
}

// create the exceptions

class invalidCharacters extends Exception {

    private static final long serialVersionUID = 1L;
    private String password;

    invalidCharacters(String password) {
        this.password = password;
    }

    public String toString() {
        return "password " + password + " does not contain a special character";
    }

}

class hasnonumberexception extends Exception {

    private static final long serialVersionUID = 1L;
    private String password;

    hasnonumberexception(String password) {
        this.password = password;
    }

    public String toString() {
        return "password " + password + " doesn't contain a number";
    }
}

class hasnoletterexception extends Exception {

    private static final long serialVersionUID = 1L;
    private String password;

    hasnoletterexception(String password) {
        this.password = password;
    }

    public String toString() {
        return "password " + password + " doesn't contain a letter";
    }
}

class hasnospecialcharaexception extends Exception {

    private static final long serialVersionUID = 1L;
    private String password;

    hasnospecialcharaexception(String password) {
        this.password = password;
    }

    public String toString() {
        return "password " + password + " doesn't contain a special character";
    }
}