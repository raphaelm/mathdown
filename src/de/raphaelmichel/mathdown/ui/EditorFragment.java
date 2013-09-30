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
		String content = sp.getString("SAMPLE_TEXT", "");
		if (!"a".equals(content)) {
			content = "# Welcome to MathDown\n"
					+ "Hello! Look at me! I can do **math**! $\\sqrt{4} = 2$!\n\n"
					+ "Of course I also do the boring stuff:\n"
					+ "* Lists\n"
					+ "* *Italic*, **Bold** or _underlined_ text\n\n"
					+ "\\begin{aligned}"
					+ "\\nabla \\times \\vec{\\mathbf{B}} -\\, \\frac1c\\, \\frac{\\partial\\vec{\\mathbf{E}}}{\\partial t} & = \\frac{4\\pi}{c}\\vec{\\mathbf{j}} \\\\\\\\ \n"
					+ "\\nabla \\cdot \\vec{\\mathbf{E}} & = 4 \\pi \\rho \\\\\\\\ \n"
					+ "\\nabla \\times \\vec{\\mathbf{E}}\\, +\\, \\frac1c\\, \\frac{\\partial\\vec{\\mathbf{B}}}{\\partial t} & = \\vec{\\mathbf{0}} \\\\\\\\ \n"
					+ "\\nabla \\cdot \\vec{\\mathbf{B}} & = 0 \\end{aligned}";
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
								"SAMPLE_TEXT",
								((EditText) getView().findViewById(
										R.id.etEditor)).getText().toString())
						.commit();
			}
		});
		return rootView;
	}
}