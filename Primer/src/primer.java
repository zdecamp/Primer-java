import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;


public class primer {
	
	boolean remainder(double a, double b) {
		if (a%b == 0) {
			return true ;
		}else {
			return false;
		}
	}
	
	

	
	public static void main(String[] args) throws Exception {
	
	// Read number from lastNumber.txt to resume work
		double start;
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
		RandomAccessFile file = new RandomAccessFile("src\\lastNumber.txt", "r");
		String r;
		if ((r = file.readLine()) != null) {
			start = Double.valueOf(r);  // +2 to prevent calculating the same number again.
		}else {
			start = (double) 3;  // if lastNumber.txt is empty, start at 3 because 1 and 2 aren't truly primes.
		}
		file.close();  //close lastNumber.txt file
		
		
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
		
	//	start timing for runtime
		long startTime = System.nanoTime();
		
	// variables
		boolean isPrime;  // prime test
		double y;  // incremental for MOD calc.
		//double p = (new double("1"));
		int x = 0;  // incremental for progress bar
		int b;  // var for progress bar % calc.
		int div;  //var for progress bar division
		int zdiv;
		int c = 1; // incremental for keeping primes @ 30/line in primes.txt
		
	// Open primes.txt for writing primes.
		BufferedWriter writer = new BufferedWriter(new FileWriter("src\\primes.txt",true));
		
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
		
	        
			isPrime = true;
			double sqr = (double) Math.sqrt(y);  	//	<<<---- Need to get the square root of y, for use in for loop. 
// ---->												//	y.sqr can be a very large number, so still needs to be BigInteger.
			//int sqr = bsqr.sqrt;
			int zx = 0;
			int zb;
			
			for(int z = 2; z <= sqr; z++) {
				if (Double.toString(y).length() >= 14) {
					zb = (int) sqr/100;
					zdiv = (int) Math.round(sqr/100);
					if (z % zb == 0) {
						if(zx < zdiv) {
						// animated progress bar  *not quite working*
							String anim= "|/-\\";
						String data = "\r" + "<========== " + anim.charAt(z % anim.length()) + " " + zx + "% ==========>" + "    "+y;
			            System.out.write(data.getBytes());
							zx = zx+1;
						}
					}
				}
			// check for even divisibility
				if(y % z == 0) {
					isPrime = false;  // if remainder is 0, is divisible by number and not prime
				break;
				}
				
			}
	// if isPrime==true write prime to file
		if(isPrime) {
			writer.write(String.valueOf(y) + ",");  //print to primes.txt
			if(c % 30 == 0){
			    writer.newLine();  //print new line every 30 entries
			}
			c++;
		}
		
		}
	//print status bar complete
		String dataComp = "\r <========== Complete ==========> \n";
		System.out.write(dataComp.getBytes());
		
		writer.newLine();  // write new line at end of file
		writer.close();  // close\save file
		
	// Save last number scanned in lastNumber.txt
		BufferedWriter lwriter = new BufferedWriter(new FileWriter("src\\lastNumber.txt"));
	    lwriter.write(String.valueOf(y));
	    lwriter.close();
		
	// end timer for runtime
		long endTime = System.nanoTime();
		long durration = ((endTime - startTime)/1000000000) ;  // divide into seconds.
		
	// Pull number from lastNumber.txt 
		RandomAccessFile endfile = new RandomAccessFile("src\\lastNumber.txt", "r");
		String en;
		double ended;
		if ((en = endfile.readLine()) != null) {
			ended = Double.valueOf(en);
		}else {
			ended = (double) 3;
		}
		endfile.close();
		System.out.print("Ended with: " + ended + " in " + durration + " seconds.");
		
				
	}
}