package com.merttoptas.retrofittutorial.ui.postdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.databinding.FragmentPostDetailBinding
import com.merttoptas.retrofittutorial.ui.postdetail.viewmodel.PostDetailViewModel
import com.merttoptas.retrofittutorial.ui.posts.adapter.OnPostClickListener
import com.merttoptas.retrofittutorial.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsDetailFragment : Fragment(), OnPostClickListener {

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel by viewModels<PostDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // null state check of data
        arguments?.let {

            val postData = it.getString(Constants.ARGUMENT_POST_DATA)

            postData?.let { safePostData ->

                val post = PostDTO.fromJson(safePostData)
                binding.dataHolder = post
                Log.e("hata2","post ${post.body}")

            }

        }

    }

    override fun onPostClick(post: PostDTO, favorite: Boolean) {
        viewModel.onFavoritePost(post)
    }

}