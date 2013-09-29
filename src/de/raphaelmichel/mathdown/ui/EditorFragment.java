package de.raphaelmichel.mathdown.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import de.raphaelmichel.mathdown.R;

public class EditorFragment extends Fragment {

	public EditorFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		String content = sp.getString("DEMO_TEXT", "");
		if ("".equals(content)) {
			content = "Hello! I can do Maths! $\\sqrt{4} = 2$.";
			sp.edit().putString("DEMO_TEXT", content).commit();
		}
		View rootView = inflater.inflate(R.layout.fragment_editor, container,
				false);
		EditText editorText = (EditText) rootView.findViewById(R.id.etEditor);
		editorText.setText(content);
		Button btSave = (Button) rootView.findViewById(R.id.btSave);
		btSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sp = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				sp.edit()
						.putString(
								"DEMO_TEXT",
								((EditText) getView().findViewById(
										R.id.etEditor)).getText().toString())
						.commit();
			}
		});
		return rootView;
	}
}