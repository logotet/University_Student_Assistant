package com.logotet.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityHolder> {

    private List<UniversityEntity> universityEntityList = new ArrayList<>();
    private UniversityHolder.OnItemPressedListener listener;
    private String fragmentCreatorTag;

    public UniversityAdapter(UniversityHolder.OnItemPressedListener listener, String fragmentCreatorTag) {
        this.listener = listener;
        this.fragmentCreatorTag = fragmentCreatorTag;
    }

    public void updateData(List<UniversityEntity> data) {
        universityEntityList.clear();
        universityEntityList.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UniversityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new UniversityHolder(view, listener, fragmentCreatorTag);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityHolder holder, int position) {
        UniversityEntity universityEntity = universityEntityList.get(position);
        holder.name.setText(universityEntity.getName());
        holder.location.setText(String.format("%s, %s", universityEntity.getCity(), universityEntity.getState()));
        holder.address.setText(universityEntity.getAddress());
        holder.webPage.setText(universityEntity.getWebPage());
        holder.setUniversity(universityEntity);
    }

    @Override
    public int getItemCount() {
        return universityEntityList.size();
    }

    public static class UniversityHolder extends RecyclerView.ViewHolder {
        private UniversityEntity universityEntity;
        private TextView name, location, address, webPage;
        private ImageButton imageButton;
        private String tag = "";

        public UniversityHolder(@NonNull View itemView, OnItemPressedListener listener, String tag) {
            super(itemView);
            this.tag = tag;
            name = itemView.findViewById(R.id.txt_uni_name);
            location = itemView.findViewById(R.id.txt_location);
            address = itemView.findViewById(R.id.txt_address);
            webPage = itemView.findViewById(R.id.txt_web_address);
            imageButton = itemView.findViewById(R.id.img_favourite);
            toggleFavButton(imageButton, tag);
            itemView.setOnClickListener(view -> {
//                    TODO: button should stay pressed
                imageButton.setPressed(true);
                Toast.makeText(itemView.getContext(), name.getText().toString(), Toast.LENGTH_LONG).show();
            });
            imageButton.setOnClickListener(view -> listener.onFavButtonClicked(universityEntity));
            webPage.setOnClickListener(view -> { listener.onWebPageClicked(universityEntity); });
            address.setOnClickListener(view -> listener.onAddressClicked(universityEntity));
        }

        public void toggleFavButton(ImageButton button, String tag){
            if(tag.equals(AppConstants.FRAGMENT_MY_UNIVERSITIES)){
                button.setPressed(true);
            }
        }

        public void setUniversity(UniversityEntity unversity) {
            this.universityEntity = unversity;
        }

        public interface OnItemPressedListener {
            void onFavButtonClicked(UniversityEntity entity);
            void onWebPageClicked(UniversityEntity entity);
            void onAddressClicked(UniversityEntity entity);
        }


    }
}
