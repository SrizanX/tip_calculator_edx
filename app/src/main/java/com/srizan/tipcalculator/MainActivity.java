package com.srizan.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;


    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAUL_TIP_PERCENTAGE = 15;
    float EXCELLENT_TIP_PERCENTAGE = 20;

    float totalBillAmount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tipPercent, percentage));
        tvTipAmount.setText(getString(R.string.main_msg_tipTotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billTotalResult, finalBillAmount));
    }


    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAUL_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged
            (R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if(percentage==0)
            percentage = DEFAUL_TIP_PERCENTAGE;

        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());

        else
            totalBillAmount = 0;
        tipTotal = (totalBillAmount*percentage)/100;
        finalBillAmount = totalBillAmount + tipTotal;
    }
}
