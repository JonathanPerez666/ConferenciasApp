package com.platzi.conferenciasapp.view.adapter

import com.platzi.conferenciasapp.model.Speaker

interface SpeakersListener {
    fun onSpeakersClicked( speaker: Speaker, position: Int )
}