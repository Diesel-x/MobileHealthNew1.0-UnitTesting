package com.novikov.mobilehealth.domain.interfaces;

import com.novikov.mobilehealth.domain.models.MedicineModel;

public interface IOnMedicineRVItemClick {
    public void onClick(MedicineModel model, int position);
}
