package net.shirayu.android.NaistSscInstall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Install implements OnClickListener{
	private Context context;
	
	Install(Context context){
		this.context = context;
	}
    @Override
    public void onClick(View v) {
    	this.install();
    	Button button = (Button) ((Activity) context).findViewById(R.id.install_button);
    	button.setEnabled(false);
    	button.setText( context.getResources().getString(R.string.install_finished) );
    }
    private void install(){
    	File dirs = new File(Const.outdir);
    	if (!dirs.exists()) {
    		dirs.mkdirs();
    	}
    	
    	InputStream input = context.getResources().openRawResource(R.raw.naist_server_ca);
    	
        FileOutputStream output=null;
		try {
			output = new FileOutputStream( Const.outpath , true);
		} catch (FileNotFoundException e1) {
        	Toast.makeText(context, context.getResources().getString(R.string.file_already_exist), Toast.LENGTH_SHORT).show();
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
        	Toast.makeText(context, context.getResources().getString(R.string.install_failed) , Toast.LENGTH_SHORT).show();
        	return;
		}
    	Toast.makeText(context, context.getResources().getString(R.string.install_finished), Toast.LENGTH_SHORT).show();
    }
}
