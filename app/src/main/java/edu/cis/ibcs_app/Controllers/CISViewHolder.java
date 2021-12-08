package edu.cis.ibcs_app.Controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cis.ibcs_app.R;

public class CISViewHolder extends RecyclerView.ViewHolder {
    protected TextView nameText;
    protected TextView descriptionText;
    protected TextView priceText;
    protected TextView idText;
    protected TextView amountAvailableText;
    protected TextView typeText;

    public CISViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.nameTextView);
        descriptionText = itemView.findViewById(R.id.descriptionTextView);
        priceText = itemView.findViewById(R.id.priceTextView);
        idText = itemView.findViewById(R.id.menuItemIDTextView);
        amountAvailableText = itemView.findViewById(R.id.amountAvailableTextView6);
        typeText = itemView.findViewById(R.id.typeTextView);
    }
}
