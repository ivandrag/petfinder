import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.petfinder.R
import com.petfinder.databinding.ItemAnimalBinding
import com.petfinder.presentation.animalList.model.UiAnimal

class AnimalAdapter(
    private val onItemClickListener: (Int) -> Unit
) : PagingDataAdapter<UiAnimal, AnimalAdapter.AnimalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimalBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return AnimalViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val uiAnimal = getItem(position)
        uiAnimal?.let { holder.bind(it) }
    }

    class AnimalViewHolder(
        private val binding: ItemAnimalBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uiAnimal: UiAnimal) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(uiAnimal.photo)
                    .error(R.drawable.ic_dog_placeholder)
                    .centerCrop()
                    .into(animalImageView)
                nameTextView.text = uiAnimal.name
                genderTextView.text = uiAnimal.gender
            }
            itemView.setOnClickListener {
                onItemClickListener(uiAnimal.id)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiAnimal>() {
            override fun areItemsTheSame(oldItem: UiAnimal, newItem: UiAnimal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UiAnimal, newItem: UiAnimal): Boolean {
                return oldItem == newItem
            }
        }
    }
}
