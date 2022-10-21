package com.merttoptas.retrofittutorial.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.merttoptas.retrofittutorial.databinding.FragmentFavoritesBinding
import com.merttoptas.retrofittutorial.ui.favorites.adapter.FavoritesAdapter
import com.merttoptas.retrofittutorial.ui.favorites.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {

            it?.let { safeData ->
                binding.rvFavoritesList.adapter = FavoritesAdapter().apply {
                    submitList(safeData)
                }
            } ?: run {
                Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
            }

        }

    }

}