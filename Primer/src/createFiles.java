import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;



public class createFiles {
	
	public void create() {
		try {
			File lnum = new File("src\\lastNumber.txt");
			File primes = new File("src\\primes.txt");
			if(lnum.createNewFile()) {
				BufferedWriter lnStart = new BufferedWriter(new FileWriter("src\\lastNumber.txt"));
			    lnStart.write(String.valueOf(3));
			    lnStart.close();
				System.out.println("lastNumber.txt has been created");
			}
			if(primes.createNewFile()) {
				System.out.println("primes.txt has been created");
			}
			
		}catch(Exception e) {
			  //  Block of code to handle errors
		}
		
	}
	
}