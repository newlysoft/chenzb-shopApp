package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.utils.GlideUtils;

/**
 * 商品规格点击进入的页面
 */
public class ProductFormatActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView productView=null;

    private int productNumber=1;

    private Button btnSbutraction=null;
    private Button btnAdd=null;
    private EditText etProductNumber=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_format);
        productView= (ImageView) findViewById(R.id.imageView);
        btnSbutraction= (Button) findViewById(R.id.btnSbutraction);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        etProductNumber= (EditText) findViewById(R.id.etProductNumber);
        //intent 接收上个页面传递过来的数据
        Bundle bundle=getIntent().getBundleExtra("bundle");
        ProductBean.DataBean.ListBean listBean= (ProductBean.DataBean.ListBean) bundle.getSerializable("listBean");
        //加载网络图片
        GlideUtils.GlideLoadImage(this,productView,listBean.getPicUrl());
        btnSbutraction.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    /**
     *
     * 按钮的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSbutraction:
                 productNumber--;
                    if (productNumber<0){
                        productNumber=0;
                    }
                etProductNumber.setText(Integer.toString(productNumber));
                break;

            case R.id.btnAdd:
                productNumber++;
                etProductNumber.setText(Integer.toString(productNumber));
                break;
        }
    }
}
