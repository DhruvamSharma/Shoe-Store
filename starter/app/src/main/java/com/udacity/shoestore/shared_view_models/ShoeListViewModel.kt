package com.udacity.shoestore.shared_view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    // shoe list data
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    // on save event for performing action on save click
    private val _onSaveEvent = MutableLiveData<SaveEvent>()
    val onSaveEvent: LiveData<SaveEvent> get() = _onSaveEvent

//    private val _currentShoe = MutableLiveData<Shoe>()
//    val currentShoe: LiveData<Shoe> get() = _currentShoe

    init {
        _shoeList.value = mutableListOf()
    }

    // variables for current shoe
    var shoeName: String? = null
    var shoeSize: String? = null
    var shoeCompany: String? = null
    var shoeDescription: String? = null

    // save a shoe
    fun saveNewShoe() {
        if (shoeName.isNullOrEmpty()) {
            _onSaveEvent.value = SaveEvent.EMPTY_NAME
        } else if (shoeCompany.isNullOrEmpty()) {
            _onSaveEvent.value = SaveEvent.EMPTY_COMPANY_NAME
        } else if (shoeSize.isNullOrEmpty()) {
            _onSaveEvent.value = SaveEvent.EMPTY_SIZE
        } else if (shoeDescription.isNullOrEmpty()) {
            _onSaveEvent.value = SaveEvent.EMPTY_DESCRIPTION
        } else {
            _shoeList.value?.add(
                Shoe(
                    name = shoeName!!,
                    company = shoeCompany!!,
                    size = shoeSize!!,
                    description = shoeDescription!!
                )
            )
            _onSaveEvent.value = SaveEvent.SUCCESS
        }
    }

    // when error is reported, we need to also set state empty
    // so that whenever there is a config change, the observer doesn't
    // send the state twice preventing double actions
    fun onErrorDone() {
        _onSaveEvent.value = SaveEvent.EMPTY
    }

    // when save is completed, we need to also set state empty
    // so that whenever there is a config change, the observer doesn't
    // send the state twice preventing double actions
    fun onSaveReset() {
        shoeName = null
        shoeSize = null
        shoeCompany = null
        shoeDescription = null
        _onSaveEvent.value = SaveEvent.EMPTY
    }
}

enum class SaveEvent {
    EMPTY,
    EMPTY_NAME,
    EMPTY_COMPANY_NAME,
    EMPTY_SIZE,
    EMPTY_DESCRIPTION,
    SUCCESS,
}