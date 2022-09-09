package MusicPlayer.models;


import MusicPlayer.enums.Genre;
import MusicPlayer.enums.Type;

public class Song  extends Audio {
    Type type;
    String singer;

    public Song(String title, String duration, Genre genre, String path, String album, Type type, String singer) {
        super(title, duration, genre, path, album);
        this.type = type;
        this.singer=singer;
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

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", path='" + path + '\'' +
                ", Album='" + Album + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void delete(Song song)
    {
        song = null;
        System.out.println(" Song is deleted ");
    }


    public static class Artist {
        String name;

        public Artist(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "Artist{" +
                    "name='" + name + '\'' +
                    ", allAudio=" + +
                    '}';
        }
    }
}
