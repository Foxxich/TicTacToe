package com.example.kolko

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kolko.databinding.ActivityMain5x5Binding

class Main5x5Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMain5x5Binding
    private val buttons: Array<Array<Button?>> = Array<Array<Button?>>(5) { arrayOfNulls<Button>(5) }
    private var playerOTurn = true
    private var roundCount = 0
    private val textViewPlayerO: TextView? = null
    private val textViewPlayerX: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_5x5)
        val message = intent.getStringExtra("startMessage")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding = ActivityMain5x5Binding.inflate(layoutInflater)

        for (i in 0..4) {
            for (j in 0..4) {
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
        } else if (roundCount == 25) {

        } else {
            playerOTurn = !playerOTurn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(5) { arrayOfNulls<String>(5) }
        for (i in 0..4) {
            for (j in 0..4) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..4) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] == field[i][3] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..4) {
            if (field[i][1] == field[i][2] && field[i][1] == field[i][3] && field[i][1] == field[i][4] && field[i][1] != "") {
                return true
            }
        }
        for (i in 0..4) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == field[3][i] && field[0][i] != "") {
                return true
            }
        }
        for (i in 0..4) {
            if (field[1][i] == field[2][i] && field[1][i] == field[3][i] && field[1][i] == field[4][i] && field[1][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] == field[3][3] && field[0][0] == field[4][4] && field[0][0] != "") {
            return true
        }
        if (field[0][4] == field[1][3] && field[0][4] == field[2][2] && field[0][4] == field[3][1] && field[0][4] == field[4][0] && field[0][4] != "") {
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