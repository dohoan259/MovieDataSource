package com.example.moviedatasource.ui.moviedetail

import android.os.Handler
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.RequestManager
import com.example.moviedatasource.ui.UIState

class DetailEpoxyController(
    private val glide: RequestManager,
    epoxyHandler: Handler
) : EpoxyController<UIState.De>(epoxyHandler, epoxyHandler){
}