package love_music;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.valetine.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import app.Constants;
import app.ImageLoader;
import app.JSONfunctions;


public class Love_songs extends Activity {
	
private ListView list;
//
JSONObject jsonobject;
JSONArray jsonarray;
//
//ListViewAdapter adapter;
ProgressDialog mProgressDialog;
ArrayList<HashMap<String, String>> arraylist;
//
static String      SPEECHTITLE ="speechTitle";	
static String	    FILEREFERENCE ="fileReference";	
static String 		FLAG  ="flag";
//
//
private Custom_mp4v adapter;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.love_tips);

		new DownloadJSON().execute();
		
    
 }
 
 
public class Custom_mp4v extends  BaseAdapter {
//
	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();
//
	public Custom_mp4v(Context context,	ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ViewHolder")
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView title,fileref;			
		String 	speechdate,filereference;;
		ImageView flag;

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	View itemView = inflater.inflate(R.layout.tadaptor, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		title = (TextView) itemView.findViewById(R.id.title);		
		fileref=(TextView) itemView.findViewById(R.id.likes);
		flag = (ImageView) itemView.findViewById(R.id.flag);
		
		
		fileref.setText(resultp.get("fileReference"));
		title.setText(resultp.get("title"));		
		filereference=resultp.get("fileReference");
		
	
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		//imageLoader.DisplayImage(resultp.get(getActivity().FLAG), flag);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
			resultp = data.get(position);
			Intent intent = new Intent(context, Play_love.class);
//				
				intent.putExtra("title", resultp.get("title"));
//			
				intent.putExtra("fileReference", resultp.get("fileReference"));
			
				// Pass all data flag
			
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}


// DownloadJSON AsyncTask
private class DownloadJSON extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Create a progressdialog
		mProgressDialog = new ProgressDialog(Love_songs.this);
		// Set progressdialog title
		//mProgressDialog.setTitle("Android JSON Parse Tutorial");
		// Set progressdialog message
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setIndeterminate(false);
		// Show progressdialog
		mProgressDialog.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// Create an array
		arraylist = new ArrayList<HashMap<String, String>>();
// Retrieve JSON Objects from the given URL address
		jsonobject = JSONfunctions.getJSONfromURL(Constants.LOVE_SONGS);

		try {
			// Locate the array name in JSON
			jsonarray = jsonobject.getJSONArray("mp4");
			Log.d("jsonobject.getString",jsonarray.length()+"" );
			
			for (int i = 0; i < jsonarray.length(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				jsonobject = jsonarray.getJSONObject(i);
		map.put("Id", jsonobject.getString("id"));
		
		map.put("title", jsonobject.getString("name"));				
		map.put("fileReference", Constants.URL_MP4+jsonobject.getString("file_name"));
				//map.put("flag", app.AppConst.URL_SPEECH_ICON+jsonobject.getString("icon_file"));
				
		arraylist.add(map);
			}
		} catch (JSONException e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void args) {
		// Locate the listview in listview_main.xml
		list = (ListView)findViewById(R.id.listView);
		// Pass the results into ListViewAdapter.java
		adapter = new Custom_mp4v(Love_songs.this, arraylist);
		// Set the adapter to the ListView
		list.setAdapter(adapter);
		// Close the progressdialog
		mProgressDialog.dismiss();
	}
}
}