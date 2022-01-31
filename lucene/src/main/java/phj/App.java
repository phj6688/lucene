package phj;

import org.apache.lucene.queryParser.ParseException;

import main.java.phj.IndexItem;
import main.java.phj.Indexer;
import main.java.phj.Searcher;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.List;

import javax.sound.sampled.SourceDataLine;
import javax.swing.plaf.basic.BasicBorders.FieldBorder;

public class App 
{
    //location of the index
    private static final String INDEX_DIR = "src/main/resources/index";
    private static final int DEFAULT_RESULT_SIZE = 100;

    public static void main( String[] args ) throws IOException, java.text.ParseException
    {
        //items to be index
        IndexItem[] indexItems = 
        { 
            new IndexItem(1L, "java", "Java in Action", "This is Java in Action Book"),
            new IndexItem(2L, "spring", "Spring in Action", "This is Spring in Action Book"),
            new IndexItem(3L, "hibernate", "Hibernate in Action", "This is Hibernate in Action Book"),
            new IndexItem(4L, "soa", "SOA in Action", "This is SOA in Action Book"),
            new IndexItem(5L, "apache", "Apache Axis2 in Action", "This is Axis2 in Action Book"),
            new IndexItem(6L, "apache", "Apache CXF in Action", "This is CXF in Action Book"),
            new IndexItem(7L, "query", "jQuery in Action", "This is jQuery in Action Book")            
        };
        //create indexer and indexing items
        Indexer indexer = new Indexer(INDEX_DIR);
        for (IndexItem indexItem : indexItems)
        {
            indexer.index(indexItem);
        }
        indexer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println("Type Q/q to quit.");
        System.out.println("Type 1 Query by artist");
        System.out.println("Type 2 Query by titel");
        System.out.println("Type 3 Query by text");
        
        //create searcher 
        Searcher searcher = new Searcher(indexDir);
        
        do
        {
            System.out.println("Enter input: ");
            input = reader.readLine();
            if (input.equalsIgnoreCase("q"))
            {
                break;
            }
                
            //search by artist
            if (input.equals("1"))
            {
                System.out.println("Enter artist to search: ");
                input = reader.readLine();
                List<IndexItem> result = searcher.findByArtist(input,DEFAULT_RESULT_SIZE);
                print(result);                
            }
            if (input.equals("2"))
            {
                System.out.println("Enter title to search: ");
                input = reader.readLine();
                List<IndexItem> result = searcher.findByTitle(input,DEFAULT_RESULT_SIZE);
                print(result);                
            }
            else if (input.equals("3"))
            {
                System.out.println("Enter text to search: ");
                input = reader.readLine();
                List<IndexItem> result = searcher.findByText(input,DEFAULT_RESULT_SIZE);
                print(result);                
            }    
        }while (true);
        searcher.close();
    }
    //print the results   
    private static void print(List<IndexItem> result)
    {
        System.out.println("Result Size: " + result.size());
        for (IndexItem item : result)
        {
            System.out.println(item);
        }
    }
}
