package com.example.namaztracker.fragment.islamic_info.qibla

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.*

class QiblaManager(
    context: Context,
    private val onRotate: (Float) -> Unit
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val gravity = FloatArray(3)
    private val geomagnetic = FloatArray(3)

    private var hasGravity = false
    private var hasGeomagnetic = false

    private var qiblaAngle = 0f // stays 0 until location updates safely
    private var lastRotation = 0f

    fun start() {
        accel?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        magnet?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    fun updateLocation(lat: Double, lon: Double) {
        try {
            val kaabaLat = Math.toRadians(21.4225)
            val kaabaLon = 39.8262

            val userLatRad = Math.toRadians(lat)
            val deltaLon = Math.toRadians(kaabaLon - lon)

            val y = sin(deltaLon)
            val x =
                cos(userLatRad) * tan(kaabaLat) -
                        sin(userLatRad) * cos(deltaLon)

            qiblaAngle = ((Math.toDegrees(atan2(y, x)) + 360) % 360).toFloat()
        } catch (e: Exception) {
            qiblaAngle = 0f
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                System.arraycopy(event.values, 0, gravity, 0, 3)
                hasGravity = true
            }

            Sensor.TYPE_MAGNETIC_FIELD -> {
                System.arraycopy(event.values, 0, geomagnetic, 0, 3)
                hasGeomagnetic = true
            }
        }

        if (!hasGravity || !hasGeomagnetic) return

        val r = FloatArray(9)
        val i = FloatArray(9)

        val success = SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)

        if (!success) return

        val orientation = FloatArray(3)
        SensorManager.getOrientation(r, orientation)

        val azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()

        val correctedRotation = (qiblaAngle - azimuth + 360) % 360

        // Smooth rotation
        val smoothRotation = lastRotation + (correctedRotation - lastRotation) * 0.1f
        lastRotation = smoothRotation

        onRotate(smoothRotation)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}