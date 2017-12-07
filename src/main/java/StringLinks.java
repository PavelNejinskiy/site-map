import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LinksFromSinglePage<T> implements Links<String> {

    String url;

    public LinksFromSinglePage(String url) {
        this.url = url;
    }

    private List<String> prepareLinks() {

        List<String> links  = new ArrayList<String>();
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Elements elements = doc.body().getElementsByTag("a");

                for (Element element : elements) {
                    String urlName =  element.attr("abs:href");
                    if (!urlName.equals("")) {
                        links.add(urlName);
                    }
                }

            } catch (IOException e) {
            }

        return links;
    }

    public Iterator<String> iterator() {
        List<String> links = prepareLinks();
        return links.iterator();

    }


}