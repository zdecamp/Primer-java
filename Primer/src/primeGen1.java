import java.lang.Math;

public class primeGen1 {
	public static void main(String[] args) {
		int i;
		int num = 3;
        int maxCheck = num + top; // maxCheck limit till which you want to find prime numbers
        boolean isPrime = true;
 
        //Empty String
        String primeNumbersFound = "";
 
        //Start loop 1 to maxCheck
        for (i = num; i <= maxCheck; i++) {
            isPrime = CheckPrime(i);
            if (isPrime) {
                primeNumbersFound = primeNumbersFound + i + " ";
            }
        }
        System.out.println("Prime numbers from " + num + " to " + maxCheck + " are:");
        // Print prime numbers from 1 to maxCheck
        System.out.println(primeNumbersFound);
    }
    public static boolean CheckPrime(int numberToCheck) {
        int remainder;
        for (int i = 2; i <= numberToCheck / 2; i++) {
            remainder = numberToCheck % i;
            //if remainder is 0 than numberToCheckber is not prime and break loop. Else continue loop
            if (remainder == 0) {
                return false;
            }
        }
        return true;
 
    }

}