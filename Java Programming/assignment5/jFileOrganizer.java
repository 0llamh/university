//PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 5

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class jFileOrganizer {
	
	/** checks to see if a pathname is an existing file
	 * and a directory*/
	public static boolean validDir(String s){
		File temp = new File(s);
		if (temp.exists() && temp.isDirectory())
			return true;
		else
			return false;
	}
	/** prints the valid usage prompt after error cases */
	public static void help(){
		System.out.println("usage:\tjava -jar hw5.jar [directtory] [-a | -l | -s]");
		System.out.println("\t(current directory is default)");
		System.out.println("\t-a alphabetical sorting)");
		System.out.println("\t-l last time modified sorting");
		System.out.println("\t-s file size");
		System.out.println(" ");
	}
	public static String longDateToString(long lastModified){
		Date d = new Date(lastModified);	//converts the long value into a date obj
		SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm:ss");
			// displays the date like so: May 26 14:51:35
		String s = new String();			//creates the string displaying the date
		s = format.format(d); 	// sets string s to the formatted date obj
		return s;
	}
	public static int longIntoInteger(long lastModified1, long lastModified2) {
		// takes in two long values (the last modified long value of two files)
		long value = (lastModified1 - lastModified2);
			// the difference in long values
		//if ((value < Integer.MIN_VALUE) || (value > Integer.MAX_VALUE))
			//if value is smaller than the smallest possible value for an integer, and
			//larger than the largest possible value for an integer, throw an error
			//throw new IllegalArgumentException("The difference between long values is too great for an integer value");
		return (int) value;		// casts value into an int and returns it
	}
	public static void main(String[] args){
		
		if (args.length >= 3)
			jFileOrganizer.help();
		else if ((args.length == 2) && (validDir(args[0]) == false)){
			System.out.println("Invalid directory name");
			System.out.println(" ");
			jFileOrganizer.help();
		}
		else if ((args.length == 2) && ((args[0].compareToIgnoreCase("-a") == 0) ||
				(args[0].compareToIgnoreCase("-l") == 0) || (args[0].compareToIgnoreCase("-s") == 0)))
			jFileOrganizer.help();
		File directory = new File(System.getProperty("user.dir"));
		if ((args.length > 0) && (validDir(args[0]))){
			directory = new File(args[0]);
		}	// sends in the first command line argument
		else if ((args.length == 1) && (jFileOrganizer.validDir(args[0]) == false)){
			if ((args[0].compareToIgnoreCase("-a") == 0) || (args[0].compareToIgnoreCase("-l") == 0) ||
					(args[0].compareToIgnoreCase("-s") == 0)){}
				//does nothing if the only argument is a valid flag
			else{
				// if the only one argument is not a valid flag: error
				System.out.println("Invalid directory name");
				System.out.println(" ");
				jFileOrganizer.help();
				System.exit(0);	//exits the program on error
			}
		}
		File files[] = directory.listFiles();
//			System.out.println("Number of files/directories: " + files.length);
//			for (int i = 0; i < files.length; i++)
//				System.out.println(files[i].getName() + files[i].lastModified());
		if (args.length == 2){
			String param = new String(args[1]);
			if (param.compareToIgnoreCase("-a") == 0)
				Arrays.sort(files, new Alphabetic());
			else if (param.compareToIgnoreCase("-l") == 0)
				Arrays.sort(files, new LastModified());
			else if (param.compareToIgnoreCase("-s") == 0)
				Arrays.sort(files, new FileSize());
		}
		else if ((args.length == 1) && ((args[0].compareToIgnoreCase("-a") == 0) ||
				(args[0].compareToIgnoreCase("-l") == 0) || (args[0].compareToIgnoreCase("-s") == 0))){
			String param = new String(args[0]);
			if (param.compareToIgnoreCase("-a") == 0)
				Arrays.sort(files, new Alphabetic());
			else if (param.compareToIgnoreCase("-l") == 0)
				Arrays.sort(files, new LastModified());
			else if (param.compareToIgnoreCase("-s") == 0)
				Arrays.sort(files, new FileSize());
		}
		for (int i = 0; i < files.length; i++){
			System.out.print(files[i].length());
			System.out.print("\t" + jFileOrganizer.longDateToString(files[i].lastModified()));
			System.out.print("\t" + files[i].getName() + "\n");
		}
		//System.out.println(args[0]);
	}
	
	//COMPARATOR NESTED CLASSES
		private static class Alphabetic implements Comparator<File>{
			// alphabetic comparator interface for Array sorting
			public int compare(File f1, File f2){
				return (f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase()));
			// takes in two file objects, gets their file names as strings, converts them to lowercase,
			// and compares them alphabetically to one another
			}
		}
		private static class LastModified implements Comparator<File>{
			// compares the times last modified for array sorting
			public int compare(File f1, File f2){
				int result = longIntoInteger(f1.lastModified(), f2.lastModified());
					//passes the last modified long values into a casting-compare method
				return result;
			}
		}
		private static class FileSize implements Comparator<File>{
			// compares file sizes with one another for array sorting
			public int compare(File f1, File f2){
				int result = longIntoInteger(f1.length(), f2.length());
					//passes the file size long values into a casting-compare method
				return result;
			}
		}
}