package ibm.damlinux.penjadwalanpribadi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menginisialisasi dan memanggil widget button pada file layout
        Button btn1 = (Button)findViewById(R.id.jadwal_tambah);
        Button btn3 = (Button)findViewById(R.id.keluar);
        Button btn4 = (Button)findViewById(R.id.tentang_app);
        Button btn6 = (Button)findViewById(R.id.jadwal_lihat);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this,TambahJadwal.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindahdua = new Intent(MainActivity.this, TentangAplikasi.class);
                startActivity(pindahdua);
                //menghubungkan antar activity dengan intent
            }
        });

        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent pindahempat = new Intent(MainActivity.this, DaftarTahun.class);
                startActivity(pindahempat);
            }
        });

    }
}