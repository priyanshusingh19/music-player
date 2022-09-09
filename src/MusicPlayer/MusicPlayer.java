package MusicPlayer;



import MusicPlayer.config.Database;
import MusicPlayer.enums.Genre;
import MusicPlayer.enums.Type;
import MusicPlayer.models.Audio;
import MusicPlayer.models.Playlist;
import MusicPlayer.service.AudioStreamPlay;
import MusicPlayer.service.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;

public class MusicPlayer {
   public static LinkedList<Audio> audios = new LinkedList<>();
    public static List<Playlist> playlists = new ArrayList<>();
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Database db = new Database();
        audios = db.readDatabase();
        Player play = new Player();
        AudioStreamPlay ad = new AudioStreamPlay();
        Playlist Arijit = new Playlist(" Arijit Singh Playlist");
        Playlist Jubin = new Playlist(" Jubbin Nautiyal Playlist");
        Playlist podcast = new Playlist(" PodCasts");
        Playlist sadSongs = new Playlist(" Sad Songs");
        Playlist happy = new Playlist(" Happy Songs ");
        Playlist motivation = new Playlist(" Motivations");
        playlists.add(Arijit);
        playlists.add(Jubin);
        playlists.add(podcast);
        playlists.add(sadSongs);
        playlists.add(happy);
        playlists.add(motivation);

   for(Audio song : audios)
   {
       if(song.getSinger().equals("Arijit Singh"))
           Arijit.add(song);
       else if(song.getSinger().equals("Jubin Nautiyal"))
           Jubin.add(song);
       else if(song.getType()== Type.podcast)
           podcast.add(song);
       else if(song.getGenre()== Genre.happy)
           happy.add(song);
       else if(song.getGenre()==Genre.sad)
           sadSongs.add(song);
       else if(song.getGenre()==Genre.motivation)
           motivation.add(song);
   }
   Boolean exit = false;

