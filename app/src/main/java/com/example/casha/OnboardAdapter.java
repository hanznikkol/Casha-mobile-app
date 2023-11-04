package com.example.casha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardingViewHolder>{

    private List<OnboardItem> onboardItems;

    public OnboardAdapter(List<OnboardItem> onboardItems){
        this.onboardItems = onboardItems;

    }
    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardDate(onboardItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle, textDescription;
        private ImageView imgOnboard;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.tvTitleOnboard);
            textDescription = itemView.findViewById(R.id.tvDescOnboard);
            imgOnboard = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnboardDate (OnboardItem onboardItem){
            textTitle.setText(onboardItem.getTitle());
            textDescription.setText(onboardItem.getDescription());
            imgOnboard.setImageResource(onboardItem.getImage());
        }
    }
}
