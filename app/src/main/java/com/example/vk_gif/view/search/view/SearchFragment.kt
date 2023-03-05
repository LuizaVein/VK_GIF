package com.example.vk_gif.view.search.view

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vk_gif.R
import com.example.vk_gif.databinding.SearchFragmentBinding
import com.example.vk_gif.view.search.view.adapter.GiphyModelAdapter
import com.example.vk_gif.view.search.vm.SearchViewModel
import com.google.android.gms.common.internal.Preconditions.checkMainThread
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(R.layout.search_fragment) {

    val viewModel: SearchViewModel by viewModel()
    private lateinit var binding: SearchFragmentBinding
    private var adapter: GiphyModelAdapter? = null

    private lateinit var pagingJob: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {

        }
        initSearch()
        if (adapter == null) {
            startPaging()
        }
    }

    private fun initSearch() {
        binding.search.textChanges()
            .debounce(300)
            .onEach {
                pagingJob.cancel()
                startPaging(query = it.toString().trim())
            }.launchIn(lifecycleScope)
    }

    private fun startPaging(query: String = "") {
        initAdapter()
        pagingJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.createPaging(query = query).collectLatest {
                adapter?.submitData(lifecycle, it)
            }
        }
    }

    private fun initAdapter() {
        adapter = GiphyModelAdapter(requireContext()) {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            requireView().findNavController().navigate(action)
        }

        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val display: Display = wm.defaultDisplay

        val point = Point()
        display.getSize(point)
        val screenWidth: Int = point.x


        val gyphWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100f,
            this.resources.displayMetrics
        ).toInt()

        val columnsCount = screenWidth / gyphWidth

        binding.gyphs.adapter = adapter
        binding.gyphs.layoutManager = GridLayoutManager(requireContext(), columnsCount)
    }
}

fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        checkMainThread()

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}