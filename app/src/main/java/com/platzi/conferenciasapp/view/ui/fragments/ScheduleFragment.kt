package com.platzi.conferenciasapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.platzi.conferenciasapp.R
import com.platzi.conferenciasapp.model.Conference
import com.platzi.conferenciasapp.view.adapter.ScheduleAdapter
import com.platzi.conferenciasapp.view.adapter.ScheduleListener
import com.platzi.conferenciasapp.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment(), ScheduleListener{
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var  viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View?
      {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        rwSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean>{
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }
}