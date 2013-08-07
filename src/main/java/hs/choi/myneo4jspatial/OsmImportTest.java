package hs.choi.myneo4jspatial;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.neo4j.collections.graphdb.impl.EmbeddedGraphDatabase;
import org.neo4j.gis.spatial.osm.OSMImporter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

public class OsmImportTest {

	private static final String dir = "C:/dev_neo4j-spatial/data/graph.db";
	
	public static void main(String[] args) {

		OSMImporter importer = new OSMImporter("london");
		Map<String, String> config = new HashMap<String, String>();
		config.put("neostore.nodestore.db.mapped_memory", "90M");	// 90 -> 200 -> 500
		config.put("dump_configuration", "true");
		config.put("use_memory_mapped_buffers", "true");
		BatchInserter batchInserter = new BatchInserterImpl(dir, config);
		try {
			importer.importFile(batchInserter, "C:/dev_neo4j-spatial/data_osm/london.osm", false);
//			importer.importFile(batchInserter, "C:/dev/neo4j-spatial/130104_1/data_osm/map.osm", false);
			
			System.out.println("1============================================================================================");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();			
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2============================================================================================");
		batchInserter.shutdown();
		
		System.out.println("3============================================================================================");

		GraphDatabaseService db = new EmbeddedGraphDatabase(dir);
	    importer.reIndex(db, 10000);
	    db.shutdown();

	}
}
