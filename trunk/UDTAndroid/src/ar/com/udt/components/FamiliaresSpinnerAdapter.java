package ar.com.udt.components;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import ar.com.udt.model.Persona;

public class FamiliaresSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

	private ArrayList<Persona> familia = null;
	private Context myCtx;
	
	public FamiliaresSpinnerAdapter(Context ctx,ArrayList<Persona> familia){
		this.familia = familia;
		myCtx = ctx;
	}

	public int getCount() {
        return (familia!=null)?familia.size():0;
    }

    public Object getItem(int position) {
        try {
			return familia.get(position);
		} catch (Exception e) {
			return null;
		}
    }
    
    public long getItemId(int position) {
		return familia.get(position).getId();
    }

    public View getView(int position, View view, ViewGroup parent) {
        TextView text = new TextView(myCtx);
        	text.setPadding(5,5,5,5);
        	text.setGravity(Gravity.CENTER);
			text.setText(familia.get(position).getUsername().toUpperCase());
			text.setGravity(Gravity.LEFT);
			text.setTextColor(Color.GRAY);
        return text;
    }

}
