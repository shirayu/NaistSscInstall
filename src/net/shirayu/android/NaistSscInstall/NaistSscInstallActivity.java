package net.shirayu.android.NaistSscInstall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NaistSscInstallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //activate link
		((TextView) findViewById(R.id.copyright_text)).setMovementMethod(LinkMovementMethod.getInstance());
		
        TextView tv = (TextView) findViewById(R.id.path_tv);
        final String outpath = Environment.getExternalStorageDirectory().getPath() + "/selfssl/";
        final String filename = "naist_server_ca.crt";
        tv.setText("Output to : " + outpath + filename + "\n\n");
        
        //set callback listener
        Button button = (Button) findViewById(R.id.install_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	this.install();
            }
            private void install(){
            	File dirs = new File(outpath);
            	if (!dirs.exists()) {
            		dirs.mkdirs();
            	}
            	
            	InputStream input = NaistSscInstallActivity.this.getResources().openRawResource(R.raw.naist_server_ca);
            	
                FileOutputStream output=null;
				try {
					output = new FileOutputStream(outpath + filename, true);
				} catch (FileNotFoundException e1) {
                	Toast.makeText(NaistSscInstallActivity.this, "File Already Exist..", Toast.LENGTH_SHORT).show();
                	return;
				};
				
	            try{
					int c;
					while ((c = input.read()) != -1){
						output.write(c); 
					}
					input.close();
					output.close();
            	}
            	catch (Exception e) {
                	Toast.makeText(NaistSscInstallActivity.this, "Install Failed..", Toast.LENGTH_SHORT).show();
                	return;
				}
            	Toast.makeText(NaistSscInstallActivity.this, "Install Finished!", Toast.LENGTH_SHORT).show();
            }
        }

        );
        
    }
}
