package net.shirayu.android.NaistSscInstall;

import android.os.Environment;

public class Const {

	public static final String outdir;
	public static final String filename;
	public static final String outpath;
	
	static{
		filename = "naist_server_ca.crt";
		outdir = Environment.getExternalStorageDirectory().getPath() + "/selfssl/";
		outpath = outdir + filename;
	};
}
