
/*
 * SE1021 - 032
 * Winter 2016
 * Lab 1 - Wav Manipulation
 * Name: Ian Guswiler
 * Created: 12/3/2015
 */

package guswilerib;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * @author Ian Guswiler
 * @version 12/7/2015
 *
 * Uses the WavFile class to perform a number of manipulations on .wav files based on user input
 */
public class Lab1 {

    /**
     * Main program functions that creates looping gui menus until the program is exited
     * @param args array of strings containing the program command-line arguments
     */
    public static void main(String[] args) {
        boolean isRunning = true;

        while(isRunning){
            int input = mainMenu();
            switch(input){
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    reverseWav();
                    break;
                case 2:
                    frequency();
                    break;
                case 3:
                    twoChannel();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid input");
                    break;
            }
        }
    }

    /**
     * This method creates the gui main input menu for the program
     *
     * @return The integer the user has entered for menu selection
     */
    private static int mainMenu(){
        String input = JOptionPane.showInputDialog(null, "Select an option.\n0. Exit\n1. Reverse a .wav file" +
                "\n2. Create a .wav of a specified frequency\n3. Create a .wav with two frequencies");
        return Integer.parseInt(input);
    }

    /**
     * Method used to reverse the samples of a selected .wav file
     * and create a new .wav file with a 'Rev' affix
     */
    private static void reverseWav(){
        String fileName = JOptionPane.showInputDialog(null, "Please input a wav file name without the .wav extension");
        WavFile original = new WavFile(fileName + ".wav");

        ArrayList<Double> samples = original.getSamples();
        ArrayList<Double> reverseSamples = new ArrayList<>();
        for(int i  = 1; i <= samples.size(); i++){
            reverseSamples.add(samples.get(samples.size() - i));
        }

        WavFile reverse = new WavFile(fileName + "Rev.wav", original.getNumChannels(), original.getNumFrames(),
                original.getValidBits(), original.getSampleRate());
        reverse.setSamples(reverseSamples);
    }

    /**
     * Method used to create a .wav file of one second of audio at a specified frequency with a specified file name
     */
    private static void frequency(){
        int sampleRate = 96000;
        int bits = 16;
        int channels = 1;

        String nameInput = JOptionPane.showInputDialog(null, "Enter a file name without an extension");
        String frequencyInput = JOptionPane.showInputDialog(null, "Enter a frequency");

        ArrayList<Double> samples = new ArrayList<>();
        for(int i = 0; i < sampleRate; i++){
            samples.add(2 * Math.PI * i * Double.parseDouble(frequencyInput)/sampleRate);
        }

        WavFile frequency = new WavFile(nameInput + ".wav", channels, sampleRate, bits, sampleRate);
        frequency.setSamples(samples);
    }

    /**
     * Method used to create a .wav file of one second of audio with two specified frequencies interleaved
     * and a specified file name
     */
    private static void twoChannel(){
        int sampleRate = 96000;
        int bits = 16;
        int channels = 2;

        String nameInput = JOptionPane.showInputDialog(null, "Enter a file name without an extension");
        String frequencyInput1 = JOptionPane.showInputDialog(null, "Enter the first frequency");
        String frequencyInput2 = JOptionPane.showInputDialog(null, "Enter the second frequency");

        ArrayList<Double> samples = new ArrayList<>();
        for(int i = 0; i < sampleRate * 2; i++){
            if(i%2 == 0){
                samples.add(2 * Math.PI * i * Double.parseDouble(frequencyInput1)/sampleRate);
            }else {
                samples.add(2 * Math.PI * i * Double.parseDouble(frequencyInput2)/sampleRate);
            }
        }

        WavFile stereo = new WavFile(nameInput + ".wav", channels, sampleRate, bits, sampleRate);
        stereo.setSamples(samples);
    }
}
