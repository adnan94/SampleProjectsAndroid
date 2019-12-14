package com.tilismtech.demobindingadaptor;
import android.support.v7.widget.RecyclerView;
import com.tilismtech.demobindingadaptor.databinding.SingleItemBinding;

public class personViewHolder extends RecyclerView.ViewHolder {
     SingleItemBinding binding;

    public personViewHolder(SingleItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String url, RecyclerAdaptor adapter) {
        binding.setUrl(url);
        binding.setAdapter(adapter);
        binding.executePendingBindings();

    }

    public void recycle() {
//        binding.categoryImage.setImageURI("");
//        binding.categoryName.setText("");
    }
}
