package MusicPlayer.config;



import MusicPlayer.enums.Genre;
import MusicPlayer.enums.Type;
import MusicPlayer.models.Audio;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Database {
    public Database() {
    }

    public  LinkedList<Audio> readDatabase() {


            LinkedList<Audio> audios = new LinkedList<>();
            File read = new File("C:\\Users\\Priyanshu Singh\\Desktop\\git hub\\music-player\\Music Player\\src\\MusicPlayer\\database.txt");
            try {
                Scanner sc = new Scanner(read);
                Scanner scanner = new Scanner(System.in);
                while (sc.hasNext()) {
                    String[] str = sc.nextLine().split(",");
                    audios.add( new Audio(str[0], str[1], Genre.valueOf(str[2]), str[3], str[4], Type.valueOf(str[5]), str[6]));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           return audios;
        }

        public void readDataFromFile() throws IOException {
            //File read = new File("input.txt");
            //FileWriter writer = new FileWriter("output.txt");

        }
    }

