public class Hello {
    public static void main(String[] args) {
        String username = System.getProperty("user.name");
        System.out.print("Hello, "+username+"!");
    }
}