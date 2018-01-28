package com.teslyuk.android.androidtutorial_filespreferences.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teslyuk.android.androidtutorial_filespreferences.R;

/**
 * Created by taras.teslyuk on 11/16/15.
 */
public class FileListAdapter extends BaseAdapter {

    public interface FileListAdapterEventListener {
        public void removeFile(int position);

        public void previewFile(int position);
    }

    private final String TAG = getClass().getSimpleName();

    private String[] array;
    private int selectedItem = -1;
    private Context context;
    FileListAdapterEventListener listener;

    public FileListAdapter(Context context, String[] array) {
        this.context = context;
        this.array = array;
        Log.d(TAG, "items count: " + array.length);
    }

    public void addListener(FileListAdapterEventListener listener) {
        this.listener = listener;
    }

    static class ViewHolder {
        protected TextView name;
        protected ImageView delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            final ViewHolder viewHolder = new ViewHolder();
            view = inflator.inflate(R.layout.item_file, null);
            viewHolder.name = (TextView) view.findViewById(R.id.item_file_name);
            viewHolder.delete = (ImageView) view.findViewById(R.id.item_file_remove);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder.name != null) {
            holder.name.setText(array[position]);
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.previewFile(position);
                    }
                }
            });
        }

        if (holder.delete != null) {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.removeFile(position);
                    }
                }
            });
        }

        if (view == null) {
            Log.d(TAG, "NULL VIEW position:" + position);
        }
        return view;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return array[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void setSelectedItemPos(int position) {
        //Logger.d(TAG,"setSelectedItemPos:"+position);
        selectedItem = position;
    }
}