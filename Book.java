/*
 * Steven Benjamin
 * CSIS 2420
 * AssignmentBooks
 */
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book<T> implements Comparable<T>
{

    public Book(String title, String author, int year)
    {
   	 super();
   	 this.title = title;
   	 this.author = author;
   	 this.year = year;    
    }
    
    public Book() {}

    private String title;
    private String author;
    private int year;
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    
    public int compareTo(T p)
    {
    	return getTitle().compareTo(((Book) p).getTitle());
    }


    public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
   	 return "Book [title=" + title + ", author=" + author + ", year=" + year
   			 + "]";    
    }
    
    
	/*
	 * Takes a filepath, reads in a csv file using a scanner. Items are added first to a string list. These strings are broken up into 
	 * two strings and an int using the comma as an item separator. Any strings that do not have the expected types or are not properly delimited
	 * are not read in. The name of the incorrectly formatted book is diplayed.
	 * 
	 */
    public <T> List<Book> listGetter(String file)
    {    
   	 
   	 Path filePath = Paths.get(file);

   	 String s = null;
	
   	 ArrayList<Book> list = new ArrayList<Book>();
   		 
	 ArrayList<String> lines = new ArrayList<String>();
	
	 String str = null;
   	 
   	 try
   	 {
   		 Scanner scanner = new Scanner(filePath);
   		 String setDelimiter = ",";
   		 scanner.useDelimiter(setDelimiter);

   		 


   		 while (scanner.hasNextLine())
   		 {

   		 	String line = scanner.nextLine();

   		 	lines.add(line);
   			 
   		 }} catch (IOException e) {
   			 System.out.println(e.getMessage());
   			 e.printStackTrace();}
   			 
   			
   				for (String books : lines)
   				{	try
   	   			{	 
   					//System.out.println(books.toString());
   					String[] fields = books.split(",");
   					str = books;
   					
   					int yr = Integer.parseInt(fields[2]);
   	   		 		list.add(new Book(fields[0].toString(),fields[1].toString(),yr));
   	   		 		//System.out.println("***");
   				} catch (IndexOutOfBoundsException e) {
   		 	System.err.println("\nProblem reading in " + str);          	 
    	}
   				catch (NumberFormatException e) {
   		   		 	System.err.println("\nProblem reading in " + str);          	 
   		    	}
   	 

    }
				return list;

   	 
}
}
