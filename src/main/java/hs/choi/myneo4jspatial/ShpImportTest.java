package hs.choi.myneo4jspatial;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.geotools.data.shapefile.shp.ShapefileException;
import org.neo4j.collections.graphdb.impl.EmbeddedGraphDatabase;
import org.neo4j.gis.spatial.ShapefileImporter;
import org.neo4j.graphdb.GraphDatabaseService;

public class ShpImportTest {
	
	private static final String storeDir = "C:/dev_neo4j-spatial/data/graph.db";
	
    public static void main( String[] args ) {
    	
    	
    	
		GraphDatabaseService database = new EmbeddedGraphDatabase(storeDir);
        try {
                ShapefileImporter importer = new ShapefileImporter(database);
                
                try {
					importer.importFile("c:/dev_neo4j-spatial/data_shp/highway.shp", "layer_roads");
				} catch (ShapefileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
        } finally {
                database.shutdown();
        }
		
    	
    
      
    }
}
