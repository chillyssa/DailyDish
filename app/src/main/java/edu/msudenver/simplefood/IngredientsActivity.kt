package edu.msudenver.simplefood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientsActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {

    private inner class IngredientsHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtIngredient: TextView = view.findViewById(R.id.txtIngredient)
        val txtIngredientUnit: TextView = view.findViewById(R.id.txtIngredientUnit)

    }

    //TODO replace Item with whatever the model is.
    private inner class IngredientAdapter(var ingredient: List<Item>, var onClickListener: View.OnClickListener, var onLongClickListener: View.OnLongClickListener): RecyclerView.Adapter<IngredientsHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_list, parent, false)
            return IngredientsHolder(view)
        }

        override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
            //TODO update item.name and item.category to correct variables.
            val ingredient = ingredient[position]
            holder.txtIngredient.text = ingredient.name
            holder.txtIngredientUnit.text = ingredient.categoryAsString()


            holder.itemView.setOnClickListener(onClickListener)
            holder.itemView.setOnLongClickListener(onLongClickListener)
        }
        override fun getItemCount(): Int {
            return ingredient.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(v: View?): Boolean {
        TODO("Not yet implemented")
    }
}