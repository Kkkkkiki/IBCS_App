package edu.cis.ibcs_app.Controllers;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

public class CISAdapter extends RecyclerView.Adapter<CISViewHolder> {

    //arraylists that contains data
    ArrayList<String> menuItemNames;
    ArrayList<String> menuItemDescription;
    ArrayList<Double> menuItemPrice;
    ArrayList<String> menuItemID;
    ArrayList<Integer> menuItemAmountAvailable;
    ArrayList<String> menuItemType;

    //constructor
    public CISAdapter(ArrayList<String> menuItemNames, ArrayList<String> menuItemDescription, ArrayList<Double> menuItemPrice, ArrayList<String> menuItemID, ArrayList<Integer> menuItemAmountAvailable, ArrayList<String> menuItemType) {
        this.menuItemNames = menuItemNames;
        this.menuItemDescription = menuItemDescription;
        this.menuItemPrice = menuItemPrice;
        this.menuItemID = menuItemID;
        this.menuItemAmountAvailable = menuItemAmountAvailable;
        this.menuItemType = menuItemType;
    }
    public CISAdapter(String[] strings){
        this.menuItemNames = new ArrayList<>();
        this.menuItemNames = new ArrayList<>();
        this.menuItemDescription = new ArrayList<>();
        this.menuItemPrice = new ArrayList<>();
        this.menuItemID = new ArrayList<>();
        this.menuItemAmountAvailable = new ArrayList<>();
        this.menuItemType = new ArrayList<>();

        for (String menuItem : strings){
            //TBD, THE MENUiTEM HERE IS NULL
            String[] itemStrings = menuItem.split("\\|");

            menuItemNames.add(itemStrings[0]);
            menuItemDescription.add(itemStrings[1]);
            menuItemPrice.add(Double.valueOf(itemStrings[2]));
            menuItemAmountAvailable.add(Integer.valueOf(itemStrings[3]));
            menuItemType.add(itemStrings[4]);
            menuItemID.add(itemStrings[5]);
        }
//first split the ;, then split the |

//        for (int i = 0; i < strings.length; i++) {
//            String[] itemStrings = strings
//        }
    }

    @NonNull
    @Override
    public CISViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cis_row_view, parent, false);
        CISViewHolder holder = new CISViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CISViewHolder holder, int position) {
        //go through each one the folder and tell each one where to display.
        //get the texts from the arraylist and set them as the view
        holder.nameText.setText(menuItemNames.get(position));
        holder.descriptionText.setText(menuItemDescription.get(position));
        holder.priceText.setText(String.valueOf(menuItemPrice.get(position)));
        holder.idText.setText(menuItemID.get(position));
//        holder.amountAvailableText.setText(menuItemAmountAvailable.get(position));
        holder.typeText.setText(menuItemType.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItemNames.size();
    }

}
