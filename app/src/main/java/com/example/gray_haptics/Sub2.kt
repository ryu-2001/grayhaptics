package com.example.gray_haptics

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

//右に※が付いているデータはgrid_5~grid_600(詳しくはassetsまたはres>drawable参照)

class Sub2 : Activity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub2)
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        //画像表示
        val imageView: ImageView = findViewById(R.id.imageButton2)
        imageView.setImageResource(R.drawable.grid_200)//※

        //returnボタンで前画面に移動
        val returnButton = findViewById<Button>(R.id.return_main2)
        returnButton.setOnClickListener {
            vibrator.cancel()
            finish()
        }
    }

    //画面タップ時の処理
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        //振動のデータが入ったファイル
        var file2 = File("grid_200.csv")//※

        return when (MotionEventCompat.getActionMasked(event)) {
            //画面を触っている間
            MotionEvent.ACTION_DOWN -> {
                var intarray = arrayListOf<Int>()
                //csvファイルから数値を配列に代入
                try {
                    val file = resources.assets.open(file2.toString())
                    val fileReader = BufferedReader(InputStreamReader(file))
                    fileReader.forEachLine {
                        val line = it.split(",").toTypedArray()
                        intarray.add(line[0].toInt())
                    }
                }catch (e: IOException) {
                    print(e)
                }

                //振幅を決める配列
                var array = arrayListOf<Int>()
                for(i in 0 until intarray.size step 2){
                    var x=(intarray[i]+intarray[i+1])/2 //振動する時間を短縮するため配列の要素2つごとに平均をとった
                    array.add(x)
                }

                //振動させる時間の配列
                var longListArray = arrayListOf<Long>()
                repeat(array.size) {
                    longListArray.add(1) //単位はms
                }

                //振動の様子を決定
                val vibrationEffect = VibrationEffect.createWaveform(
                    longListArray.toLongArray(),
                    array.toIntArray(),
                    0
                )
                vibrator.vibrate(vibrationEffect) //振動開始
                true
            }

            //画面から指を離したとき
            MotionEvent.ACTION_UP -> {
                vibrator.cancel() //振動を止める
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}