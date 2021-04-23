package ge.bgugulashvili.rockpaperscissors

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private enum class Move(val moveIconId: Int, val beatsMoveOrdinal: Int) {
        ROCK(R.drawable.rock, 2),
        PAPER(R.drawable.paper, 0),
        SCISSORS(R.drawable.scissors, 1)
    }

    private enum class Player {
        COMPUTER,
        PLAYER
    }

    private lateinit var computerPointsText: TextView

    private lateinit var playerPointsText: TextView

    private lateinit var rockButton: Button

    private lateinit var paperButton: Button

    private lateinit var scissorsButton: Button

    private lateinit var computerMoveIcon: ImageView

    private lateinit var playerMoveIcon: ImageView

    private lateinit var movesGroup: Group

    private lateinit var pressAnyButtonView: TextView

    private var initialPlay: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        computerPointsText = findViewById(R.id.computerPointsText)
        playerPointsText = findViewById(R.id.playerPointsText)
        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorsButton)
        computerMoveIcon = findViewById(R.id.computerMoveIcon)
        playerMoveIcon = findViewById(R.id.playerMoveIcon)
        movesGroup = findViewById(R.id.movesGroup)
        pressAnyButtonView = findViewById(R.id.pressAnyButtonView)

        rockButton.setOnClickListener {
            playMove(Move.ROCK)
        }
        paperButton.setOnClickListener {
            playMove(Move.PAPER)
        }
        scissorsButton.setOnClickListener {
            playMove(Move.SCISSORS)
        }
    }

    private fun playMove(playerMove: Move) {
        showMoveIcons()
        playerMoveIcon.setImageResource(playerMove.moveIconId)
        val computerMove: Move = Move.values()[Random.nextInt(0, 3)]
        computerMoveIcon.setImageResource(computerMove.moveIconId)

        val winner = getWinner(computerMove, playerMove)
        when (winner) {
            Player.COMPUTER -> {
                computerPointsText.text =
                    (computerPointsText.text.toString().toInt() + 1).toString()
                colorPlayers(R.color.winnerColor, R.color.black)
            }
            Player.PLAYER -> {
                playerPointsText.text =
                    (playerPointsText.text.toString().toInt() + 1).toString()
                colorPlayers(R.color.black, R.color.winnerColor)
            }
            else -> colorPlayers(R.color.drawColor, R.color.drawColor)
        }
    }

    private fun getWinner(computerMove: Move, playerMove: Move): Player? {
        if (Move.values()[computerMove.beatsMoveOrdinal] == playerMove) return Player.COMPUTER
        if (Move.values()[playerMove.beatsMoveOrdinal] == computerMove) return Player.PLAYER
        return null
    }

    private fun colorPlayers(computerColorId: Int, playerColorId: Int) {
        computerPointsText.setTextColor(ContextCompat.getColor(applicationContext, computerColorId))
        playerPointsText.setTextColor(ContextCompat.getColor(applicationContext, playerColorId))
    }

    private fun showMoveIcons() {
        if (initialPlay) {
            pressAnyButtonView.visibility = View.GONE
            movesGroup.visibility = View.VISIBLE
        }
        initialPlay = false
    }

}