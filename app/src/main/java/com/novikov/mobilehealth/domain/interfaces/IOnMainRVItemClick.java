package com.novikov.mobilehealth.domain.interfaces;

import com.novikov.mobilehealth.domain.models.MainRVItemModel;

public interface IOnMainRVItemClick {
    public void onClick(MainRVItemModel item, int position);
}
