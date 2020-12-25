package main;

import be.ceau.itunesapi.Search;
import be.ceau.itunesapi.request.*;
import be.ceau.itunesapi.request.search.Attribute;
import be.ceau.itunesapi.request.search.Media;
import be.ceau.itunesapi.response.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchApi {

    private static String getArtistPicture(String artistView) throws IOException {
        Document doc = Jsoup.connect(artistView).get();
        var x = doc.select("meta[property=\"og:image\"]").attr("content");
        return x.substring(0, x.lastIndexOf("/") + 1) + "80x80-999.jpg";
    }

    public static String get80x80Picture(String url) {
        return url.substring(0, url.lastIndexOf("/") + 1) + "80x80-999.jpg";
    }

    public static List<SearchResponse> getTopResults(String term) throws IOException {
        List<SearchResponse> responseList = new ArrayList<>();

        Response albumResponse = new Search(term)
                .setCountry(Country.UNITED_STATES)
                .setAttribute(Attribute.ALBUM_TERM)
                .setEntity(Entity.ALBUM)
                .setMedia(Media.ALL)
                .setLimit(1)
                .execute();

        Result response = albumResponse.getResults().get(0);
        response.setArtworkUrl30(get80x80Picture(response.getLargestArtworkUrl()));
        responseList.add(new SearchResponse(response.getArtistName(), "Album", "", response.getCollectionName(), response.getArtworkUrl30()));

        Response artistResponse = new Search(term)
                .setCountry(Country.UNITED_STATES)
                .setAttribute(Attribute.ARTIST_TERM)
                .setEntity(Entity.ALL_ARTIST)
                .setMedia(Media.ALL)
                .setLimit(1)
                .execute();

        response = artistResponse.getResults().get(0);
        response.setArtistViewUrl(getArtistPicture("https://itunes.apple.com/ca/artist/taylor-swift/id" + response.getArtistId()));
        responseList.add(new SearchResponse(response.getArtistName(), "Artist", "", "", response.getArtistViewUrl()));

        Response songResponse = new Search(term)
                .setCountry(Country.UNITED_STATES)
                .setAttribute(Attribute.SONG_TERM)
                .setEntity(Entity.SONG)
                .setMedia(Media.ALL)
                .setLimit(1)
                .execute();

        response = songResponse.getResults().get(0);
        response.setArtworkUrl30(get80x80Picture(response.getLargestArtworkUrl()));
        responseList.add(new SearchResponse(response.getArtistName(), "Song", response.getTrackName(), "", response.getArtworkUrl30()));

        return responseList;
    }
}
