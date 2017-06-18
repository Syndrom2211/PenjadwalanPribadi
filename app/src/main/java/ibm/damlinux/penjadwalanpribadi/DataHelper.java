package ibm.damlinux.penjadwalanpribadi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "penjadwalanpribadi.db";
    private static final int DATABASE_VERSION =1;

    // Database creation sql statement
    public static final String jadwal= "CREATE TABLE jadwal(id_jadwal INTEGER PRIMARY KEY AUTOINCREMENT, waktu_jadwal TEXT NULL, hari_jadwal TEXT NULL, bulan_jadwal TEXT NULL, kegiatan_jadwal TEXT NULL, tahun_jadwal TEXT NULL);";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(jadwal);

    }

    // Method is called during an upgrade of the database, e.g. if you increase
    // the database version

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {

        Log.w(DataHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS jadwal");
        database.execSQL("DROP TABLE IF EXISTS catatan");

        onCreate(database);

    }


    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }


}
