package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.widget.UpdateBakeService;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;

    private Context mContext;

    private ArrayList<String> bakeStrArray;


    {
        ingredientList = new ArrayList<>();
        bakeStrArray = new ArrayList<>();

    }

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param mContext       The current context. Used to inflate the layout file.
     * @param ingredientList A List of Movie objects to display in a list
     */
    public IngredientAdapter(Context mContext, List<Ingredient> ingredientList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        this.mContext = mContext;
        this.ingredientList = ingredientList;


    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  *                  can use this viewType integer to provide a different layout.
     * @return A new MovieViewHolder that holds the View for each list item
     */

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredient_card, viewGroup, false);

        return new IngredientViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *                   item at the given position in the data set.
     * @param position   The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(final IngredientViewHolder viewHolder, int position) {


        Ingredient ingredient = ingredientList.get(position);

        viewHolder.ingredients.setText(ingredient.getIngredient());
        viewHolder.measure.setText(ingredient.getMeasure());
        viewHolder.quantity.setText(String.valueOf(ingredient.getQuantity()));

        bakeStrArray.add(ingredientList.get(position).getIngredient());


        UpdateBakeService.startBakingService(mContext, bakeStrArray);


    }


    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        return ingredientList.size();

    }

    /**
     * Cache of the children views for a list item.
     */


    public class IngredientViewHolder extends RecyclerView.ViewHolder {


        TextView ingredients;
        TextView measure;
        TextView quantity;
        public int id;
        public String image;


        public IngredientViewHolder(View itemView) {
            super(itemView);


            ingredients = itemView.findViewById(R.id.recipeIngred);
            measure = itemView.findViewById(R.id.recipemeasure);
            quantity = itemView.findViewById(R.id.recipeQuantity);
            /**
             * Constructor for our ViewHolder. Within this constructor, we get a reference to our
             * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
             * onClick method below.
             * @param itemView The View that you inflated in
             *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
             */


            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Ingredient clickedIngredientItem = ingredientList.get(pos);


                    }
                }


            });


        }


    }
}