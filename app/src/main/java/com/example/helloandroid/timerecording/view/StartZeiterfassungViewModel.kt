package com.example.helloandroid.timerecording.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.ConsumingDatabaseAsyncTask
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import java.lang.ref.WeakReference

class StartZeiterfassungViewModel(appConfigurationRepository: AppConfigurationRepository) : ViewModel() {

    var existsConfiguration: MutableLiveData<Boolean> = MutableLiveData()

    init {
        ConsumingDatabaseAsyncTask(//
            backgroundOperationConsumer = appConfigurationRepository::existsConfiguration,
            dbOperationSuccessfulListener = WeakReference { configured -> existsConfiguration.postValue(configured) }//
        ).execute()
    }


}