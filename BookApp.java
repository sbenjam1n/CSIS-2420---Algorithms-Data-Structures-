/*
 * Steven Benjamin
 * CSIS 2420
 * AssignmentBooks
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookApp {


    public static void main(String[] args)
    {
   	 	Book book = new Book();
   	 	
   	 /*
   	  * The  List Getter list is copied to booklist.
   	  */
   	 	List<Book> bookList = new ArrayList<>();
   	 
   	 	bookList = book.listGetter("src/books.csv");
   	 /*
   	  * The total number of items copied in from the  list getter.
   	  */
   	 	int itemCount = bookList.size();
   	 
   	 
   	 	System.out.println("Number of books read in: "+itemCount);
   	 
   	 	/*
   	 	 * Books are sorted in natural, alphabetical order. For each book in booklist, getter methods for each book column are displayed.
   	 	 */
   	 	Collections.sort(bookList);
   	 	System.out.println("\nSorted Book List:\n");
   	 	
   	 	for(Book b : bookList)
   	 	{
   	 	
   	 		System.out.println("\t" + b.getTitle()+" by "+b.getAuthor()+" ("+b.getYear()+") "+ "\n");
   	 		
   	 	}
   	 	
   	 	/*
   	 	 * Books are sorted in reverse alphabetical order. 
   	 	 */

   	 	Collections.sort(bookList, Collections.reverseOrder());
   	 	System.out.println("Reverse Order:\n");
   	 	
   	 	for(Book b : bookList)
   	 	{
   	 	
   	 		System.out.println("\t" + b.getTitle()+" by "+b.getAuthor()+" ("+b.getYear()+") "+ "\n");
   	 		
   	 	}

}}
