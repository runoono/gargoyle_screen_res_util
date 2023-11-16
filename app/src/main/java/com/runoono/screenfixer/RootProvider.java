package com.runoono.screenfixer;


import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class RootProvider {
    public static void EnableRoot()
    {
        RunAsRoot("exit");
    }

    public static String RunAsRoot(String command)
    {
        String response = "";
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");

            os.flush();
            os.close();

            try { p.waitFor(); } catch (InterruptedException e) { Log.d("EXCEPTION", e.toString());}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static void Remount() {
        RunAsRoot("remount");
    }
}
