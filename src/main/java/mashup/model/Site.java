package mashup.model;

import java.net.URL;

public class Site {
	public Site(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	String url;
}
