package com.example.kolko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.kolko.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        val winMessage = data?.getStringExtra("winMessage")

        checkBox1.isChecked = false
        checkBox2.isChecked = false

        if(requestCode == 33) {
            Toast.makeText(this, "$winMessage 3X3", Toast.LENGTH_SHORT).show()
        } else if (requestCode == 55) {
            Toast.makeText(this, "$winMessage 5X5", Toast.LENGTH_SHORT).show()
        } else {

        }
    }

    fun startActivity(view: View) {
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        if (checkBox1.isChecked && (!checkBox2.isChecked)) {
            val intent = Intent(this, Main3x3Activity::class.java).apply {
                putExtra("startMessage","You play at 3x3")
            }
            startActivityForResult(intent,33)
        } else if (checkBox2.isChecked && (!checkBox1.isChecked)) {
            val intent = Intent(this, Main5x5Activity::class.java).apply {
                putExtra("startMessage","You play at 5x5")
            }
            startActivityForResult(intent,55)
        } else if (!(checkBox1.isChecked) && !(checkBox2.isChecked)){
            Toast.makeText(this, "Choose desk!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Choose only 1 desk!", Toast.LENGTH_SHORT).show()
            checkBox1.isChecked = false
            checkBox2.isChecked = false
        }
    }
}