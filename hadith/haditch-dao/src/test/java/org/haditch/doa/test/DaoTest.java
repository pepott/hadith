package org.haditch.doa.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

public class DaoTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private Cluster cluster;
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		cluster.close();
	}
	
	private void connect(String node) {
		   cluster = Cluster.builder()
		         .addContactPoint(node).build();
		   Metadata metadata = cluster.getMetadata();
		   System.out.printf("Connected to cluster: %s\n", 
		         metadata.getClusterName());
		   for ( Host host : metadata.getAllHosts() ) {
		      System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
		         host.getDatacenter(), host.getAddress(), host.getRack());
		   }
		}

	@Test
	public void test() {
		connect("127.0.0.1");
	}

}
