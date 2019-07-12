package com.example.madcamp_3;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_tab2_Adapter extends RecyclerView.Adapter<Recycler_tab2_Adapter.ItemViewHolder> {

    private ArrayList<buysell_item> listItem = new ArrayList<>();
    Context mcontext;
    public Recycler_tab2_Adapter(ArrayList<buysell_item> recyclerItems) {
        listItem = recyclerItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buysell_item, parent, false);
        mcontext = parent.getContext();
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listItem.get(position));
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }

     void addItem(buysell_item recyclerItem) {
            listItem.add(recyclerItem);
      }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView year;
        private TextView price;
        private TextView won;
        private ImageView image;
        private ImageView heart;
        private LinearLayout linearLayout;
        private buysell_item recyclerItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vehicle_name);
            year = itemView.findViewById(R.id.buy_year);
            price = itemView.findViewById(R.id.price);
            won = itemView.findViewById(R.id.won);
            image = itemView.findViewById(R.id.vehicle_image);
            heart = itemView.findViewById(R.id.button_favorite);
            linearLayout = itemView.findViewById(R.id.linearItem);
        }

        void onBind(buysell_item recyclerItem) {
            this.recyclerItem = recyclerItem;

            name.setText(recyclerItem.getName());
            year.setText(recyclerItem.getYear());
            image.setImageResource(recyclerItem.getImageId());
            price.setText(recyclerItem.getYear());
            heart.setImageDrawable();

            name.setOnClickListener(this);
            year.setOnClickListener(this);
            image.setOnClickListener(this);
            price.setOnClickListener(this);
            heart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearItem_id:
                    show(view);
                    break;
                case R.id.textView_id:
                    show(view);
                    break;
                case R.id.contactView_id:
                    show(view);
                    break;
                case R.id.imageView_id:
                    show(view);
                    break;
            }
        }

        void show(View view) {
            final Context context = view.getContext();

            final List<String> InfoItems = new ArrayList<>();
            List<String> ToastItems = new ArrayList<>();
            final List<String> nameofItems = new ArrayList<>();

            InfoItems.add(recyclerItem.getName());
            InfoItems.add(recyclerItem.getUsername());
            InfoItems.add(recyclerItem.getEmail());
            InfoItems.add(recyclerItem.getAddress());
            InfoItems.add(recyclerItem.getPhone());
            InfoItems.add(recyclerItem.getWebsite());
            InfoItems.add(recyclerItem.getCompany());

            ToastItems.add("Name: \n" + recyclerItem.getName());
            ToastItems.add("Username: \n" + recyclerItem.getUsername());
            ToastItems.add("Email: \n" + recyclerItem.getEmail());
            ToastItems.add("Address: \n" + recyclerItem.getAddress());
            ToastItems.add("Phone Number: \n" + recyclerItem.getPhone());
            ToastItems.add("Website: \n" + recyclerItem.getWebsite());
            ToastItems.add("Company: \n" + recyclerItem.getCompany());

            nameofItems.add("Name");
            nameofItems.add("Username");
            nameofItems.add("Email");
            nameofItems.add("Address");
            nameofItems.add("Phone Number");
            nameofItems.add("Website");
            nameofItems.add("Company");

            final CharSequence[] Infos = ToastItems.toArray(new String[ToastItems.size()]);
            final CharSequence[] ContactNumber = InfoItems.toArray(new String[InfoItems.size()]);
            final CharSequence[] NameItem = nameofItems.toArray(new String[InfoItems.size()]);

            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
            builder.setTitle("Contact Info of " + recyclerItem.getName());
            builder.setItems(Infos, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 4) {
                        Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ContactNumber[4].toString()));
                        context.startActivity(dial);

                    } else {
                        String selectedInfo = NameItem[i].toString();
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("label", ContactNumber[i].toString());
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(context, selectedInfo + " has been copied on Clipboard", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "Closed " + recyclerItem.getName() + "'s Contact Info", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
    }

    public void filterList(ArrayList<RecyclerItem> filteredList) {
        listItem = filteredList;
        notifyDataSetChanged();
    }
}
