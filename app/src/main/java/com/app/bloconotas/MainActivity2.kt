package com.app.bloconotas

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.app.bloconotas.data.converteMillisSegundo
import com.app.bloconotas.data.formatDate
import com.app.bloconotas.databinding.ActivityMain2Binding
import com.app.bloconotas.interfac.NotaDao
import com.app.bloconotas.interfac.NotaDatabase
import com.app.bloconotas.models.Notass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var db: NotaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.date.setText(dateVal())
        val focusChangeListener = observarCampos()

        binding.titulo.onFocusChangeListener = focusChangeListener
        binding.txtJaEscrito.onFocusChangeListener = focusChangeListener

        db = Room.databaseBuilder(
            this,
            NotaDatabase::class.java,
            "audioRecords"
        ).build()

        binding.confirmSalvar.setOnClickListener {
            verifiqueCamposBranco()
            showCustonDialog()
        }
    }

    fun dateVal(): String{
        val millis: Long = Date().time

        val date = converteMillisSegundo(millis)
        val  ftdate = formatDate(date)

        return ftdate
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun locate(){
        val titulo = binding.titulo.text.toString()
        val conteudo = binding.txtJaEscrito.text.toString()
        val data = Date().time.toString()

        val nota = Notass(
            title = titulo,
            texto = conteudo,
            data = data )

        lifecycleScope.launch {
            db.notaDao().insert(nota)
            runOnUiThread {
                Toast.makeText(this@MainActivity2,
                    "Nota salva com sucesso",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifiqueCamposBranco(): Boolean {
        var very = false
        if (binding.titulo.text.isBlank()
            || binding.txtJaEscrito.text.isBlank()){
            Toast.makeText(this, "Por favor escreva um texto"
                , Toast.LENGTH_SHORT).show()
             very = false
        }else{
            locate()
            very = true
        }
        return very
    }

    private fun observarCampos(): View.OnFocusChangeListener {
        val focusChangeListen = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.confirmSalvar.visibility = View.VISIBLE
            }else{
                binding.confirmSalvar.visibility = View.GONE
            }
        }
        return focusChangeListen
    }

    private fun showCustonDialog(){
        val dialog = View.inflate(this, R.layout.dialog, null)
        val buider = AlertDialog.Builder(this)
        buider.setView(dialog)
        val dialogs = buider.create()
         val btnSim: Button = dialog.findViewById(R.id.btn_sim)
         val btnNao: Button = dialog.findViewById(R.id.btn_nao)
        btnSim.setOnClickListener {
            locate()
            dialogs.dismiss()
        }

        btnNao.setOnClickListener {
            dialogs.dismiss()
        }
        dialogs.show()
    }
}