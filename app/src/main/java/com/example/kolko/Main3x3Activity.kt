package com.example.kolko

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kolko.databinding.ActivityMain3x3Binding


class Main3x3Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMain3x3Binding
    private val buttons: Array<Array<Button?>> = Array<Array<Button?>>(3) { arrayOfNulls<Button>(3) }
    private var playerOTurn = true
    private var roundCount = 0
    private val textViewPlayerO: TextView? = null
    private val textViewPlayerX: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_3x3)
        val message = intent.getStringExtra("startMessage")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding = ActivityMain3x3Binding.inflate(layoutInflater)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<View>(resID) as Button?
                buttons[i][j]
                        ?.setOnClickListener(this)
            }
        }
    }

    override fun onClick(v: View) {
        if ((v as Button).text.toString() != "") {
            return
        }
        if (playerOTurn) {
            findViewById<TextView>(R.id.text_view_pX).text = "Player X : Your turn"
            findViewById<TextView>(R.id.text_view_pO).text = "Player O : "
            v.text = "O"
        } else {
            findViewById<TextView>(R.id.text_view_pO).text = "Player O : Your turn"
            findViewById<TextView>(R.id.text_view_pX).text = "Player X : "
            v.text = "X"
        }
        roundCount++
        if (checkForWin()) {
            if (playerOTurn) {
                playerOWins()
            } else {
                playerXWins()
            }
        } else if (roundCount == 9) {

        } else {
            playerOTurn = !playerOTurn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            return  true
        }
        return false
    }

    private fun playerOWins() {
       // Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        val intent = Intent()
        intent.putExtra("winMessage","Player O won")
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    private fun playerXWins() {
        //Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        val intent = Intent()
        intent.putExtra("winMessage","Player X won")
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}