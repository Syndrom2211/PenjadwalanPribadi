package ibm.damlinux.penjadwalanpribadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by damlinux on 23/08/16.
 */
public class JadwalKegiatan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    public static JadwalKegiatan djk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalkegiatan);

        Button btn8 = (Button) findViewById(R.id.button8);
        Button btn9 = (Button) findViewById(R.id.button9);
        final TextView kotak2 = (TextView) findViewById(R.id.kotak2);
        final TextView kotak3 = (TextView) findViewById(R.id.kotak3);

        kotak2.setTextColor(Color.parseColor("#ffffff"));
        kotak3.setTextColor(Color.parseColor("#ffffff"));

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent pindahlahh = new Intent(JadwalKegiatan.this, DaftarTahun.class);
                startActivity(pindahlahh);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent pindahlahhh = new Intent(JadwalKegiatan.this, MainActivity.class);
                startActivity(pindahlahhh);
            }
        });

        djk = this;
        dbHelper = new DataHelper(this);
        RefreshLists();
    }

    public void RefreshLists() {
        ListView thetampilan = (ListView) findViewById(R.id.tampilan_nya);

        final TextView data1 = (TextView) findViewById(R.id.data1);
        final TextView data2 = (TextView) findViewById(R.id.data2);
        final TextView data3 = (TextView) findViewById(R.id.data3);

        String[] darimana = new String[]{"kol1", "kol2"};
        int[] kemana = new int[]{R.id.utk_kolom1, R.id.utk_kolom2};
        final List<HashMap<String, String>> melengkapiData = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE hari_jadwal = '" + getIntent().getStringExtra("hari_jadwal") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            data1.setText(cursor.getString(2).toString());
            data2.setText(cursor.getString(3).toString());
            data3.setText(cursor.getString(5).toString());
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);
                HashMap<String, String> menghubungData = new HashMap<String, String>();
                menghubungData.put("kol1", cursor.getString(1).toString());
                menghubungData.put("kol2", cursor.getString(4).toString());
                melengkapiData.add(menghubungData);
            }
        }
        AdapterJadwal adaptasiWarna = new AdapterJadwal(this, melengkapiData, R.layout.per_baris_nya, darimana, kemana);
        thetampilan.setAdapter(adaptasiWarna);

        thetampilan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                final HashMap<String, String> selection = melengkapiData.get(arg2);
                final CharSequence[] dialogitem = {"Edit Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(JadwalKegiatan.this);
                builder.setTitle("Aksi");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:

                                break;
                            case 1:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("DELETE FROM jadwal WHERE waktu_jadwal = '" + selection + "'");
                                RefreshLists();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
