import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class primer {

	public static void main(String[] args) throws Exception {
		int start;

		RandomAccessFile file = new RandomAccessFile("C:\\Users\\zdeca\\eclipse-workspace\\Primer\\src\\lastNumber.txt", "r");
		String r;

		if ((r = file.readLine()) != null) {
			start = Integer.valueOf(r);
		}else {
			start = 3;
		}
		file.close();
		
		
		Scanner scan = new Scanner(System.in);
		boolean isPrime;
		int input;
		int c = 1;
		System.out.print("Starting with: " + start + "\n");
		System.out.println("Enter a gen ceiling: ");
		input = scan.nextInt();
		scan.close();
		System.out.println();
		
		//System.out.print("2 ");
		for(int y = start; y < input; y++) {
			BufferedWriter lwriter = new BufferedWriter(new FileWriter("C:\\Users\\zdeca\\eclipse-workspace\\Primer\\src\\lastNumber.txt"));
		    lwriter.write(Integer.toString(y));
		    lwriter.close();
			isPrime = true;
			for(int z = 2; z <= Math.sqrt(y); z++) {
				if(y % z == 0) {
					isPrime = false;
				}
			}
		
		if(isPrime) {
			if(c % 10 == 0){
				BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\zdeca\\eclipse-workspace\\Primer\\src\\primes.txt",true));
			    writer.newLine();
			    writer.close();
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\zdeca\\eclipse-workspace\\Primer\\src\\primes.txt",true));
			writer.write(Integer.toString(y) + ",");
		    writer.close();
		    
			c++;
		}
		}
		
	}

}
