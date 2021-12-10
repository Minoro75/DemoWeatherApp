package io.minoro75.demoweatherapp.ui.city_selection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import io.minoro75.demoweatherapp.domain.cities_suggestions.model.Suggestions

class CitySelectionAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    suggestions: List<Suggestions>
) : ArrayAdapter<Suggestions>(context, layoutResource, suggestions) {
    private val suggestionsList = mutableListOf<Suggestions>()

    init {
        suggestionsList.addAll(suggestions)
    }

    override fun getCount(): Int =
        suggestionsList.size

    override fun getItem(position: Int): Suggestions =
        suggestionsList[position]

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView =
            convertView as TextView? ?: LayoutInflater.from(context).inflate(
                layoutResource, parent,
                false
            ) as TextView
        view.text = "${suggestionsList[position].suggestions?.cityName}"
        return view
    }

    fun addAllDataToSuggestionsList(list: List<Suggestions>) {
        suggestionsList.clear()
        suggestionsList.addAll(list)
        notifyDataSetChanged()
    }
}
