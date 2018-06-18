package string;

public class StringIntern {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "a";
        String s3 = new String("a");
        String s4 = s1.intern();
        String s5 = s3.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s3 == s5);
        System.out.println(s1 == s5);
    }
}
