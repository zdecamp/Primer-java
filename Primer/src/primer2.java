
import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;


public class primer2 {
	
	static double readLast() throws FileNotFoundException, IOException {
		double start;
		RandomAccessFile file = new RandomAccessFile("src\\lastNumber.txt", "r");
		String r;
		if ((r = file.readLine()) != null) {
			start = Double.valueOf(r);  // +2 to prevent calculating the same number again.
		}else {
			start = (double) 3;  // if lastNumber.txt is empty, start at 3 because 1 and 2 aren't truly primes.
		}
		file.close();  //close lastNumber.txt file
		return start;
	}
	
	static void saveLast(double y) throws IOException {
		BufferedWriter lwriter = new BufferedWriter(new FileWriter("src\\lastNumber.txt"));
	    lwriter.write(String.valueOf(y));
	    lwriter.close();
	}
	
	static void savePrime( int c, double y) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("src\\primes.txt",true));
		writer.write(String.valueOf(y) + ",");  //print to primes.txt
		if(c % 30 == 0){
		    writer.newLine();  //print new line every 30 entries
		}
		writer.close();
	}
	
	
	int prime(){
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
			
		createFiles create = new createFiles() ;
		create.create();
		
		double start = readLast() ;
		
	// start user input for process count
		Scanner scan = new Scanner(System.in);
		System.out.println("Starting with: " + start);
		System.out.println("Enter a gen ceiling: ");
		System.out.println(); 
		int input = scan.nextInt();
		String in = String.valueOf(input);
		double out =  Double.valueOf(in);
		double end = start + out;
		scan.close();
		
		int sSqr = (int) Math.sqrt(end);
		int[] nums = java.util.stream.IntStream.rangeClosed(2, sSqr).toArray();
		
	//	start timing for runtime
		long startTime = System.nanoTime();
		
	// variables
		boolean isPrime = true;  // prime test
		double y;  // incremental for MOD calc.
		int x = 0;  // incremental for progress bar
		int b;  // var for progress bar % calc.
		int div;  //var for progress bar division
		int zdiv;
		int c = 1; // incremental for keeping primes @ 30/line in primes.txt
		
		
	// main runtime loop
		for(y = start; y < end; y++) {
			//System.out.print(y + "\n");
			if (Double.toString(y).length() < 14) {
				//System.out.println("Calculating " + y + "\n");
				// Print status bar
				if(input > 99) {
					b = (int) Math.floor(input/100);
					div = 100;
				} else if( 10 <= input && input < 100) {
					b = (int) Math.floor(input/10);
					div = 10;
				} else {
					b = 1;
					div = 1 ;
				}
				
				if (y % b == 0) {
					if(x < div) {
					// animated progress bar  *not quite working* <-- supposedly only doesn't work inside eclipse
						//String anim= "|/-\\";
						String data = "\r" + "<======---- "  + "% " + x + "% ----======>  with " + y;
			            System.out.write(data.getBytes());
						x = x+1;
					}
				}
			}
		
	        
			double sqr = (double) Math.sqrt(y);  	//	<<<---- Need to get the square root of y, for use in for loop. 
// ---->												//	y.sqr can be a very large number, so still needs to be BigInteger.
			int zx = 0;
			int zb;
			
			for (int num : nums) {
				if (num > sqr) {
					break ;
				}
				if (Double.toString(y).length() >= 14) {
					zb = (int) sqr/100;
					zdiv = (int) Math.round(sqr/100);
					if (num % zb == 0) {
						if(zx < zdiv) {
						// animated progress bar  *not quite working*
							String anim= "|/-\\";
						String data = "\r" + "<========== " + anim.charAt(num % anim.length()) + " " + zx + "% ==========>" + "    "+y;
			            System.out.write(data.getBytes());
							zx = zx+1;
						}
					}
				}
			// check for even divisibility
				if(y % num == 0) {
					isPrime = false;  // if remainder is 0, is divisible by number and not prime
				break;
				}
				
			}
	// if isPrime==true write prime to file
		if(isPrime) {
			savePrime(c, y) ;
			
			c++;
		
		}
		}
	//print status bar complete
		String dataComp = "\r <========== Complete ==========> \n";
		System.out.write(dataComp.getBytes());
		
		
	// Save last number scanned in lastNumber.txt
		saveLast(y) ;
		
	// end timer for runtime
		long endTime = System.nanoTime();
		long durration = ((endTime - startTime)/1000000000) ;  // divide into seconds.
		
		System.out.print("Ended with: " + y + " in " + durration/60 + " minutes " + durration%60 + " seconds.");
		
				
		
	}
}