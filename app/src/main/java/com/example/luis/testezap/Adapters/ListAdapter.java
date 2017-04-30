package com.example.luis.testezap.Adapters;

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

import com.example.luis.testezap.Activities.InfoActivity;
import com.example.luis.testezap.Entities.Property;
import com.example.luis.testezap.R;
import com.example.luis.testezap.Utils.Money;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luis on 27/04/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>  {
    protected static final String TAG = "livroandroid";
    private final List<Property> propertyList;
    private final Context context;




    public ListAdapter(Context context, List<Property> propertyList ) {
        this.context = context;
        this.propertyList = propertyList;


    }

    @Override
    public int getItemCount()
    {
        return this.propertyList != null ? this.propertyList.size() : 0;
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
        final Property property = propertyList.get(position);
        holder.codProperty.setText(property.getCodProperty()+"");
        holder.type.setText(property.getTypeProprety());
        holder.address.setText(property.getAddressTxt());
        holder.rooms.setText(context.getString(R.string.rooms)+" : "+property.getRooms());
        holder.value.setText(context.getString(R.string.price)+" : "+ Money.formatMoney(property.getPrice()));
        try {
            Picasso.with(context).load(property.getUrlImage()).memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(holder.img, new Callback() {
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
                intent.putExtra("codProperty" ,property.getCodProperty());
                context.startActivity(intent);

            }
        });
    }

    // ViewHolder com as views
    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView codProperty;
        public TextView type;
        public TextView address;
        public TextView rooms;
        public TextView value;
        public ImageView img;
        public ProgressBar progress;
        public CardView cardView;


        public ListViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            codProperty = (TextView) view.findViewById(R.id.codProperty);
            type = (TextView) view.findViewById(R.id.type);
            address= (TextView) view.findViewById(R.id.address);
            rooms = (TextView) view.findViewById(R.id.usefullArea);
            value = (TextView) view.findViewById(R.id.totalArea);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
