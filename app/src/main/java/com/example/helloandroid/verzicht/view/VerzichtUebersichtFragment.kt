package com.example.helloandroid.verzicht.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.databinding.FragmentVerzichtUebersichtBinding
import com.example.helloandroid.verzicht.persistence.Verzicht
import java.lang.ref.WeakReference
import java.util.function.Consumer


class VerzichtUebersichtFragment : ListFragment() {

    private lateinit var viewModel: VerzichtUebersichtViewModel
    private lateinit var sharedVerzichtViewModel: SharedVerzichtViewModel
    lateinit var openEditVerzichtFragmentCallback: EditVerzichtFragmentOpener
    var verzichtDTO = VerzichtDTO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVerzichtUebersichtBinding.inflate(inflater, container, false)

        binding.fragmentVerzichtUebersicht = this

        activity?.let {
            viewModel = ViewModelProviders.of(it, VerzichtViewModelFactory(it.application))
                .get(VerzichtUebersichtViewModel::class.java)
            sharedVerzichtViewModel = ViewModelProviders.of(it).get(SharedVerzichtViewModel::class.java)
            viewModel.initializeByLoadingAllVerzichte()
            viewModel.verzichteLiveData.observe(this, aVerzichtListObserver(it))

        }

        return binding.root
    }


    private fun aVerzichtListObserver(activity: FragmentActivity): Observer<List<Verzicht>> {
        return Observer { verzichte ->
            listAdapter = VerzichtArrayAdapter(activity, //
                verzichte,//
                Consumer { verzicht -> openEditVerzichtFragment(verzicht) },//
                Consumer { t -> viewModel.deleteVerzicht(t) })
        }
    }

    private fun openEditVerzichtFragment(currentVerzicht: Verzicht) {
        sharedVerzichtViewModel.currentVerzicht = currentVerzicht
        openEditVerzichtFragmentCallback.openEditVerzichtFragment()
    }

    interface EditVerzichtFragmentOpener {
        fun openEditVerzichtFragment()
    }


    fun saveVerzichtAndResetInputField(view: View) {
        createSaveVerzichtAsyncTask().execute(verzichtDTO)
        closeSoftKeyboard(view)
    }

    private fun createSaveVerzichtAsyncTask(): DatabaseAsyncTask<VerzichtDTO, Long> {
        val successCallback: (Long) -> Unit = { verzichtDTO.name = "" }
        val errorCallback: (String) -> Unit = { errorMsg ->
            Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
        }
        return DatabaseAsyncTask(//
            backgroundOperationFunc = viewModel::saveVerzicht,//
            dbOperationSuccessfulListener = WeakReference(successCallback),
            dbOperationFailedListener = WeakReference(errorCallback)//
        )
    }


    private fun closeSoftKeyboard(view: View) {
        activity?.let {
            val inputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
