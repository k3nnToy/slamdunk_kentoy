package com.example.slamdunks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.slamdunks.databinding.FragmentHobbyDialogBinding

class HobbyDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentHobbyDialogBinding
    private lateinit var slambookEntry: SlambookEntry

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHobbyDialogBinding.inflate(inflater, container, false)

        // Retrieve SlambookEntry object passed from Form2Activity
        slambookEntry = arguments?.getSerializable("slambookEntry") as? SlambookEntry
            ?: return null // Handle case if the object is not passed properly

        // Set default values (if any) from the current SlambookEntry
        setDefaultValues()

        // Done button logic
        binding.Done.setOnClickListener {
            // Update SlambookEntry with values from EditTexts
            updateSlambookEntry()

            // Pass the updated SlambookEntry back to the parent activity
            (activity as? Form2Activity)?.onHobbyDataUpdated(slambookEntry)

            // Show a confirmation message
            Toast.makeText(requireContext(), "Hobbies updated!", Toast.LENGTH_SHORT).show()

            // Close the dialog
            dismiss()
        }

        return binding.root
    }

    // Helper method to set default values in the EditText fields
    private fun setDefaultValues() {
        binding.etFavoriteHobby.setText(slambookEntry.favoriteHobby)
        binding.etBooksOrMovies.setText(slambookEntry.booksOrMovies)
        binding.etSportOrGame.setText(slambookEntry.sportOrGame)
        binding.etDrawingOrPainting.setText(slambookEntry.drawingOrPainting)
        binding.etFunHobby.setText(slambookEntry.funHobby)
    }

    // Helper method to update SlambookEntry from the EditText fields
    private fun updateSlambookEntry() {
        slambookEntry = slambookEntry.copy(
            favoriteHobby = binding.etFavoriteHobby.text.toString(),
            booksOrMovies = binding.etBooksOrMovies.text.toString(),
            sportOrGame = binding.etSportOrGame.text.toString(),
            drawingOrPainting = binding.etDrawingOrPainting.text.toString(),
            funHobby = binding.etFunHobby.text.toString()
        )
    }

    companion object {
        // This method creates a new instance of the dialog, passing the SlambookEntry object
        fun newInstance(slambookEntry: SlambookEntry): HobbyDialogFragment {
            val fragment = HobbyDialogFragment()
            val args = Bundle()
            args.putSerializable("slambookEntry", slambookEntry)
            fragment.arguments = args
            return fragment
        }
    }
}
