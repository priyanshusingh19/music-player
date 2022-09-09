package MusicPlayer.models;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    String name;
    int count = 0;
    List<Audio> audios = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist( List<Audio> audios) {
        this.count = this.getCount()+1;
        this.audios = audios;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Audio> getAudios() {
        return audios;
    }

    public void setAudios(List<Audio> audios) {
        this.audios = audios;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", audios=" + audios +
                '}';
    }

    public void add( Audio audio)
    {
        this.count+=1;
        audios.add(audio);
    }

    public void removeSong(Audio song)
    {
        audios.remove(song);
    }

    public void deletePlaylis( Playlist playlist)
    {
        playlist = null;
        System.out.println("Playlist Deleted ");

    }
}
