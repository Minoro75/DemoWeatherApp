package io.minoro75.demoweatherapp.ui.city_forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.minoro75.demoweatherapp.R
import io.minoro75.demoweatherapp.domain.forecasts.model.Weather
import java.text.SimpleDateFormat
import java.util.Locale

class ForecastAdapter(private val weatherList: ArrayList<Weather>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int =
        weatherList.size

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItem: Weather) {
            val responseUtcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
            val date = responseUtcFormat.parse(weatherItem.utcTime)
            val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
            itemView.findViewById<TextView>(R.id.tvTemperature).text =
                itemView.context.getString(R.string.temperature_item, weatherItem.temperature)
            itemView.findViewById<TextView>(R.id.tvDescription).text = weatherItem.description
            itemView.findViewById<TextView>(R.id.tvDate).text = dateFormat.format(date)
            Glide.with(itemView)
                .load(weatherItem.iconLink)
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
