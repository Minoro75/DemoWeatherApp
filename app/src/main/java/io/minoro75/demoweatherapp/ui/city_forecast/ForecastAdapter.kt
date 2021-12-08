package io.minoro75.demoweatherapp.ui.city_forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.minoro75.demoweatherapp.R
import io.minoro75.demoweatherapp.domain.forecast.model.Forecast

class ForecastAdapter(private val weatherList: ArrayList<Forecast>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int =
        weatherList.size

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecast: Forecast) {
            /* val responseUtcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
             val date = responseUtcFormat.parse(forecast.utcTime)
             val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()) */
            itemView.findViewById<TextView>(R.id.tvTemperature).text =
                itemView.context.getString(
                    R.string.temperature_item,
                    forecast.temperature?.min,
                    forecast.temperature?.max
                )
            itemView.findViewById<TextView>(R.id.tvDescription).text = forecast.iconList.first().iconDescription
            itemView.findViewById<TextView>(R.id.tvDate).text = forecast.currentTime.toString()
            Glide.with(itemView)
                .load("https://openweathermap.org/img/wn/${forecast.iconList.first().iconLink}.png")
                .into(itemView.findViewById(R.id.ivIcon))
        }
    }

    fun addList(list: List<Forecast>?) {
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
