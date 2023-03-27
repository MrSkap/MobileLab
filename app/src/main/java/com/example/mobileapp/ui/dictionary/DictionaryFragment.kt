package com.example.mobileapp.ui.dictionary

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobileapp.DictionaryApi
import com.example.mobileapp.databinding.FragmentDictionaryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dictionaryViewModel =
            ViewModelProvider(this).get(DictionaryViewModel::class.java)

        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        set on click search button findWordMeaning with editText text
        return root
    }

    fun findWordMeaning(word: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DictionaryApi::class.java)

        lifecycleScope.launch(Dispatchers.IO){
            var result = service.getWord(word)

            withContext(Dispatchers.Main){
                //show in activity
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}