package com.evofox.bridge

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        statusText = TextView(this).apply {
            text = "EvoFox Bridge Ready!\n\nConnect controller via OTG\nand press any button to test."
            textSize = 20f
            gravity = Gravity.CENTER
        }
        setContentView(statusText)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            statusText.text = "Button Pressed: KeyCode ${event.keyCode}"
            return true
        } else if (event.action == KeyEvent.ACTION_UP) {
            statusText.text = "Button Released: KeyCode ${event.keyCode}"
            return true
        }
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchGenericMotionEvent(event: MotionEvent): Boolean {
        if (event.source and InputDevice.SOURCE_JOYSTICK != 0) {
            val x = event.getAxisValue(MotionEvent.AXIS_X)
            val y = event.getAxisValue(MotionEvent.AXIS_Y)
            statusText.text = "Joystick Moved:\nX: $x\nY: $y"
            return true
        }
        return super.dispatchGenericMotionEvent(event)
    }
}
