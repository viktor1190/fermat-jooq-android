package com.fermat.jooq.android.example;

import com.bitdubai.fermat.db.sqlite.generated.tables.pojos.NodesCatalog;
import org.jooq.impl.DSL;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testInformationSchema() throws Exception{
        Class.forName("org.sqlite.JDBC");

        NodesCatalog nodesCatalog = new NodesCatalog();
        nodesCatalog.setIdentityPublicKey("mykey" + Math.random());
        nodesCatalog.setName("testNode"+ Math.random());
        nodesCatalog.setIp("192.168.1.1");
        nodesCatalog.setDefaultPort(8080);
        nodesCatalog.setLatitude((float) 42.34556);
        nodesCatalog.setLongitude((float) 27.54556);
        nodesCatalog.setRegisteredTimestamp((int) (System.currentTimeMillis() / 1000L)); //seconds precision only todo rethink this

        /*nodesCatalogService.insert(nodesCatalog);
        Assert.assertEquals(
                Arrays.asList("COLUMNS", "TABLES"),
                DSL.using("jdbc:sqlite:node.db")
                        .select(TABLES.TABLE_NAME)
                        .from(TABLES)
                        .where(TABLES.TABLE_NAME.in("COLUMNS", "TABLES"))
                        .and(TABLES.TABLE_SCHEMA.eq("INFORMATION_SCHEMA"))
                        .fetch(TABLES.TABLE_NAME)
        );*/
    }
}