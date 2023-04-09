//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mj.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Files {
    public Files() {
    }

    public static void writeToFile(String filePath, Object data) throws Throwable {
        writeToFile(filePath, data, false);
    }

    public static void writeToFile(String filePath, Object data, boolean append) throws Throwable {
        if (filePath != null && data != null) {
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                Throwable var4 = null;
                Object var5 = null;

                try {
                    FileWriter writer = new FileWriter(file, append);

                    try {
                        BufferedWriter out = new BufferedWriter(writer);

                        try {
                            out.write(data.toString());
                            out.flush();
                        } finally {
                            if (out != null) {
                                out.close();
                            }

                        }
                    } catch (Throwable var21) {
                        if (var4 == null) {
                            var4 = var21;
                        } else if (var4 != var21) {
                            var4.addSuppressed(var21);
                        }

                        if (writer != null) {
                            writer.close();
                        }

                        throw var4;
                    }

                    if (writer != null) {
                        writer.close();
                    }
                } catch (Throwable var22) {
                    if (var4 == null) {
                        var4 = var22;
                    } else if (var4 != var22) {
                        var4.addSuppressed(var22);
                    }

                    throw var4;
                }
            } catch (Exception var23) {
                var23.printStackTrace();
            }

        }
    }
}
