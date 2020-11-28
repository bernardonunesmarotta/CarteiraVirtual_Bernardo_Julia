package br.com.carteiravirtual_bernardonunes_juliafernades;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;
import ferramentas.EventosDB;

public class MainActivity extends AppCompatActivity {



        private TextView titulo;
        private TextView entrada;
        private TextView saida;
        private TextView saldo;
        private ImageButton entraBtn;
        private ImageButton saidaBtn;
        private Button proxBtn;
        private Button anteriorBtn;
        private Button novoBtn;
        private Calendar hoje;
        private Calendar dataApp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criando o link entre os componentes de java e xml
        titulo = (TextView) findViewById(R.id.tituloMain);
        entrada = (TextView) findViewById(R.id.entradaTxt);
        saida = (TextView) findViewById(R.id.saidaTxt);
        saldo = (TextView) findViewById(R.id.saldoTxt);

        entraBtn =(ImageButton) findViewById(R.id.entradaButton);
        saidaBtn = (ImageButton) findViewById(R.id.saidaButton);

        proxBtn = (Button) findViewById(R.id.proximoButton);
        anteriorBtn = (Button) findViewById(R.id.anteriorButton);
        novoBtn = (Button) findViewById(R.id.novoButton);

        //resposavel por todas as açoes dos botões
        cadastroEventos();

        //recupero data e hora atual
        hoje = Calendar.getInstance();
        dataApp = Calendar.getInstance();

        mostraDataApp();
    }

        private void cadastroEventos(){
        anteriorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaMes(-1);
            }
        });

        proxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaMes(+1);
            }
        });

        novoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EventosDB db = new EventosDB(MainActivity.this);
                db.insereEvento();
            }
        });
    }

        private void mostraDataApp(){
        String nomeMes[] = {"Janeiro","Feveireiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
                "Setembro", "Outubro", "Novembro", "Dezembro"};

        int mes = dataApp.get(Calendar.MONTH);
        int ano = dataApp.get(Calendar.YEAR);

        titulo.setText(nomeMes[mes] + "/" + ano);
    }

        private void atualizaMes(int ajuste){
        dataApp.add(Calendar.MONTH, ajuste);
        //proximo mes não pode passar do mes atual
        if(ajuste > 0){
            if(dataApp.after(hoje)) {
                dataApp.add(Calendar.MONTH,-1);
            }
        }else{
            // precisa-se de um banco de dados para avaliar essa situação
        }
        //realizar uma busca no banco de dados para avaliar se existe meses anteriores cadastrados
        mostraDataApp();
    }

    }
