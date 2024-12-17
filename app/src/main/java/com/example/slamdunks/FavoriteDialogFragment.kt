package com.example.slamdunks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.slamdunks.databinding.FragmentFavoriteDialogBinding

class FavoriteDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFavoriteDialogBinding
    private lateinit var slambookEntry: SlambookEntry

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteDialogBinding.inflate(inflater, container, false)

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
            (activity as? Form2Activity)?.onFavoriteDataUpdated(slambookEntry)

            // Show a confirmation message
            Toast.makeText(requireContext(), "Favorites updated!", Toast.LENGTH_SHORT).show()

            // Close the dialog
            dismiss()
        }

        return binding.root
    }

    // Helper method to set default values in the EditText fields
    private fun setDefaultValues() {
        // Set values from slambookEntry, defaulting to empty string if null
        binding.etFavoriteColor.setText(slambookEntry.favoriteColor ?: "")
        binding.etFavoriteFood.setText(slambookEntry.favoriteFood ?: "")
        binding.FavoriteSports.setText(slambookEntry.favoriteSport ?: "")
        binding.etOnlineGames.setText(slambookEntry.favoriteOnlineGames ?: "")
    }

    // Helper method to update SlambookEntry from the EditText fields
    private fun updateSlambookEntry() {
        slambookEntry = slambookEntry.copy(
            favoriteColor = binding.etFavoriteColor.text.toString(),
            favoriteFood = binding.etFavoriteFood.text.toString(),
            favoriteSport = binding.FavoriteSports.text.toString(),
            favoriteOnlineGames = binding.etOnlineGames.text.toString()
        )
    }

    companion object {
        // This method creates a new instance of the dialog, passing the SlambookEntry object
        fun newInstance(slambookEntry: SlambookEntry): FavoriteDialogFragment {
            val fragment = FavoriteDialogFragment()
            val args = Bundle()
            args.putSerializable("slambookEntry", slambookEntry)
            fragment.arguments = args
            return fragment
        }
    }
}
