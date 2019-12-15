package example.com

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import org.jetbrains.anko.longToast


class Main2Activity : AppCompatActivity() {
    val MY_PERMISSIONS_REQUEST_CAMERA = null
    val REQUEST_IMAGE_CAPTURE = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)


        Camara.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                longToast("No tienes permisos de acceso a Cámara")


                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.CAMERA
                    )

                ) {

                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        1
                    )
                }
            } else {
                longToast("Tienes permiso para usar la camara")
                dispatchTakePictureIntent()
            }


        }
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray
        ) {

            when (requestCode) {
                MY_PERMISSIONS_REQUEST_CAMERA -> {
                    // If request is cancelled, the result arrays are empty.
                    if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        dispatchTakePictureIntent()

                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                    }
                    return
                }

                // Add other 'when' lines to check for other
                // permissions this app might request.
                else -> {
                    // Ignore all other requests.
                }
            }
        }


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Prueba Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val ama = intent.getStringExtra("et1")
        lbl1.text = ama
        lbl1.setBackgroundResource(R.color.coloret1)
        val roj = intent.getStringExtra("et2")
        lbl2.text = roj
        lbl2.setBackgroundResource(R.color.coloret2)

        val num1 = intent.getIntExtra("num1",0)
        val num2 = intent.getIntExtra("num2",0)
        val resul = num1 + num2
        num1t.text = num1.toString()
        num2t.text = num2.toString()
        resultado.text = resul.toString()

        resultadoSuma.setOnClickListener {
            val resultInt = Intent()
            resultInt.putExtra("result", resul)
            setResult(Activity.RESULT_OK, resultInt)
            finish()
        }
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                foto.setImageBitmap(imageBitmap)
            }

        }
    }


