package com.example.giladram.trainingapplication.Utilities;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.giladram.trainingapplication.R;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by giladram on 28/11/17.
 */

public class GoogleResultListAdapter extends RecyclerView.Adapter<GoogleResultListAdapter.ViewHolder> {

    private Elements m_searchResult;

    public GoogleResultListAdapter(Elements searchResults){
        m_searchResult = searchResults;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.google_result_text_view);
        }
    }

    @Override
    public GoogleResultListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.google_result_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        GoogleResultListAdapter.ViewHolder vh = new GoogleResultListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GoogleResultListAdapter.ViewHolder holder, int position) {
        Element elementResult = m_searchResult.get(position);
        Elements hrefResult = elementResult.select("a._Olt");
        String result = hrefResult.html();
        holder.mTextView.setText(Html.fromHtml(result));
        holder.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return m_searchResult.size();
    }


}
