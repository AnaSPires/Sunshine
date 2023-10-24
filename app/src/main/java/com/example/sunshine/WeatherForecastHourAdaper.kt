import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunshine.databinding.ForecastHourItemBinding
import com.example.sunshine.model.Hour
import com.example.sunshine.utils.SunshineWeatherUtils

class WeatherForecastHourAdaper(private val weatherForecastHourData: List<Hour>) :
    RecyclerView.Adapter<WeatherForecastHourAdaper.ForecastItemViewHolder>() {

    class ForecastItemViewHolder(val binding: ForecastHourItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hour){
            val url = "https:${item.condition.icon}"
            Glide.with(itemView.context)
                .load(url)
                .into(binding.weatherIcon)
            binding.hour.text = item.time.substring(10)
            binding.temperature.text = SunshineWeatherUtils().formatTemperature(itemView.context,item.tempC, item.tempF)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ForecastItemViewHolder {
        return ForecastItemViewHolder(ForecastHourItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
                )
    }

    override fun onBindViewHolder(holder: ForecastItemViewHolder, position: Int) {
        holder.bind(weatherForecastHourData[position])

    }

    override fun getItemCount() = weatherForecastHourData.size
}
