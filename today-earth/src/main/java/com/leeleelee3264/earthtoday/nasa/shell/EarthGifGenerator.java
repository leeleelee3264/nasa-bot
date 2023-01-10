package com.leeleelee3264.earthtoday.nasa.shell;


import com.leeleelee3264.earthtoday.exception.ShellException;
import com.leeleelee3264.earthtoday.util.LoggingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EarthGifGenerator {

    private static void validateOS() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.startsWith("windows")) {
            throw new ShellException.NotSupportedOS(OS);
        }
    }

    public static void generate(String directory, String fileName) {
        validateOS();

        String fullFileName = directory + "/" + fileName;

        String convertCommand = "convert -delay 60 " + directory + "/*.png " + fullFileName;
        String gifsicleCommand = "gifsicle -O3 --colors 128 --lossy=80 -o " + fullFileName + " " + fullFileName;

        try {
            Process convertProcess = Runtime.getRuntime().exec(convertCommand);
            convertProcess.waitFor();

            Process gifsicleProcess = Runtime.getRuntime().exec(gifsicleCommand);

            BufferedReader convertStdError = new BufferedReader(new
                    InputStreamReader(convertProcess.getErrorStream()));

            BufferedReader gifsicleStdError = new BufferedReader(new
                    InputStreamReader(gifsicleProcess.getErrorStream()));

            // Log shell command error if any
            while (convertStdError.readLine() != null) {
                LoggingUtils.error("convert Command Error: {}", convertStdError.readLine());
            }

            while (gifsicleStdError.readLine() != null) {
                LoggingUtils.error("gifsicle Command Error: {}", gifsicleStdError.readLine());
            }

        } catch (IOException | InterruptedException e) {
            LoggingUtils.error(e);
        }

    }



}
