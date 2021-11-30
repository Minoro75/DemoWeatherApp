package io.minoro75.heremapsweatherapp.ui.city_weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.domain.forecast_entity.Weather

class WeatherAdapter(private val weatherList: ArrayList<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int =
        weatherList.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItem: Weather) {
            itemView.findViewById<TextView>(R.id.tvTemperature).text = weatherItem.temperature
            itemView.findViewById<TextView>(R.id.tvDescription).text = weatherItem.description
            itemView.findViewById<TextView>(R.id.tvDate).text = weatherItem.utcTime
            Glide.with(itemView)
                .load(weatherItem.iconLink)
                .centerCrop()
                .into(itemView.findViewById(R.id.ivIcon))
        }
    }

    fun addList(list: List<Weather>?) {
        if (list != null) {
            weatherList.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        weatherList.clear()
        notifyDataSetChanged()
    }
}