   while (!exit) {
      // showAllAudios();
      //showPlaylist();
       printMenu();
       int choice = sc.nextInt();
       switch (choice) {
           case 0:
               exit = true;
               break;
           case 1:
               showAllAudios();
               break;
           case 2:
               play.play(audios);
               break;
           case 3:
               showPlaylist();
               System.out.println(" Do you want to create your own playlists \n press 0 for yes and press any other numeric value to continue" );
               int a = sc.nextInt();
               if(a!=0) {
               showPlaylist();
                   System.out.println(" select playlist you want to play ");
                   int ch = sc.nextInt();
                   play.play(playlists.get(ch-1).getAudios());
               }
               else createPlaylist();
               break;
           case 4: showPlaylist();
               System.out.println(" enter the index of playlist you want to remove");
               int i = sc.nextInt();
               playlists.remove(i-1);
               System.out.println(" Available Playlists ");
               showPlaylist();
               break;
           case 5:
               if(!search())

               {
                   System.out.println(" this song is not available");
               }
               break;
           case 6:
               if(!artistSearch())
               {
                   System.out.println("Artist not found");
               }
               break;
           case 7:
               if(addNewAudio())
               {
                   System.out.println("Audio is added \n");
                   showAllAudios();
               }
       }
   }
    }

    public  static void createPlaylist()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter the name of Your Playlist");
        String name = sc.nextLine();
        Playlist pl = new Playlist(name);
        int i = -1;
        LinkedList<Audio> songs = audios;

        while(i!=0)
        {int j=1;
            for(Audio song : songs) {
                System.out.println(j + song.getTitle());
                j++;
            }
            System.out.println(" enter the index value to add song into your playlist or press 0 to back in previous menu");
            i=sc.nextInt();
            if(i!=0) {
                pl.add(songs.get(i - 1));
                songs.remove(i - 1);

            }
        }
        playlists.add(pl);
    }

    private static void printMenu(){
        System.out.println("Available options\n");
        System.out.println("0 - close music Player\n"+"1 - to show all available audios \n" +"2- to play default audio list\n"+ "3 - to play playlists   \n"+"4 - to remove playlist \n"+
                "5 - to search song" +"\n 6 - to search artist"+"\n 7 - to add new song");
    }

    private static void showPlaylist()
    {
        System.out.println(" Available Playlist ");
        int i=1;
        for (Playlist playlist : playlists) {
            System.out.println( i +playlist.getName());
            i++;
        }
    }
    private static void showAllAudios()
    {
        System.out.println(" Available Songs & Podcasts");
        for (Audio song : audios)
            System.out.println(song.getTitle());
    }

    private static Boolean search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the song or Podcast title to search ");
        String search = sc.nextLine();
        Boolean status = false;
        ListIterator<Audio> ltr = audios.listIterator();
        for (Audio audio : audios) {
            String[] str = audio.getTitle().split(" ");
            String[] str1 = audio.getTitle().split("-");
            if (audio.getTitle().equalsIgnoreCase(search) || audio.getTitle().substring(0, 3).equals(search) || str[0].equalsIgnoreCase(search) || str1[0].equalsIgnoreCase(search)) {
                status = true;
                Player player = new Player();
                System.out.println(audio.getType() + " is found");
                try {
                    player.play(audio);
                } catch (Exception e) {
                    System.out.println(" Unable to Play this Audio");
                    continue;
                }
            }
        }
        return status;
    }

    private static Boolean addNewAudio()
    {
        boolean exit = false;
        boolean status = false;
        Scanner sc = new Scanner(System.in);
        String title="";
        while(!exit) {
            System.out.println(" Enter the title of the Audio");
            title = sc.nextLine();
            if(titleCheck(title))
            {
                System.out.println(" title already exists ");
            }
            else exit = true;
        }
        String type;
        System.out.println(" select the Audio type \n 1 - for song \n 2 - for podcast \n 3 - for music \n 4 - for not defined");
        String choice = sc.nextLine();
        int ch =Integer.parseInt(choice);
        if(ch==1)
            type="song";
        else if(ch==2)
            type="podcast";
        else if(ch==3)
            type ="music";
        else type="notDefined";

        System.out.println(" enter the path");
        String path=  sc.nextLine();
        String gType;
        System.out.println(" select the Genre type \n 1 - for happy \n 2 - for sad \n 3 - for motivation \n 4 - for love \n5- for not defined");
        choice = sc.nextLine();
        ch = Integer.parseInt(choice);
        if(ch==1)
            gType="happy";
        else if(ch==2)
            gType="sad";
        else if(ch==3)
            gType ="motivation";
        else if(ch==4)
            gType="love";
        else gType="notDefined";

        System.out.println(" enter the artist name");
        String artist = sc.nextLine();

        System.out.println("enter the album aur movie name");
        String album = sc.nextLine();

        System.out.println("enter the duration");
        String duration = sc.nextLine();

       try {
           audios.add(new Audio(title, duration, Genre.valueOf(gType), path, album, Type.valueOf(type), artist));
           System.out.println("song is added successfuly");
           status = true;
       }
       catch (Exception e)
       {e.printStackTrace();
       }


        return status;
    }

    private static Boolean titleCheck(String search)
    {
        for(Audio audio : audios) {
            String[] str = audio.getTitle().split(" ");
            String[] str1 = audio.getTitle().split("-");
            if (audio.getTitle().equalsIgnoreCase(search) || audio.getTitle().substring(0, 3).equals(search) || str[0].equalsIgnoreCase(search) || str1[0].equalsIgnoreCase(search)) {
                return true;
            }
        }
return false;

            }

        private static Boolean artistSearch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the artist name to search ");
        String search = sc.nextLine();
        Boolean status = false;
        List<Audio> artistSong = new ArrayList<>();
        ListIterator<Audio> ltr = audios.listIterator();
        for (Audio audio : audios) {
            String[] str = audio.getSinger().split(" ");
            if (str[0].equalsIgnoreCase(search) || audio.getSinger().equalsIgnoreCase(search)) {
                status = true;
                artistSong.add(audio);
            }
            Player player = new Player();
            if (status) {
                System.out.println("Artist is found");
                artistSong.toString();
                System.out.println(" Press 1 to Play the song of " + search + "\n Else play any other numeric value to back in previous menu ");
                int temp = sc.nextInt();
                if (temp == 1) {
                    try {
                        player.play(artistSong);
                    } catch (Exception e) {
                        System.out.println(" Unable to Play this Audio");
                        continue;
                    }
                }
            }
        }
        return status;
    }


}
