package com.example.luis.testeIlegra.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.luis.testeIlegra.Activities.InfoActivity;
import com.example.luis.testeIlegra.Entities.Characters;
import com.example.luis.testeIlegra.Entities.Property;
import com.example.luis.testeIlegra.R;
import com.example.luis.testeIlegra.Utils.Money;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luis on 27/04/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>  {
    protected static final String TAG = "livroandroid";
    private final List<Characters> charactersList;
    private final Context context;




    public ListAdapter(Context context, List<Characters> charactersList ) {
        this.context = context;
        this.charactersList = charactersList;


    }

    @Override
    public int getItemCount()
    {
        return this.charactersList != null ? this.charactersList.size() : 0;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adpter_property, viewGroup, false);

        // Cria o ViewHolder
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        // Atualiza a view
        final Characters characters = charactersList.get(position);
        holder.name.setText(characters.getName());
        holder.description.setText(characters.getDescription());
        try {
            Picasso.with(context).load(characters.getThumbnail()).memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(holder.img, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progress.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    holder.progress.setVisibility(View.GONE);
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , InfoActivity.class);
                intent.putExtra("codProperty" ,characters.getId());
                context.startActivity(intent);

            }
        });
    }

    // ViewHolder com as views
    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public ImageView img;
        public ProgressBar progress;
        public CardView cardView;



        public ListViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            description = (TextView) view.findViewById(R.id.description);
            name = (TextView) view.findViewById(R.id.name);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
