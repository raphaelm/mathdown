package de.raphaelmichel.mathdown.ui;

import java.io.IOException;
import java.io.InputStream;

import org.markdown4j.Markdown4jProcessor;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import de.raphaelmichel.mathdown.R;

public class PreviewFragment extends Fragment {

	public PreviewFragment() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_preview, container,
				false);

		WebView wvPreview = (WebView) rootView.findViewById(R.id.wvPreview);
		wvPreview.getSettings().setJavaScriptEnabled(true);

		Button btReload = (Button) rootView.findViewById(R.id.btReload);
		btReload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sp = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				final WebView wvPreview = (WebView) getView().findViewById(
						R.id.wvPreview);
				String content = sp.getString("DEMO_TEXT", "");
				String html = "";
				try {
					html = new Markdown4jProcessor().process(content);
					InputStream is = getActivity().getAssets().open(
							"template.html");
					int length = is.available();
					byte[] data = new byte[length];
					is.read(data);
					String template = new String(data);
					html = template.replace("{{ CONTENT }}", html);
					wvPreview.loadDataWithBaseURL("file:///android_asset/",
							html, "text/html", "UTF-8", null);
					wvPreview.setWebViewClient(new WebViewClient() {
						@Override
						public void onPageFinished(WebView view, String url) {
							super.onPageFinished(view, url);
							wvPreview
									.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
						}
					});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return rootView;
	}
}