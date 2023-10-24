import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunshine.data.SunshinePreferences
import com.example.sunshine.databinding.ForecastItemBinding
import com.example.sunshine.databinding.ForecastPrimaryInfoItemBinding
import com.example.sunshine.model.ForecastDay
import com.example.sunshine.model.WeatherForecast
import com.example.sunshine.utils.SunshineDateUtils
import com.example.sunshine.utils.SunshineWeatherUtils

class WeatherForecastAdaper(private val weatherForecastData: WeatherForecast, private val onItemClick:(item: ForecastDay)->Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ForecastItemViewHolder(private val binding: ForecastItemBinding, val onItemClick:(item: ForecastDay)->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastDay){
            val url = "https:${item.day.condition.icon}"
            Glide.with(itemView.context)
                .load(url)
                .into(binding.weatherIcon)
            binding.date.text = SunshineDateUtils().getFriendlyDateString(itemView.context,
                item.date, false)
            binding.weatherDescription.text = item.day.condition.text
            binding.highTemperature.text = SunshineWeatherUtils().formatTemperature(itemView.context,item.day.maxTempC, item.day.maxTempF)
            binding.lowTemperature.text = SunshineWeatherUtils().formatTemperature(itemView.context,item.day.minTempC, item.day.minTempF)
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class ForecastTodayViewHolder(private val binding: ForecastPrimaryInfoItemBinding, val onItemClick:(item: ForecastDay)->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastDay){
            val url = "https:${item.day.condition.icon}"
            Glide.with(itemView.context)
                .load(url)
                .into(binding.weatherIcon)
            binding.location.text = SunshinePreferences().getPreferredWeatherLocation(itemView.context)
            binding.date.text = SunshineDateUtils().getFriendlyDateString(itemView.context,
                item.date, false)
            binding.weatherDescription.text = item.day.condition.text
            binding.highTemperature.text = SunshineWeatherUtils().formatTemperature(itemView.context,item.day.maxTempC, item.day.maxTempF)
            binding.lowTemperature.text = SunshineWeatherUtils().formatTemperature(itemView.context,item.day.minTempC, item.day.minTempF)
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            FORECAST_TODAY_ITEM ->  ForecastTodayViewHolder(
                ForecastPrimaryInfoItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false),
                onItemClick)
            else -> ForecastItemViewHolder(ForecastItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false),
                onItemClick
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            FORECAST_TODAY_ITEM ->{(holder as ForecastTodayViewHolder).bind(weatherForecastData.forecast.forecastDay[position])}
            else -> {(holder as ForecastItemViewHolder).bind(weatherForecastData.forecast.forecastDay[position])}
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            FORECAST_TODAY_ITEM
        }
        else{
            FORECAST_ITEM
        }
    }

    override fun getItemCount() = weatherForecastData.forecast.forecastDay.size

    companion object {
        const val FORECAST_ITEM = 0
        const val FORECAST_TODAY_ITEM = 1
    }

}
