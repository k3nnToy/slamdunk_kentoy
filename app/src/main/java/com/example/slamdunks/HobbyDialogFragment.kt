package com.example.slamdunks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.slamdunks.databinding.FragmentHobbyDialogBinding

class HobbyDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentHobbyDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("HobbyDialogFragment", "onCreateView called")
        binding = FragmentHobbyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("HobbyDialogFragment", "onStart called")
        dialog?.window?.let { window ->
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.setLayout(width, height)
        }

        binding.Done.setOnClickListener{
            dismiss()
        }
    }

    companion object {
        fun newInstance(): HobbyDialogFragment {
            return HobbyDialogFragment()
        }
    }
}