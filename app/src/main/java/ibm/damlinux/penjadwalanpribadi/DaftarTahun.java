package ibm.damlinux.penjadwalanpribadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.widget.Button;

/**
 * Created by damlinux on 14/09/16.
 */
public class DaftarTahun extends AppCompatActivity{
    String[] daftartahun;
    ListView ListView01;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarTahun dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftartahun);

        dt = this;
        dbcenter = new DataHelper(this);
        RefreshLists();
    }

    public void RefreshLists(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal", null);
        daftartahun = new String[cursor.getCount()];
        cursor.moveToFirst();

        for(int cc = 0;cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftartahun[cc] = cursor.getString(5).toString();
        }

        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1, daftartahun));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftartahun[arg2]; //.getItemAtPosition(arg2).toString();
                //final CharSequence[] dialogitem = {"Lihat Hari","Hapus Hari"};
                //AlertDialog.Builder builder = new AlertDialog.Builder(DaftarJadwal.this);
                //builder.setTitle("Pilihan");
                //builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                //public void onClick(DialogInterface dialog, int item) {
                //switch(item){
                //case 0 :
                Intent i = new Intent(getApplicationContext(), DaftarJadwal.class);
                i.putExtra("tahun_jadwal", selection);
                startActivity(i);
                //break;
                //case 1 :
                //db.execSQL("delete from jadwal where bulan_jadwal = '"+selection+"'");
                //RefreshList();
                //break;
                //}
                //}
                //});
                //builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
