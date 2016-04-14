package com.fermat.jooq.android.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.bitdubai.fermat.db.sqlite.generated.tables.daos.NodesCatalogDao;
import com.bitdubai.fermat.db.sqlite.generated.tables.pojos.NodesCatalog;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "onCreate");

        Connection connection = null;

        try {
            Class.forName("org.sqldroid.SQLDroidDriver").newInstance();
// create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" +
                    new SQLiteAssetHelper(getApplicationContext(), "node.db", null, 12).getWritableDatabase().getPath());

            DSLContext create = DSL.using(connection, SQLDialect.SQLITE);

            NodesCatalog nodesCatalog = new NodesCatalog();
            String publicKey = "mykey" + Math.random();
            nodesCatalog.setIdentityPublicKey(publicKey);
            nodesCatalog.setName("testNode" + Math.random());
            nodesCatalog.setIp("192.168.1.1");
            nodesCatalog.setDefaultPort(8080);
            nodesCatalog.setLatitude((float) 42.34556);
            nodesCatalog.setLongitude((float) 27.54556);
            nodesCatalog.setRegisteredTimestamp((int) (System.currentTimeMillis() / 1000L));

            NodesCatalogDao nodesCatalogDao = new NodesCatalogDao(create.configuration());
            nodesCatalogDao.insert(nodesCatalog);


            List<NodesCatalog> nodesCatalogArr = nodesCatalogDao.findAll();

            if (nodesCatalogArr != null) {
                Log.i("MainActivity", "nodesCatalogArr:"+nodesCatalogArr.size());

                TextView textView = (TextView) findViewById(R.id.textid);
                if (textView != null)
                    for (NodesCatalog nodesCatalog1 : nodesCatalogArr) {
                        textView.append(nodesCatalog1.getName() + ",");
                    }
            } else Log.i("MainActivity", "nodesCatalogArr:null");

            connection.close();
        } catch (Exception e) {
            Log.e("MainActivity", "Ex:", e);
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    Log.e(null, null, e);
                }
            }
        }
    }
}
