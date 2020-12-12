package com.j.sm.task;

import javax.swing.*;
import java.io.*;

/**
 * @ClassName TxtThread
 * @Description
 * @Author orange
 * @Date 2020-12-12 17:07
 **/

public class TxtThread implements Runnable{
    File file = new File("/Users/orange/Desktop/text.txt");

    private JLabel txtLabel;

    public void setTxtLabel(JLabel txtLabel) {
        this.txtLabel = txtLabel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                InputStream in;
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while((str = bufferedReader.readLine()) != null) {
                    txtLabel.setText(str);
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

