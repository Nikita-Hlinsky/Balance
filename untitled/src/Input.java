import java.util.Scanner;

public class Input {

    private static String getInput(String value) {
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println(value);
//        System.err.flush();

        Scanner in = new Scanner(System.in);

        try {
            return in.nextLine();
        } catch (Exception e) {
            return "Is not a number!";
        }
    }

    public static int getInt(String value) {
        String in = getInput(value);
        return Integer.parseInt(in);
    }

    public static String getString(String value) {
        String in = getInput(value);
        return in;
    }
}

