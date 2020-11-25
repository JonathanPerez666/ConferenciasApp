package com.platzi.conferenciasapp.view.adapter

import com.platzi.conferenciasapp.model.Conference

interface ScheduleListener {
    fun onConferenceClicked( conference: Conference, position: Int )

}