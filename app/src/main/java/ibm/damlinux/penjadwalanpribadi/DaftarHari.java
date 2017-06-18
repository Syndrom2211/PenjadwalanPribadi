package ibm.damlinux.penjadwalanpribadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

public class DaftarHari extends AppCompatActivity{
    String[] daftarhari;
    ListView listView02;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarHari dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarhari);

        dh = this;
        dbcenter = new DataHelper(this);
        RefreshLists();
    }

    public void RefreshLists(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE bulan_jadwal = '" + getIntent().getStringExtra("bulan_jadwal") + "'",null);
        daftarhari = new String[cursor.getCount()];
        cursor.moveToFirst();

        for(int cc = 0;cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarhari[cc] = cursor.getString(2).toString();
        }

        listView02 = (ListView) findViewById(R.id.listView02);
        listView02.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1, daftarhari));
        listView02.setSelected(true);
        listView02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarhari[arg2]; //.getItemAtPosition(arg2).toString();
                //final CharSequence[] dialogitem = {"Lihat Waktu & Kegiatan"};
                //AlertDialog.Builder builder = new AlertDialog.Builder(DaftarHari.this);
                //builder.setTitle("Pilihan");
                //builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface dialog, int item) {
                        //switch(item){
                            //case 0 :
                                Intent i = new Intent(getApplicationContext(), JadwalKegiatan.class);
                                i.putExtra("hari_jadwal", selection);
                                startActivity(i);
                                //break;
                        //}
                //    }
               // });
                //builder.create().show();
            }});
        ((ArrayAdapter)listView02.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
