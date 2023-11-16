package com.runoono.screenfixer;


import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class RootProvider {
    public static void EnableRoot()
    {
        RunAsRoot("exit");
    }

    public static void RunAsRoot(String command)
    {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");

            os.flush();
            os.close();

            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void Remount() {
        RunAsRoot("remount");
    }
}
