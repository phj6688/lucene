package main.java.phj;

import org.apache.lucene.analysis.standard.StandardAnalyzer; 
import org.apache.lucene.document.Document; 
import org.apache.lucene.index.IndexReader; 
import org.apache.lucene.queryParser.ParseException; 
import org.apache.lucene.queryParser.QueryParser; 
import org.apache.lucene.search.*; 
import org.apache.lucene.store.FSDirectory; 
import org.apache.lucene.util.Version; 

import java.io.File; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List;

import javax.management.Query;
import javax.print.Doc;

public class Searcher
{
    private IndexSearcher searcher;
    private QueryParser artistQueryParser;
    private QueryParser titleQueryParser;
    private QueryParser textQueryParser;

    public Searcher(String indexDir) throws IOException
    {
        //open the index directory to search
        searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_900);
        //define the query parser to search items by artist field
        artistQueryParser = new QueryParser(Version.LUCENE_900,IndexItem.ARTIST,analyzer);
        //define the query parser to search items by title field
        titleQueryParser = new QueryParser(Version.LUCENE_900,IndexItem.TITLE,analyzer);
        //define the query parser to search items by text field
        textQueryParser = new QueryParser(Version.LUCENE_900,IndexItem.TEXT,analyzer);        
    }   


    //This method is used to find the indexed items by the artist.
    public List<IndexItem> findByArtist(String queryString,int numberOfResults) throws java.text.ParseException, IOException    
    {
        //create query from incoming query string.
        Query query = artistQueryParser.parse(queryString);
        //execute and get the results
        ScoreDoc[] queryResults = searcher.search(query,numberOfResults).scoreDocs;
        List<IndexItem> results = new ArrayList<IndexItem>();

        for (ScoreDoc scoreDoc : queryResults)
        {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(Long.parseLong(doc.get(IndexItem.ID)), doc.get(IndexItem.ARTIST), doc.get(IndexItem.TITLE), doc.get(IndexItem.TEXT)));
        }
        return results;
    }
    //This method is used to find the indexed items by the title.
    public List<IndexItem> findByTitle(String queryString,int numberOfResults) throws java.text.ParseException, IOException    
    {
        //create query from incoming query string.
        Query query = titleQueryParser.parse(queryString);
        //execute and get the results
        ScoreDoc[] queryResults = searcher.search(query,numberOfResults).scoreDocs;
        List<IndexItem> results = new ArrayList<IndexItem>();

        for (ScoreDoc scoreDoc : queryResults)
        {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(Long.parseLong(doc.get(IndexItem.ID)), doc.get(IndexItem.ARTIST), doc.get(IndexItem.TITLE), doc.get(IndexItem.TEXT)));
        }
        return results;
    }
    //This method is used to find the indexed items by the text.
    public List<IndexItem> findByText(String queryString,int numberOfResults) throws java.text.ParseException, IOException    
    {
        //create query from incoming query string.
        Query query = textQueryParser.parse(queryString);
        //execute and get the results
        ScoreDoc[] queryResults = searcher.search(query,numberOfResults).scoreDocs;
        List<IndexItem> results = new ArrayList<IndexItem>();

        for (ScoreDoc scoreDoc : queryResults)
        {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(Long.parseLong(doc.get(IndexItem.ID)), doc.get(IndexItem.ARTIST), doc.get(IndexItem.TITLE), doc.get(IndexItem.TEXT)));
        }
        return results;
    }
    public void close() throws IOException
    {
        searcher.close();
    }
}