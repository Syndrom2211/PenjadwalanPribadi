package ibm.damlinux.penjadwalanpribadi;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class AdapterJadwal extends SimpleAdapter {
    private int[] warnaYgDipakai = new int[] { 0x30ffffff, 0x30ffffff, 0x30ffffff };
    //berikut adalah constructor_nya
    public AdapterJadwal(Context contextNya, List<HashMap<String, String>> bagianYgDiWarnai, int sumberNya, String[] darimana, int[] kemana) {
        super(contextNya, bagianYgDiWarnai, sumberNya, darimana, kemana);
    }

    @Override
    public View getView(int wilayahYgdiWarnai, View perubahanWarna, ViewGroup semuaWarna) {
        View tampilanAkhir = super.getView(wilayahYgdiWarnai, perubahanWarna, semuaWarna);
        int posisiWarna = wilayahYgdiWarnai % warnaYgDipakai.length;
        tampilanAkhir.setBackgroundColor(warnaYgDipakai[posisiWarna]);
        return tampilanAkhir;
    }
}