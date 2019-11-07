package com.example.teht8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class ownAdapter extends ArrayAdapter<Picture> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<MyEntity> itemList;
    private Tietokanta t = Room.databaseBuilder(context, Tietokanta.class, "TIETOKANTA").build();

    public ownAdapter (Context context, int layoutResourceId, ArrayList<Picture> itemList){
        super(context, layoutResourceId, itemList);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        //this.itemList = itemList;
    }

    private class ViewHolder{
        TextView text;
        TextView text2;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        final ViewHolder viewHolder;

        if(convertView==null){

            convertView = inflater.inflate(R.layout.listtemplate, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.text =  convertView.findViewById(R.id.textViewUrl);
            viewHolder.text2 = convertView.findViewById(R.id.textViewName);
            viewHolder.image = convertView.findViewById(R.id.imgView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        System.out.println(position);
        viewHolder.text.setText("Owner: " + t.myTableDao().getOwners());
        viewHolder.text2.setText("License: " + t.myTableDao().getLicenses());

        //Toteutus picassolla ja pois kommentoituna Asynctaskin avulla.

        Picasso.get().load(t.myTableDao().getUrls()).into(viewHolder.image); //?

        //new DownloadImageTask(viewHolder.image).execute(itemList.get(position).getUrl());

        return convertView;

    }

    // Vaihtoehtoinen tapa hakea kuva
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
