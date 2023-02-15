package com.example.asm_networking.ASM.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.asm_networking.ASM.Activity.ProductDetailActivity;
import com.example.asm_networking.ASM.Models.Product;
import com.example.asm_networking.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> list;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void updateData(List<Product> data) {
        list.clear();
        list.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int _i, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if (view == null) {
            view = View.inflate(_viewGroup.getContext(), R.layout.layout_item_pet, null);
            TextView name = view.findViewById(R.id.tv_productName);
            TextView price = view.findViewById(R.id.tv_productPrice);
            TextView quantity = view.findViewById(R.id.tv_productQuantity);
            ImageView image = view.findViewById(R.id.imv_product);
            ConstraintLayout layout = view.findViewById(R.id.layout_product_one_item);

            ViewHolder holder = new ViewHolder(name, price, quantity, image, layout);
            view.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Product product = (Product) getItem(_i);
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(product.getPrice() + " đồng");
        holder.textViewProductQuantity.setText("Còn lại: " + product.getQuantity());
        Glide.with(context).load(product.getImage()).into(holder.imageViewProduct);
        Log.d(">>>>>>>", "getView: "+holder.imageViewProduct);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("name", product.getName());
                intent.putExtra("quantity", product.getQuantity());
                intent.putExtra("category_id", product.getCategory_id());
                intent.putExtra("image", product.getImage());
                context.startActivity(intent);
            }
        });

        return view;
    }

    private static class ViewHolder {
        final TextView textViewProductName, textViewProductQuantity, textViewProductPrice;
        final ImageView imageViewProduct;
        final ConstraintLayout layout;

        public ViewHolder(TextView textViewProductName, TextView textViewProductQuantity, TextView textViewProductPrice, ImageView imageViewProduct, ConstraintLayout layout) {
            this.textViewProductName = textViewProductName;
            this.textViewProductQuantity = textViewProductQuantity;
            this.textViewProductPrice = textViewProductPrice;
            this.imageViewProduct = imageViewProduct;
            this.layout = layout;
        }
    }
}
