package com.example.practica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText edad;
    TextView resultado;
    Spinner spinner;
    String valor, dato, cadena="";
    RadioGroup grupo;
    RadioButton op1, op2;
    CheckBox op4, op5, op6;
    Button boton_send, boton_clear;
    int seleccion=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner2);
        resultado=findViewById(R.id.resultado);
        nombre=findViewById(R.id.nombre);
        edad=findViewById(R.id.edad);
        grupo=findViewById(R.id.grupo);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op4=findViewById(R.id.cb1);
        op5=findViewById(R.id.cb2);
        op6=findViewById(R.id.cb3);
        boton_send=findViewById(R.id.enviar);
        boton_clear=findViewById(R.id.limpiar);


        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                seleccion =grupo.getCheckedRadioButtonId();

                switch (seleccion){
                    case R.id.op1: {
                        dato=op1.getText().toString();
                        break;
                    }
                    case R.id.op2: {
                        dato=op2.getText().toString();
                        break;
                    }
                }
            }
        });



        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.valores,
                android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valor=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        op4.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    cadena=cadena+(" "+op4.getText() + " ");
                }
            }
        });
        op5.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    cadena=cadena+(" "+op5.getText()+" ");
                }
            }
        });
        op6.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    cadena=cadena+(" "+op6.getText()+" ");
                }
            }
        });

        boton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultado.setText(nombre.getText()+" con edad "+edad.getText()+" y tu sexo es ");
                resultado.append(dato);
                resultado.append("\n");
                resultado.append("\n");
                valor=spinner.getSelectedItem().toString();
                resultado.append("Estudios: "+valor);
                resultado.append("\n");
                resultado.append("\n");
                resultado.append("Hobbies: ");
                resultado.append("\n");
                resultado.append(cadena);
                resultado.setTextColor(Color.WHITE);
                resultado.setTextSize(20);

                InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(nombre.getWindowToken(),0);//PARA QUE EL TECLADO SE ESCONDA

            }
        });

        boton_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText("");
                nombre.setText("");
                edad.setText("");
                op1.setChecked(false);
                op2.setChecked(false);
                op4.setChecked(false);
                op5.setChecked(false);
                op6.setChecked(false);
                spinner.setSelection(0);
                cadena="";
            }
        });

    }

}