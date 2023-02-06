package love_corner;

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

public class Love_tips extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	static String ID  = "id";
	static String PERSON  = "person";
	static String CONTENT  = "content";
	static String FLAG = "flag";	
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.love_tips);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setTitle("LOVE TIPS");
		new DownloadJSON().execute();
		
	}
	
	

	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(Love_tips.this);
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
			jsonobject = JSONfunctions.getJSONfromURL(Constants.LOVE_TIPS);

			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("love");

				Log.d("Error  jsonarray",jsonarray+"");
				
				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
					map.put("id", jsonobject.getString("id"));
					map.put("person", jsonobject.getString("aurthor"));
					map.put("content", jsonobject.getString("content"));					
					
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
			listview = (ListView) findViewById(R.id.listView);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(Love_tips.this, arraylist);
			// Set the adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
	
	
	
	
	public class ListViewAdapter extends BaseAdapter {

		// Declare Variables
		Context context;
		LayoutInflater inflater;
		ArrayList<HashMap<String, String>> data;
		ImageLoader imageLoader;
		HashMap<String, String> resultp = new HashMap<String, String>();

		public ListViewAdapter(Context context,	ArrayList<HashMap<String, String>> arraylist) {
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
			TextView person;
			TextView content;
			String  id;
			ImageView flag;

			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View itemView = inflater.inflate(R.layout.love_list, parent, false);
			// Get the position
			resultp = data.get(position);

			// Locate the TextViews in listview_item.xml
			person = (TextView) itemView.findViewById(R.id.textView1);
			content = (TextView) itemView.findViewById(R.id.textView2);			
			flag = (ImageView) itemView.findViewById(R.id.flag);
			
			// Capture position and set results to the TextViews
			person.setText(resultp.get(Love_tips.PERSON));			
			content.setText(resultp.get(Love_tips.CONTENT));
			
			
			id=resultp.get(Love_tips.ID);
			
			Log.d("image", id);
			// Capture position and set results to the ImageView
			// Passes flag images URL into ImageLoader.class
//imageLoader.DisplayImage(resultp.get(Love_tips.FLAG), flag);
			// Capture ListView item click
			itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Get the position
					resultp = data.get(position);
					Intent intent = new Intent(context, Love_Read.class);
					
					intent.putExtra("id",resultp.get(Love_tips.ID));
					
					intent.putExtra("author", resultp.get(Love_tips.PERSON));
				
					intent.putExtra("content", resultp.get(Love_tips.CONTENT));
				
					
					// Pass all data flag
					//intent.putExtra("flag", resultp.get(Love_tips.FLAG));
					// Start SingleItemView Class
					
					//Log.d("image", resultp.get(Love_tips.FLAG));
					context.startActivity(intent);

				}
			});
			return itemView;
		}
	}

	
	
}
