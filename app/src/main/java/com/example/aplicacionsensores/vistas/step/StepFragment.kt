package com.example.aplicacionsensores.vistas.step
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionsensores.CounterStepActivity
import com.example.aplicacionsensores.R
import kotlinx.android.synthetic.main.fragment_step.*


class StepFragment: Fragment() {

    private lateinit var stepViewModel: StepViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_step, container, false)
    }

    companion object{
        fun newInstance(): StepFragment = StepFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stepViewModel = run{
            ViewModelProviders.of(this).get(StepViewModel::class.java)
        }

        fabRegisterStep.setOnClickListener{
            val editProfileIntent = Intent(activity, CounterStepActivity::class.java)
            startActivity(editProfileIntent)
        }

//        fabRegisterStep.setOnClickListener{
//            requireContext().let {
//                it.startActivity(Intent(it, CounterStepActivity::class.java ))
//            }
//        }

        loadData()
    }


    private fun loadData(){
        val adapter = StepAdapter()
        recyclerSteps.adapter = adapter
        recyclerSteps.layoutManager = LinearLayoutManager(activity)

        stepViewModel.steps.observe(viewLifecycleOwner){steps ->
            if(steps.isNotEmpty()){
                steps?.let {
                    adapter.setStep(steps)
                }
            }
        }
    }


//    public fun registerAlertStep(){
//
//        val mDialogView = LayoutInflater.from(context).inflate((R.layout.dialog_step, null)
//
//        val mBuilder = AlertDialog.Builder(context)
//            .setView(mDialogView)
//            .setTitle("Guardar not")
//
//        val mAlertDialog = mBuilder.show()
//
//        mAlertDialog.btnRegisterStep.setOnCliclListener{
//
//            mAlertDialog.dismiss()
//
//            val dateStep = mDialogView.edt
//        }
//    }

}