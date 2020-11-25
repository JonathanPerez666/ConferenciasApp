package com.platzi.conferenciasapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.platzi.conferenciasapp.R
import com.platzi.conferenciasapp.model.Conference
import com.platzi.conferenciasapp.model.Speaker
import com.platzi.conferenciasapp.view.adapter.ScheduleAdapter
import com.platzi.conferenciasapp.view.adapter.SpeakersAdapter
import com.platzi.conferenciasapp.view.adapter.SpeakersListener
import com.platzi.conferenciasapp.viewmodel.ScheduleViewModel
import com.platzi.conferenciasapp.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakersListener {

    private lateinit var speakerAdpater: SpeakersAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdpater = SpeakersAdapter(this)

        rwSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdpater
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.listSpeakers.observe(this, Observer<List<Speaker>> { speakers ->
            speakers.let {
                speakerAdpater.updateData(speakers)
            }
        })

        viewModel.isLoading.observe(this, Observer<Boolean>{
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakersClicked(speaker: Speaker, position: Int) {
    }


}