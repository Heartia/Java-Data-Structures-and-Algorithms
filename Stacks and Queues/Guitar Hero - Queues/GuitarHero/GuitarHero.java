package GuitarHero;

import java.awt.*;
import java.util.ArrayList;

public class GuitarHero {

    public static void main(String[] args) {

        // Create two guitar strings, for concerts shown in the keyboard string below
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitar = new GuitarString[keyboard.length()];
        int ticks = 0;
        int maxTicks = 1500;
        ArrayList<Double> records = new ArrayList<>();
        final int PLAYING = 0, RECORDING = 1, PLAYBACKING = 2;
        int state = PLAYING;
        int index = 0;

        for (int k = 0; k < guitar.length; k++) {
            guitar[k] = new GuitarString(440 * Math.pow(1.05956, k - 24));
        }

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (keyboard.contains(key + "") && state != PLAYBACKING) {
                    guitar[keyboard.indexOf(key)].pluck();
                    //System.out.println(key);
                    //guitarPitch.add(guitar[keyboard.indexOf(key)]);
                }
                else if (key == '1') {
                    state++;
                    if (state == 3) {
                        state = 0;
                        records.clear();
                    }
                }

            }

            // compute the superposition of the samples
            double sample = 0;
            if (state == PLAYING || state == RECORDING) {
                for (int i = 0; i < guitar.length; i++) {
                    sample += guitar[i].sample();
                }
            }
            else if (state == PLAYBACKING) {
                if (index >= records.size()) {
                    index = 0;
                }
                sample += records.get(index);
                index++;
            }
            if (ticks != maxTicks) {
                ticks++;
            }
            else {
                StdDraw.setPenColor(new Color(232, 236, 255));
                StdDraw.filledSquare(0, 0, StdDraw.getWidth());
                StdDraw.setPenColor(new Color(0, 0, 50));
                StdDraw.line(0, sample + 0.5, 1, sample + 0.5);
                String status = "[System] State: ";
                if (state == 0) {
                    status += "PLAYING";
                }
                else if (state == 1) {
                    status += "RECORDING";
                }
                else if (state == 2) {
                    status += "PLAYING BACK";
                }
                StdDraw.text(0.2, 0, status);
                if (state == RECORDING) {
                    records.add(sample);
                }
                ticks = 0;
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < guitar.length; i++) {
                guitar[i].tic();
            }
        }
    }

}