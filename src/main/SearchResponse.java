package main;

public class SearchResponse {
    private final String artistName;

    private final String mediaType;

    private final String songName;

    private final String albumName;

    private final String mediaUrl;

    SearchResponse(String artistName, String mediaType, String songName, String albumName, String mediaUrl) {
        this.artistName = artistName;
        this.mediaType = mediaType;
        this.songName = songName;
        this.albumName = albumName;
        this.mediaUrl = mediaUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
