package inacaptemuco.cl.asegurateseguros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //Creamos objetos relacionados con el layout
    EditText patente;
    EditText ano;
    EditText uf;
    Button consultar;
    ImageView resultadoImagen;
    //EditText marca;
    EditText modelo;
    Spinner marca;
    String marcaseleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asociamos los objetos con un elemento de la interfaz

        patente = (EditText) findViewById(R.id.edt_patente);
        ano = (EditText) findViewById(R.id.edt_ano);
        uf = (EditText) findViewById(R.id.edt_uf);
        //marca = (EditText) findViewById(R.id.edt_marca);
        modelo = (EditText) findViewById(R.id.edt_modelo);
        resultadoImagen = (ImageView) findViewById(R.id.imv_resultado);
        consultar = (Button) findViewById(R.id.btn_calcular);
        marca = (Spinner) (findViewById(R.id.spn_marca));
        //Adaptador para spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        marca.setAdapter(adapter);
        //Habilitamos la posibilidad de que el boton ejecute operaciones en metodo onClick
        consultar.setOnClickListener(this);

        //Habilitamos la posiblilidad de que el Spinner ejecute el metodo onItemSelected
        marca.setOnItemSelectedListener(this);
    }

    public void onClick(View view) {
        //Validacion del año

        if(!this.checkAnoVehiculo(Integer.parseInt(ano.getText().toString()))){
            Toast.makeText(this,"Año invalido",Toast.LENGTH_LONG).show();
            return;
        }
        //Ayuda a que no se cayga el programa
        if(patente.getText().toString().equals(null) || patente.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese Patente",Toast.LENGTH_LONG).show();
            return;
        }
        if(modelo.getText().toString().equals(null) || modelo.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese Modelo",Toast.LENGTH_LONG).show();
            return;
        }
        if(uf.getText().toString().equals(null) || uf.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese UF",Toast.LENGTH_LONG).show();
            return;
        }
        if(ano.getText().toString().equals(null) || ano.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Ingrese año ",Toast.LENGTH_LONG).show();
            return;
        }

        //Capturamos los valores ingresados:
        //String marcaIngresada = (marca.getText().toString());
        String patenteIngresada =(patente.getText().toString());
        String modeloIngresado =(modelo.getText().toString());
        int anoIngresado = Integer.parseInt(ano.getText().toString());
        int ufActual = Integer.parseInt(uf.getText().toString());
        int valor = determinarvalor(ufActual, anoIngresado);


        Intent intento = new Intent(MainActivity.this, ResultadoActivity.class);
        //Establecemos los datos que queremos enviar al destino (ResultadoActivity)
        intento.putExtra("p_patente_ingresada", patenteIngresada);
        intento.putExtra("p_marca_ingresada", marcaseleccionada);
        intento.putExtra("p_modelo_ingresado", modeloIngresado);
        intento.putExtra("p_ano_ingresado", anoIngresado);
        intento.putExtra("p_uf_ingresado", ufActual);
        intento.putExtra("p_valor_seguro",valor);
        //Para iniciar un activity
        startActivity(intento);
    }

    //Funcion o metodo para determinar el precio apagar

    public int determinarvalor(int uf, int ano) {
        int valor;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int valorTotal;
         int x = year - ano;

        if (x <= 10) {
            if (x == 0 || x == 1) {
                valor = (int) (uf * 0.1);
                valorTotal = 1 * valor;

            } else {
                valor = (int) (uf * 0.1);
                valorTotal = x * valor;
            }


        } else {
            valorTotal = 0;
        }
        return valorTotal;
    }

    private boolean checkAnoVehiculo(int anio) {
        int actualyear = Calendar.getInstance().get(Calendar.YEAR);
        return anio <= actualyear;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //resultado.setText("Seleccionado "+ adapterView.getItemAtPosition(i));
        marcaseleccionada = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}














