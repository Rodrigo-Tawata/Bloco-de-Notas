package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.fileName = getApplicationContext().getFilesDir().getPath().toString() + "/note.txt";


        final EditText contentBox = findViewById(R.id.contentBox);
        Button btClean = findViewById(R.id.btClean);
        Button btSave = findViewById(R.id.btSave);
        Button btRecover = findViewById(R.id.btRecover);

        btClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setText("");
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentBox.getText().toString();
                MainActivity.this.gravaDadosArquivo(fileName,content);

            }
        });

        btRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = recuperaDadosArquivo(fileName);
                contentBox.setText(content);

            }
        });


    }

    public  void gravaDadosArquivo(String fileName,String data){
        try{
            OutputStreamWriter bufferSaida = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            bufferSaida.write(data);
            bufferSaida.close();
        }
        catch(FileNotFoundException e){
            Toast.makeText(this, "Erro ao abrir o arquivo", Toast.LENGTH_SHORT).show();
        }
        catch(UnsupportedEncodingException e){
            Toast.makeText(this, "Erro na codificação", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Erro na escrita", Toast.LENGTH_SHORT).show();

        }
    }

    public String recuperaDadosArquivo(String fileName){
        try{
            BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));

            StringBuilder sb = new StringBuilder();

            String linha = bufferdReader.readLine();

            while(linha != null){
                sb.append(linha);
                sb.append("\n");
                linha = bufferdReader.readLine();
            }
            return sb.toString();

        }
        catch(FileNotFoundException e){
            Toast.makeText(this, "Erro ao abrir o arquivo", Toast.LENGTH_SHORT).show();
        }
        catch(UnsupportedEncodingException e){
            Toast.makeText(this, "Erro na codificação", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Erro na leitura", Toast.LENGTH_SHORT).show();
        }
        return"";
    }
}
