package guswilerib;

import javax.swing.*;
import java.util.ArrayList;

//hey there we go
public class Lab1 {
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

    public static int mainMenu(){
        String input = JOptionPane.showInputDialog(null, "Select an option.\n0. Exit\n1. Reverse a .wav file\n2. Create a .wav of a specified frequency\n3. Create a .wav with two frequencies");
        return Integer.parseInt(input);
    }
    public static void reverseWav(){
        String fileName = JOptionPane.showInputDialog(null, "Please input a wav file name without the .wav extension");
        WavFile original = new WavFile(fileName + ".wav");
        ArrayList<Double> samples = original.getSamples();
        ArrayList<Double> reverseSamples = new ArrayList<>();
        for(int i  = 1; i <= samples.size(); i++){
            reverseSamples.add(samples.get(samples.size() - i));
        }
        WavFile reverse = new WavFile(fileName + "Rev.wav", original.getNumChannels(), original.getNumFrames(), original.getValidBits(), original.getSampleRate());
        reverse.setSamples(reverseSamples);
    }
    public static void frequency(){
        String nameInput = JOptionPane.showInputDialog(null, "Enter a file name without an extension");
        String frequencyInput = JOptionPane.showInputDialog(null, "Enter a frequency");
        int sampleRate = 96000;
        int bits = 16;
        int channels = 1;
        ArrayList<Double> samples = new ArrayList<>();
        for(int i = 0; i < sampleRate; i++){
            samples.add(2 * Math.PI * i * Double.parseDouble(frequencyInput)/sampleRate);
        }
        WavFile frequency = new WavFile(nameInput + ".wav", channels, sampleRate, bits, sampleRate);
        frequency.setSamples(samples);
    }
    public static void twoChannel(){
        String nameInput = JOptionPane.showInputDialog(null, "Enter a file name without an extension");
        String frequencyInput1 = JOptionPane.showInputDialog(null, "Enter the first frequency");
        String frequencyInput2 = JOptionPane.showInputDialog(null, "Enter the second frequency");
        int sampleRate = 96000;
        int bits = 16;
        int channels = 2;
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
