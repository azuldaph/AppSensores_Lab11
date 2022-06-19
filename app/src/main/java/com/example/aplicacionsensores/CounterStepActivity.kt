package com.example.aplicacionsensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionsensores.model.Paso
import com.example.aplicacionsensores.vistas.step.StepViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_counter_step.*
import kotlinx.android.synthetic.main.item_step.*
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CounterStepActivity : AppCompatActivity(), SensorEventListener{

    private lateinit var stepViewModel: StepViewModel

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_step)

        val actionBar = supportActionBar
        actionBar?.hide()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.v("FIREBASE_MESSAGING", "Fetching FCM registration token failed", task.exception)
            }

            // Get new FCM registration token
            val token = task.result
            Log.v("FIREBASE_MESSAGING", "New Token: $token")
        })

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        //STEP_DETECTOR detecta cada paso, cada vez q da un paso te devuelve uno. Solo devuelve un paso
        //STEP_COUTER te devuleve la cantidad total de pasos del usuario, cada cierto tiempo te dice cuantos pasos diste de golpe
        if(sensor != null){
            Log.v("VERIFICANADO SENSOR", "Sensor existe")
        }else{
            Log.v("VERIFICANADO SENSOR", "Sensor no existe")
        }

        floatingStart.setOnClickListener{
            counter =  0
            textContador.text = counter.toString()
            floatingStart.visibility = View.GONE
            floatingEnd.visibility = View.VISIBLE
            sensor?.also {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
            }
        }

        floatingEnd.setOnClickListener{
            floatingEnd.visibility = View.GONE
            floatingStart.visibility = View.VISIBLE

            // Registrar en la base de datos
            val contador = textContador.text.toString()
            val tipo = "Caminador"
            val datePaso = LocalDateTime.now().format(DateTimeFormatter.ofPattern("E-EEEE-LLLL"))

            sensorManager.unregisterListener(this)

            var step = Paso(datePaso, tipo, contador)
            stepViewModel.saveStep(step)

//            Log.v("PASO_REGISTRADA", contador)
//            Log.v("PASO_REGISTRADA", tipo)
//            Log.v("PASO_REGISTRADA", datePaso)

//            sensorManager.unregisterListener(this)
        }

        obtenerTodosLosSensores()

    }

    // Informar cuando se tiene un valor nuevo
    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val paso = sensorEvent.values[0]
        counter += paso.toInt()
        textContador.text = counter.toString()
    }

    // La exactitud de cambio de un sensors
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    fun obtenerTodosLosSensores() {

        val devicesSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Log.v("VERIFICANDO_SENSOR", devicesSensors.toString())

        val sensorText = StringBuilder()

        for (sensor in devicesSensors) {
            sensorText.append(sensor.name).append(
                System.getProperty("line.separator")
            )
        }

        //textListaSensores.text = sensorText

    }

}