package com.habitrpg.android.habitica.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.helpers.PurchaseTypes;
import com.habitrpg.android.habitica.proxy.ifce.CrashlyticsProxy;
import com.habitrpg.android.habitica.ui.GemPurchaseOptionsView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;

public class GemsPurchaseFragment extends BaseFragment {

    @BindView(R.id.gems_4_view)
    GemPurchaseOptionsView gems4View;
    @BindView(R.id.gems_21_view)
    GemPurchaseOptionsView gems21View;
    @BindView(R.id.gems_42_view)
    GemPurchaseOptionsView gems42View;
    @BindView(R.id.gems_84_view)
    GemPurchaseOptionsView gems84View;

    @Inject
    CrashlyticsProxy crashlyticsProxy;

    private HashMap<String, String> priceMap;

    private static final int GEMS_TO_ADD = 21;
    Button btnPurchaseGems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        priceMap = new HashMap<>();

        return inflater.inflate(R.layout.fragment_gem_purchase, container, false);
    }

    @Override
    public void injectFragment(AppComponent component) {
        component.inject(this);
    }

    private void updateButtonLabel(String sku, String price) {
        GemPurchaseOptionsView matchingView;
        if (sku.equals(PurchaseTypes.Purchase4Gems)) {
            matchingView = gems4View;
        } else if (sku.equals(PurchaseTypes.Purchase21Gems)) {
            matchingView = gems21View;
        } else if (sku.equals(PurchaseTypes.Purchase42Gems)) {
            matchingView = gems42View;
        } else if (sku.equals(PurchaseTypes.Purchase84Gems)) {
            matchingView = gems84View;
        } else {
            return;
        }
        matchingView.setPurchaseButtonText(price);
        matchingView.setSku(sku);
    }

}
