public class Compulsory {

    public static int sumOfDigits(int number){
        int sum = 0;
        while(number != 0){
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }

    public static void main(String args[]){
        System.out.println("Hello world");

        String[] languages = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);

        n = n * 3;

        int result = (n + 0b10101 + 0xFF)*6;

        while (result >= 10) {
            result = sumOfDigits(result);
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
}

}
