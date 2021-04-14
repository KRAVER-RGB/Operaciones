package com.example.operaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.operaciones.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Conversiones{


    private ActivityMainBinding lienzo;
    private String ultimoIdioma;
    private double val1,val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityMainBinding.inflate(getLayoutInflater());
        View view  = lienzo.getRoot();
        setContentView(view);

        setIdioma();

        lienzo.radioButton.setOnClickListener(listenerSuma());
        lienzo.radioButton2.setOnClickListener(listenerResta());
        lienzo.radioButton3.setOnClickListener(listenerMultiplicacion());
        lienzo.radioButton4.setOnClickListener(listenerDivision());

        lienzo.button.setOnClickListener(listenerBotonSalir());
        
    }


    public void setIdioma(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Por favor selecciona un idioma para la calculadora\n\nPlease select a language for the calculator")
                .setCancelable(false)
                .setPositiveButton("Español", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ultimoIdioma = "Español";
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("English", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setEnglish();
                dialogInterface.cancel();
            }
        }).setTitle("Idioma\nLanguage");

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void setEnglish(){
        
        ultimoIdioma = "English";
        lienzo.textViewNombreDesarrollador.setText("Developer:\nCesar Antonio Alvarado Crespo");
        lienzo.textView2.setText("Enter the values to operate");
        lienzo.textView3.setText("Enter the first value");
        lienzo.textView4.setText("Enter the second value");
        lienzo.radioButton.setText("Add");
        lienzo.radioButton2.setText("Subtract");
        lienzo.radioButton3.setText("Multiply");
        lienzo.radioButton4.setText("Divide");

        lienzo.button.setText("Close");
    }

    public boolean faltaDato(){

        if(lienzo.editTextNumberSigned.getText().toString().isEmpty()||lienzo.editTextNumberSigned2.getText().toString().isEmpty()) {
            
            if(lienzo.editTextNumberSigned.getText().toString().isEmpty()){
                switch(ultimoIdioma){
                    case "Español":
                        Toast.makeText(this, "Por favor ingresa el primer número a operar", Toast.LENGTH_SHORT).show();
                        break;
                        
                    case "English":
                        Toast.makeText(this, "Please enter the first number to operate", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(this, "Error, idioma no válido", Toast.LENGTH_SHORT).show();
                        break;
                }
            }else if(lienzo.editTextNumberSigned2.getText().toString().isEmpty()){
                switch(ultimoIdioma){
                    case "Español":
                        Toast.makeText(this, "Por favor ingresa el segundo número a operar", Toast.LENGTH_SHORT).show();
                        break;

                    case "English":
                        Toast.makeText(this, "Please enter the second number to operate", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(this, "Error, idioma no válido", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            
            return true;
        }else
            return false;

    }
    
    public View.OnClickListener listenerSuma(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    desmarcarRadioButtons();
                }else{
                    val1 = convertirStringADouble(lienzo.editTextNumberSigned.getText().toString());
                    val2 = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                    sumar(val1,val2);
                }
            }
        };
    }

    public View.OnClickListener listenerResta(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(faltaDato()){

                    desmarcarRadioButtons();

                }else{

                    val1 = convertirStringADouble(lienzo.editTextNumberSigned.getText().toString());
                    val2 = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                    restar(val1,val2);

                }

            }
        };
    }

    public View.OnClickListener listenerMultiplicacion(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(faltaDato()){

                    desmarcarRadioButtons();

                }else{

                    val1 = convertirStringADouble(lienzo.editTextNumberSigned.getText().toString());
                    val2 = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                    multiplicar(val1,val2);

                }

            }
        };
    }

    public View.OnClickListener listenerDivision(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(faltaDato()){

                    desmarcarRadioButtons();

                }else{

                    val1 = convertirStringADouble(lienzo.editTextNumberSigned.getText().toString());
                    val2 = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                    dividir(val1,val2);

                }

            }
        };
    }

    public View.OnClickListener listenerBotonSalir(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (ultimoIdioma){
                    case "Español":
                        desplegarAvisoSalirEspañol();
                        break;
                    case "English":
                        desplegarAvisoSalirIngles();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Idioma no valido", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    public void sumar(double val1,double val2){

        double resultado = val1 + val2;
        lienzo.cardViewResultado.setVisibility(View.VISIBLE);
        lienzo.textView5.setText(val1 + "+" + val2 + "=" + resultado);

    }

    public void restar(double val1, double val2){

        double resultado = val1 - val2;
        lienzo.cardViewResultado.setVisibility(View.VISIBLE);
        lienzo.textView5.setText(val1 + "-" + val2 + "=" + resultado);

    }

    public void multiplicar(double val1, double val2){

        double resultado = val1 * val2;
        lienzo.cardViewResultado.setVisibility(View.VISIBLE);
        lienzo.textView5.setText(val1 + "*" + val2 + "=" + resultado);

    }

    public void dividir(double val1, double val2){

        if(val2 == 0){

            lienzo.cardViewResultado.setVisibility(View.GONE);

            switch (ultimoIdioma){
                case "Español":
                    Toast.makeText(this, "Imposible dividir un número entre cero", Toast.LENGTH_SHORT).show();
                    break;

                case "English":
                    Toast.makeText(this, "Unable to divide a number by zero", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(this, "Idioma invalido", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else{
            double resultado = val1 / val2;
            lienzo.cardViewResultado.setVisibility(View.VISIBLE);
            lienzo.textView5.setText(val1 + "/" + val2 + "=" + resultado);
        }

    }


    public void desplegarAvisoSalirEspañol(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Está seguro que desa finalizar el programa?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void desplegarAvisoSalirIngles(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to end the program? ")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void desmarcarRadioButtons(){
        lienzo.radioButton.setChecked(false);
        lienzo.radioButton2.setChecked(false);
        lienzo.radioButton3.setChecked(false);
        lienzo.radioButton4.setChecked(false);
    }

}