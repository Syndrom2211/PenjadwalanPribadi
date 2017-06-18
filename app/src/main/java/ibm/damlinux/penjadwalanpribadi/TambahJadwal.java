package ibm.damlinux.penjadwalanpribadi;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahJadwal extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahjadwal);

        dbHelper = new DataHelper(this);
        Button btnbatal = (Button) findViewById(R.id.batal_jadwal);
        Button btnsubmit = (Button) findViewById(R.id.submit_jadwal);
        Button btnlihat = (Button) findViewById(R.id.lihatjadwal_jadwal);

        final EditText waktu_jadwal = (EditText) findViewById(R.id.waktu_jadwal);
        final EditText hari_jadwal = (EditText) findViewById(R.id.hari_jadwal);
        final EditText bulan_jadwal = (EditText) findViewById(R.id.bulan_jadwal);
        final EditText kegiatan_jadwal = (EditText) findViewById(R.id.kegiatan_jadwal);
        final EditText tahun_jadwal = (EditText) findViewById(R.id.tahun_jadwal);

        btnlihat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(TambahJadwal.this, DaftarTahun.class));
                finish();

                //menggunakan intent untuk berpindah ke activity sebelumnya
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                waktu_jadwal.setText("");
                hari_jadwal.setText("");
                bulan_jadwal.setText("");
                kegiatan_jadwal.setText("");
                tahun_jadwal.setText("");
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(waktu_jadwal, hari_jadwal, bulan_jadwal, kegiatan_jadwal, tahun_jadwal) values('" +
                        waktu_jadwal.getText().toString() +"','" +
                        hari_jadwal.getText().toString()+"','" +
                        bulan_jadwal.getText().toString() +"','" +
                        kegiatan_jadwal.getText().toString() + "','" +
                        tahun_jadwal.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                //DaftarTahun.dt.RefreshLists();
                finish();
            }
        });
    }
}
