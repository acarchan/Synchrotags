package fr.voltanite.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import fr.voltanite.noeud.NoMatchableNodeException;
import fr.voltanite.noeud.Noeud;
import fr.voltanite.noeud.NoeudsBDD;
import fr.voltanite.synchrotags.R;
import fr.voltanite.utils.Utils;

public class AddQRcode extends Activity {
	String qrcode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qrcode);
        Intent intent = getIntent();
        qrcode = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        findViewById(R.id.node_creation_parent_search).setOnClickListener(rechercheparent);
        findViewById(R.id.node_creation_metadata_add).setOnClickListener(meta);
        findViewById(R.id.node_creation_validate).setOnClickListener(validation);
        
        EditText paramParent = null;
        paramParent = (EditText)findViewById(R.id.node_creation_name_input);
        if(qrcode != null)
        	paramParent.setHint(qrcode);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_qrcode, menu);
        return true;
    }
    
    public final OnClickListener rechercheparent = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
		}
	};
	
	 public final OnClickListener meta = new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				startActivity(intent);
			}
		};
		
		 public final OnClickListener validation = new OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(getBaseContext(), MainActivity.class);
					
					TextView tnom = (TextView) findViewById(R.id.node_creation_name_input);
					String nom = tnom.getText().toString();
					TextView tpar = (TextView) findViewById(R.id.node_creation_parent_input);					
					int parent = Integer.parseInt(tpar.getText().toString());
					//String desc = findViewById(R.id.saisie_desc).toString();
					Noeud noeud = new Noeud(nom, qrcode, parent, 0);
					NoeudsBDD nbdd = new NoeudsBDD(getBaseContext());
					nbdd.open();
					try {
						nbdd.insertNoeud(noeud);
					} catch (NoMatchableNodeException e) {
						// TODO Auto-generated catch block
						Utils.popDebug(getBaseContext(), "Exception : " + e.getMessage());
					}
					nbdd.close();
					startActivity(intent);
				}
			};
}