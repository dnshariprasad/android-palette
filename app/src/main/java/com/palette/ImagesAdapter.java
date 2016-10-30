package com.palette;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Hari Prasad on 10/21/16.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.HubViewHolder> {
    class HubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_image;

        public HubViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }


        public void populate(ImagesModel imagesModel) {
            Glide.with(context).load(imagesModel.getUrl()).centerCrop().into(iv_image);
        }

        @Override
        public void onClick(View view) {
            DetailActivity.start(context, imagesModels.get(getLayoutPosition()).toJson().toString());
        }
    }

    private Context context;
    private List<ImagesModel> imagesModels;

    public ImagesAdapter(Context context, List<ImagesModel> contactModels) {
        this.context = context;
        this.imagesModels = contactModels;
    }

    public static void build(Context context, RecyclerView recyclerView, List<ImagesModel> imagesModels) {
        if (recyclerView.getAdapter() == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            ImagesAdapter messageModels = new ImagesAdapter(context, imagesModels);
            recyclerView.setAdapter(messageModels);
        } else {
            ((ImagesAdapter) recyclerView.getAdapter()).rebuild(imagesModels);
        }
    }

    private void rebuild(List<ImagesModel> imagesModels) {
        this.imagesModels = imagesModels;
        notifyDataSetChanged();
    }

    @Override
    public HubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.row_image, parent, false);
        return new HubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HubViewHolder holder, int position) {
        holder.populate(imagesModels.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesModels.size();
    }
}
