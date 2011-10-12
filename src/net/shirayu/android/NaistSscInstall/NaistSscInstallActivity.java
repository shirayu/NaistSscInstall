package net.shirayu.android.NaistSscInstall;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NaistSscInstallActivity extends Activity {
    /** Called when the activity is first created. */
	private void setButton(){   
        //set callback listener
        Button button = (Button) findViewById(R.id.install_button);
        File f = new File(Const.outpath);
        if( f.exists() ){
        	button.setEnabled(false);
        	button.setText( getResources().getString(R.string.file_already_exist) );
        }
        else{
        	button.setEnabled(true);
        	button.setText( getResources().getString(R.string.install_buttion_name) );
        	OnClickListener listener = new Install(this);
        	button.setOnClickListener( listener );
        };
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //activate link
		((TextView) findViewById(R.id.copyright_text)).setMovementMethod(LinkMovementMethod.getInstance());
		
        TextView tv = (TextView) findViewById(R.id.path_tv);
        tv.setText("Output to : " + Const.outpath + "\n\n");
        this.setButton();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
        this.setButton();	
    }
}
