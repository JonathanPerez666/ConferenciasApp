package com.platzi.conferenciasapp.view.adapter

import android.telecom.Conference
import java.text.FieldPosition

interface SpeakersListener {
    fun onConferenceClicked( speaker: SpeakersAdapter, position: Int )
}