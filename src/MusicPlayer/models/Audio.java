package MusicPlayer.models;


import MusicPlayer.enums.Genre;
import MusicPlayer.enums.Type;

public class Audio {
    String title;
    String duration;
    Genre genre;
    String path;
    String Album;
    Type type;
    String singer;

    public Audio() {
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Audio(String title, String duration, Genre genre, String path, String album) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.path = path;
        Album = album;
    }

    public Audio(String title, String duration, Genre genre, String path, String album, Type type, String singer) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.path = path;
        Album = album;
        this.type = type;
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }



    public static class Podcast extends Audio {
        String type;

        public Type getType() {
            return getType();
        }

        public void setType(String type) {
            this.type = type;
        }

        public Podcast(String title, String duration, Genre genre, String path, String album, String type) {
            super(title, duration, genre, path, album);
            this.type = type;
        }

        @Override
        public String toString() {
            return "Podcast{" +
                    "title='" + title + '\'' +
                    ", duration='" + duration + '\'' +
                    ", genre='" + genre + '\'' +
                    ", path='" + path + '\'' +
                    ", Album='" + Album + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
