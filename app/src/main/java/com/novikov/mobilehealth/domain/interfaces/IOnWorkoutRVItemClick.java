package com.novikov.mobilehealth.domain.interfaces;

import com.novikov.mobilehealth.domain.models.WorkoutModel;

public interface IOnWorkoutRVItemClick {
    public void onClick(WorkoutModel model, int position);
}
