package example.com

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    val RESULTADO_SUMA = 23
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Abriendo web", Snackbar.LENGTH_LONG)
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.google.com/")
            startActivity(openURL)

        }
        btnSaudo.setOnClickListener {
            texto.setText("Yeeepa!!!!!!!")
            longToast("Probando Toast :-)")
        }
        btnFondo.setOnClickListener {

            cambiarFondo()
            it.snackbar("Fondo cambiado")


        }
        btnIntent.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("et1", "amarillo")
            intent.putExtra("et2", "rojo")
            startActivity(intent)
        }


        btnSuma.setOnClickListener {
            val intent2 = Intent(this, Main2Activity::class.java)
            intent2.putExtra("num1", Integer.parseInt(num1r.text.toString()))
            intent2.putExtra("num2", Integer.parseInt(num2r.text.toString()))
            startActivityForResult(intent2, RESULTADO_SUMA)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cambiarFondo() {
        probaBoton.setBackgroundColor(Color.GREEN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((num1r.text.toString().equals("")) || (num2r.text.toString().equals(""))) {
            toast("Introduce algún numero")
        } else {
            if (requestCode == RESULTADO_SUMA) {

                if (resultCode == Activity.RESULT_OK) {
                    val result = data?.getIntExtra("result", 0)
                    texto.text = result.toString()


                }
            }
        }
    }
}


