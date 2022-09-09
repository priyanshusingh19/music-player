package MusicPlayer.service;



import MusicPlayer.models.Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.*;

public class Player {
    public Player() {
    }

    public static void play(List<Audio> playList) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        ListIterator<Audio> listIterator = playList.listIterator();
        AudioStreamPlay audioStreamPlay = new AudioStreamPlay();

        if (playList.size() == 0) {
            System.out.println("This playlist have no song ");
        } else {
            Audio song = listIterator.next();
                System.out.println("Currently playing " + song.getTitle() +" Duration -"+ song.getDuration());
                audioStreamPlay.play(song.getPath());
                while (!quit) {
                    printMenu();
                    System.out.println(" enter your choice");
                    int action = sc.nextInt();
                    sc.nextLine();

                    switch (action) {
                        case 0:
                            audioStreamPlay.close();
                           // System.out.println("Playlist complete");
                            quit = true;
                            break;

                        case 1:
                            audioStreamPlay.close();
                            if (listIterator.hasNext()) {
                                audioStreamPlay.play(listIterator.next());

                            } else {
                                System.out.println("no song availble, reached to the end of the list");
                            }
                            break;
                        case 2:
                            if(listIterator.hasPrevious())
                            {
                                audioStreamPlay.close();
                                audioStreamPlay.play(listIterator.previous());
                            }
                            else {
                                System.out.println("We are at the first song");
                            }
                            break;

                        case 3:
                           audioStreamPlay.close();
                           listIterator.previous();
                           audioStreamPlay.play(listIterator.next());
                            break;

                        case 4:
                            audioStreamPlay.stop();
                            break;

                        case 5:
                            audioStreamPlay.resume();
                            break;
                        case 6:
                            if (playList.size() > 0) {
                                listIterator.remove();
                                if (listIterator.hasNext()) {
                                    audioStreamPlay.close();
                                    audioStreamPlay.play(listIterator.next());
                                } else {
                                    if (listIterator.hasPrevious()) {
                                        audioStreamPlay.close();
                                        audioStreamPlay.play((listIterator.previous()));
                                    }
                                }
                            }
                            break;
                        default:
                            audioStreamPlay.close();;
                            audioStreamPlay.play(listIterator.next());
                            break;
                    }
                }
        }
    }

    public static void play(Audio singleTrack) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        List<Audio> playList = new ArrayList<>();
        playList.add(singleTrack);
        ListIterator<Audio> listIterator = playList.listIterator();
        AudioStreamPlay audioStreamPlay = new AudioStreamPlay();

        if (playList.size() == 0) {
            System.out.println("This playlist have no song ");
        } else {
            Audio song = listIterator.next();
            System.out.println("Currently playing " + song.getTitle() +" Duration -"+ song.getDuration());
            audioStreamPlay.play(song.getPath());
            while (!quit) {
                if(playList.size()==0)
                {
                    System.out.println(" No song Available in this list \n Press 0 to return main menu");

                }
                else {
                    printMenu();
                    System.out.println(" enter your choice");
                }
                int action = sc.nextInt();
                sc.nextLine();

                switch (action) {
                    case 0:
                        audioStreamPlay.close();
                        // System.out.println("Playlist complete");
                        quit = true;
                        break;

                    case 1:
                        audioStreamPlay.close();
                        if (listIterator.hasNext()) {
                            audioStreamPlay.play(listIterator.next());

                        } else {
                            System.out.println("no song availble, reached to the end of the list");
                        }
                        break;
                    case 2:
                        if(listIterator.hasPrevious())
                        {
                            audioStreamPlay.close();
                            audioStreamPlay.play(listIterator.previous());
                        }
                        else {
                            System.out.println("We are at the first song");
                        }
                        break;

                    case 3:
                        audioStreamPlay.close();
                        listIterator.previous();
                        audioStreamPlay.play(listIterator.next());
                        break;

                    case 4:
                        audioStreamPlay.stop();
                        break;

                    case 5:
                        audioStreamPlay.resume();
                        break;
                    case 6:
                        audioStreamPlay.close();
                        playList.remove(singleTrack);
                        break;
                    default:
                        audioStreamPlay.close();;
                        audioStreamPlay.play(listIterator.next());
                        break;
                }
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available options\n press");
        System.out.println("0 - to quit current playing list \n"+
                "1 - to play next song\n"+
                "2 - to play previous song\n"+
                "3 - to replay the current song\n"+
                "4 - stop\n"+
                "5 - resume\n"+
                "6 - delete current song");
    }


}
