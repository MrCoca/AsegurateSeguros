package inacaptemuco.cl.asegurateseguros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ResultadoActivity extends AppCompatActivity {

    TextView ufactual;
    TextView marca;
    TextView modelo;
    TextView ano2;
    TextView patente;
    TextView valor2;
    ImageView resultadoImagen;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Asociamos las variables de objeto con el elemento de la interfaz

        patente = (TextView) findViewById(R.id.txtv_patente);
        marca = (TextView) findViewById(R.id.txtv_marca);
        modelo = (TextView) findViewById(R.id.txtv_modelo);
        ufactual = (TextView) findViewById(R.id.txtv_ufActual);
        ano2 = (TextView) findViewById(R.id.txtv_ano);
        valor2 = (TextView) findViewById(R.id.txtv_valor);
        resultadoImagen = (ImageView) findViewById(R.id.imv_resultado);
        resultado = (TextView) findViewById(R.id.txtv_resultado);
        //Capturamos los datos desde el activity anterior:

        Intent intento = getIntent();
        //Obtenemos los datos enviados desde el activity anteroir:

        Bundle datos_recibidos = intento.getExtras();
        String patenteRecibida = datos_recibidos.getString("p_patente_ingresada");
        String marcaRecibida = datos_recibidos.getString("p_marca_ingresada");
        String modeloRecibido = datos_recibidos.getString("p_modelo_ingresado");
        int ufActual = datos_recibidos.getInt("p_uf_ingresado");
        int ano = datos_recibidos.getInt("p_ano_ingresado");
        int valor = datos_recibidos.getInt("p_valor_seguro");

        //Utilizamos el dato para desplegar el textView:
        // resultado.setText("Marca"+marcaRecibida+"/n"+"Patente"+patenteRecibida+"/n"+"Modelo"+modeloRecibido+"/n"+"UF Actual"+ufActual+"/n"+"Año"+ano+"/n"+"Valor a Pagar"+valor);
        //determinarAntiguedad(ano);
        patente.setText("Patente: " + patenteRecibida);
        marca.setText("Marca: " + marcaRecibida);
        modelo.setText("Modelo: " + modeloRecibido);
        ano2.setText("Año: " + ano);
        ufactual.setText("UF Actual: " + ufActual);
        valor2.setText("Valor a pagar: " + valor);

        determinarAntiguedad(ano);
    }

    public void determinarAntiguedad(int ano) {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int x = year - ano;
        if (x <= 10) {
            resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.asegurable));
            Toast.makeText(getApplicationContext(), "Es Asegurable", Toast.LENGTH_SHORT).show();

        } else {
            resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.noasegurable));
            Toast.makeText(getApplicationContext(), "No es asegurable", Toast.LENGTH_SHORT).show();
        }
    }
}
